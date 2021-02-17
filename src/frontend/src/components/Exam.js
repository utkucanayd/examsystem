import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import axios from "axios";
import React from "react";
import {withRouter} from "react-router-dom";
import moment from "moment";


class Exam extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            exam: {},
            studentExam:null,
            grade:0.0,
            answerKey:[],
            isTooLate:false,
        }
    }
    calculateGrade=()=>{
        let answers=this.state.answerKey
        let grade=0.0
        this.state.studentExam.studentAnswers.forEach(studentAnswer=>{
            answers.forEach(answer=>{
                if(answer.questionId===studentAnswer.questionId){
                    if(answer.optionId===studentAnswer.optionId){
                        grade=grade+parseFloat(answer.grade)
                    }
                }
            })
        })
        let student=this.state.studentExam
        student.grade=grade
        this.setState({grade:grade,studentExam:student})

    }

    componentDidMount = () => {
        let examId=null;
        axios.get(`/api/${this.props.location.pathname}`).then(
            res => {
                console.log(res.data.examId)
                examId=res.data.examId
                const studentExam={
                    examId:examId,
                    studentId:2,
                    grade:0.0,
                    date:null,
                    studentAnswers:[],
                }
                this.setState({studentExam:studentExam})
                this.setState({exam: res.data})
                this.createAnswerKey()
            }
        )
    }

    createAnswerKey=()=>{
        let answers=[]
        this.state.exam.questions.forEach(question=>{
            question.options.forEach(option=>{
                if(option.isAnswer===true){
                    console.log("question grade:",question.grade)
                    answers.push({
                        questionId:question.questionId,
                        optionId:option.optionId,
                        grade:question.grade
                    })
                }
            })
        })
        this.setState({answerKey:answers})
    }

    selectedOption = (list) => {
        let selected = null
        list.forEach(item => {
            if (item.checked === true) {
                selected = item.optionId
            }
        })
        return selected
    }
    isAnsweredBefore = (answers, id) => {
        let control = false
        answers.forEach(answer => {
            if (answer.questionId === id) {
                control = true
            }
        })
        return control
    }
    handleSubmit=()=>{
        let date= new Date()
        this.calculateGrade()
        if(date.getTime()>moment(this.state.exam.endDate)){
            this.setState({isTooLate:true})
        }else{


            axios.post("/api/take/exam",this.state.studentExam).then
            (res=>{
                console.log(res)
                console.log(res.data)
            })
            this.props.history.push("/examList")
        }

    }

    onChangeAnswer = (checkedList, id) => {
        let selected = this.selectedOption(checkedList)
        let studentExam = this.state.studentExam
        if (selected !== null) {
            if (this.isAnsweredBefore(studentExam.studentAnswers, id)) {
                studentExam.studentAnswers.forEach(answer => {
                    if (answer.questionId === id) {
                        answer.optionId = selected
                    }
                })
            } else {
                const newAnswer = {
                    questionId: id,
                    optionId: selected,
                }
                studentExam.studentAnswers.push(newAnswer)
            }
        } else {

            if (this.isAnsweredBefore(studentExam.studentAnswers, id)) {
                studentExam.studentAnswers.forEach(answer => {
                    if (answer.questionId === id) {
                        studentExam.studentAnswers = this.deleteFromAnswers(studentExam.studentAnswers, id)
                    }
                })
            }
        }
        this.setState({studentExam: studentExam})

    }
    deleteFromAnswers = (answers, id) => {
        let newAnswers = []
        answers.forEach(answer => {
            if (answer.questionId !== id) {
                const newAnswer = {
                    questionId: answer.questionId,
                    optionId: answer.optionId,
                }
                newAnswers.push(newAnswer)
            }
        })
        return newAnswers
    }
    renderQuestions = () => {
        let counter = 0
        if (this.state.exam.questions) {
            return (
                this.state.exam.questions.map(question => {
                    return (
                        <div className="question">
                            <Question options={question.options} questionText={question.questionText}
                                      key={question.questionId}
                                      questionId={question.questionId}
                                      counter={counter = counter + 1}
                                      onChange={(e, f) => this.onChangeAnswer(e, f)}/>
                        </div>
                    )
                })
            )
        } else return null
    }

    render() {
        return (
            <div className="exam">

                <div className="col text-center">
                    <h1>{this.state.exam.examName}</h1>

                </div>
                <div>
                    {}
                </div>
                {this.renderQuestions()}
                <div className="col text-right">
                    <Button className="btn btn-primary " size="lg" color="primary" onClick={this.handleSubmit}>Submit</Button>
                </div>
            </div>

        );
    }
}

class Question extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            options: [],
            checkedList: [],
        }
    }

    componentDidMount = () => {
        this.setState({options: this.props.options})
        let list = []
        this.props.options.forEach(option => {
            const temp = {
                optionId: option.optionId,
                checked: false,

            }
            list.push(temp)
        })
        this.setState({checkedList: list})
    }

    findQuestionChecked = (id) => {
        let temp = null
        this.state.checkedList.forEach(item => {
            if (item.optionId === id) {
                temp = item.checked
            }
        })
        return temp
    }

    onChangeOption = (id) => {
        let list = this.state.checkedList
        list.forEach(item => {
            if (item.optionId === id) {
                item.checked = !item.checked
            }
        })
        list.forEach(item => {
            if (item.optionId !== id) {
                item.checked = false
            }
        })
        this.setState({checkedList: list})
    }

    render() {
        return (
            <div>
                <h3>Q{this.props.counter}){' '}{this.props.questionText}</h3>
                {this.state.options.map(option => {
                    return (
                        <div className="options ">
                            <Label size="lg" >
                                <Input  id={option.optionId} type="checkbox"
                                       checked={this.findQuestionChecked(option.optionId)}
                                       onClick={() => this.onChangeOption(option.optionId)}
                                       onChange={() => this.props.onChange(this.state.checkedList, this.props.questionId)}
                                        className="checkbox"
                                />
                                {option.optionText}
                            </Label>
                        </div>
                    )
                })}
            </div>
        );
    }
}

export default withRouter(Exam)





