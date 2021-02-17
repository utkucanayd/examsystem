import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import moment from "moment";
import axios from "axios";
import cloneDeep from 'lodash/cloneDeep';
import "../styles/CreateExam.css";
import {withRouter} from "react-router-dom";


class CreateExam extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            examName: "",
            startDate: null,
            endDate: null,
            questions: [],
            counter: 1,
            alert: "",

        }
    }

    handleChangeExamName = (e) => {
        this.setState({examName: e.target.value})
    }
    handleChangeStartDate = (e) => {
        const date = moment(e.target.value).toDate()
        this.setState({startDate: date})
    }
    handleChangeEndDate = (e) => {
        const date = moment(e.target.value).toDate()
        this.setState({endDate: date})
    }

    addQuestion = (t, g, p, o, clear) => {
        const questions = this.state.questions
        const text = cloneDeep(t)
        const grade = cloneDeep(g)
        const penalty = cloneDeep(p)
        const options = cloneDeep(o)
        let counter = this.state.counter
        if (text !== "") {
            this.setState({alert: ""})
            if (this.checkIsAnyAnswerExist(options) === true) {
                this.setState({alert: ""})
                questions.push({
                    questionId: counter,
                    questionText: text,
                    options: options,
                    grade: grade,
                    penalty: penalty,
                })
                counter = counter + 1
                this.setState({questions: questions, counter: counter})
                clear()
            } else {
                this.setState({alert: "noAnswer"})
            }


        } else {
            this.setState({alert: "emptyQuestionText"})
        }

    }

    checkIsAnyAnswerExist = (options) => {
        let control = false
        options.forEach(option => {
            if (option.isAnswer === true) {
                control = true;
            }
        })
        return control
    }
    handleChangeQuestionText = (e, id) => {
        let questions = this.state.questions
        questions.forEach(question => {
            if (question.questionId === id) {
                question.questionText = e.target.value
            }
        })
        this.setState({questions: questions})
    }
    handleChangeGrade = (e, id) => {
        let questions = this.state.questions
        questions.forEach(question => {
            if (question.questionId === id) {
                question.grade = e.target.value
            }
        })
        this.setState({questions: questions})
    }

    deleteOption = (qId, oId) => {
        let questions = this.state.questions
        let newOptions = []
        let selectedQuestion = {}
        questions.forEach(question => {
            if (question.questionId === qId) {
                selectedQuestion = question
                question.options.forEach(option => {
                    if (option.optionId !== oId) {
                        newOptions.push(option)
                    }
                })
                question.options = newOptions

            }

        })

        if (newOptions.length === 0) {
            this.deleteQuestion(qId)
        } else {
            this.setState({questions: questions})
        }
    }
    handleChangeAnswer = (qId, oId) => {
        let questions = this.state.questions
        questions.forEach(question => {
            if (question.questionId === qId) {
                question.options.forEach(option => {
                    if (option.optionId !== oId) {
                        option.isAnswer = false
                    } else {
                        option.isAnswer = true
                    }
                })
            }
        })
        this.setState({questions: questions})
    }
    handleChangeOptionText = (e, qId, oId) => {
        let questions = this.state.questions
        questions.forEach(question => {
            if (question.questionId === qId) {
                question.options.forEach(option => {
                    if (option.optionId !== oId) {
                        option.optionText = e.target.value
                    }
                })
            }
        })
        this.setState({questions: questions})
    }
    deleteQuestion = (id) => {
        let questions = this.state.questions
        let newQuestions = []
        questions.forEach(question => {
            if (question.questionId !== id) {
                newQuestions.push(question)
            }
        })
        this.setState({questions: newQuestions})
    }
    renderQuestions = () => {
        if (this.state.counter === 1) {
            return null
        } else {
            return (
                <div className="addedQuestions">
                    {this.state.questions.map(question => {
                        return (
                            <div key={question.questionId} className="question">

                                <div>
                                    <Label className="col-12">
                                        <h3>Q{question.questionId})</h3>
                                        <Input id={"question" + question.questionId}
                                               defaultValue={question.questionText}
                                               onChange={(e) => this.handleChangeQuestionText(e, question.questionId)}/>
                                    </Label>
                                </div>
                                <div>
                                    <Label className="col-3">
                                        Grade:{' '}
                                        <Input id={"grade" + question.questionId} defaultValue={question.grade}
                                               onChange={(e) => this.handleChangeGrade(e, question.questionId)}/>
                                    </Label>
                                    <Button className="btn btn-danger" color="danger"
                                            onClick={() => this.deleteQuestion(question.questionId)}>Delete</Button>
                                </div>

                                <div className="options">
                                    <Table>
                                        <thead>
                                        <tr>
                                            <th>Option Text</th>
                                            <th>Is Answer</th>
                                            <th>Select as Answer</th>
                                            <th>Delete</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {question.options.map(option => {
                                            return (
                                                <tr key={option.optionId}>
                                                    <td><Input id={"option" + option.optionId}
                                                               defaultValue={option.optionText}
                                                               onChange={(e) => this.handleChangeOptionText(e, question.questionId, option.optionId)}/>
                                                    </td>
                                                    <td>{option.isAnswer && <h5>True</h5>}
                                                        {!option.isAnswer && <h5>False</h5>}</td>
                                                    <td><Button className="btn btn-primary" color="primary"
                                                                onClick={() => this.handleChangeAnswer(question.questionId, option.optionId)}>Select</Button>
                                                    </td>
                                                    <td><Button className="btn btn-danger" color="danger"
                                                                onClick={() => this.deleteOption(question.questionId, option.optionId)}>Delete</Button>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                        </tbody>
                                    </Table>
                                </div>
                            </div>)
                    })}
                </div>
            )
        }
    }

    checkGradeSum = () => {
        let sum = 0
        this.state.questions.forEach(question => {
            sum = sum + parseFloat(question.grade)
        })
        console.log(sum)
        return sum === 100.0
    }
    prepareJSON = () => {
        let questions = []
        this.state.questions.forEach(question => {
            let options = []
            question.options.map(option => {
                options.push({
                    optionText: option.optionText,
                    isAnswer: option.isAnswer,
                })
            })
            questions.push({
                questionText: question.questionText,
                grade: question.grade,
                penalty: question.penalty,
                options: options
            })

        })
        const exam = {
            examName: this.state.examName,
            startDate: this.state.startDate,
            endDate: this.state.endDate,
            questions: questions
        }
        return exam
    }
    handleSubmit = () => {
        if(this.state.examName===""){
            this.setState({alert: "emptyExamName"})
        }else{
            this.setState({alert: ""})
            if(this.state.questions.length!==0){
                this.setState({alert: ""})
                if(this.state.startDate!==null){
                    this.setState({alert: ""})
                    if(this.state.endDate!==null){
                        this.setState({alert: ""})
                        if (this.checkGradeSum()) {
                            this.setState({alert: ""})
                            const exam = this.prepareJSON()
                            console.log(exam)
                            axios.post("/api/create/exam", exam).then
                            (res => {
                                console.log(res)
                                console.log(res.data)
                            })
                            console.log(exam)
                            this.setState({alert: false})
                            this.props.history.go(0)
                        } else {
                            this.setState({alert: "sum"})
                        }
                    }else{
                        this.setState({alert: "emptyEndDate"})
                    }


                }else{
                    this.setState({alert: "emptyStartDate"})
                }
            }else{
                this.setState({alert: "thereIsNoQuestion"})
            }


        }



    }
    alert = () => {
        if (this.state.alert === "sum") {
            return (<Alert color="danger">The sum of the question grades must be equal to 100</Alert>)
        } else if (this.state.alert === "noAnswer") {
            return (<Alert color="danger">Question doesn't have any answer!</Alert>)
        } else if (this.state.alert === "emptyQuestionText") {
            return (<Alert color="danger">Question Text is Empty!</Alert>)
        } else if (this.state.alert === "emptyExamName") {
            return (<Alert color="danger">Exam Name is Empty!</Alert>)
        } else if (this.state.alert === "emptyStartDate") {
            return (<Alert color="danger">Pick a Start Date!</Alert>)
        } else if (this.state.alert === "emptyEndDate") {
            return (<Alert color="danger">Pick a End Date!</Alert>)
        }else if (this.state.alert === "thereIsNoQuestion") {
            return (<Alert color="danger">No question Added!</Alert>)
        } else {
            return null
        }
    }

    render() {
        return (
            <div className="createExam ">
                <div>
                    <div className="col text-center">
                        <h1>Create New Exam</h1>
                    </div>
                    {this.alert()}
                </div>
                <ExamInfo handleChangeExamName={(e) => this.handleChangeExamName(e)}
                          handleChangeStartDate={(e) => this.handleChangeStartDate(e)}
                          handleChangeEndDate={(e) => this.handleChangeEndDate(e)}/>
                {this.renderQuestions()}
                <AddQuestion addQuestion={(e, f, k, h, l) => this.addQuestion(e, f, k, h, l)}/>
                <div className="col text-center">
                    <Button color="primary" size="lg" className="button submitButton"
                            onClick={this.handleSubmit}>Submit</Button>
                </div>


            </div>
        )
    }
}

class ExamInfo extends React.Component {
    render() {
        return (
            <div className="border examInfo">
                <div>
                    <Label>
                        Exam Name :
                        <Input type="text" onChange={this.props.handleChangeExamName}/>
                    </Label>
                </div>
                <div className="row">
                    <div className="col-6">
                        <Label>
                            Start Date:
                            <Input
                                id="startDate"
                                bsSize="lg"
                                type="datetime-local"
                                name="startDate"
                                onChange={this.props.handleChangeStartDate}
                            />
                        </Label>
                    </div>
                    <div className="col-6">
                        <Label>
                            End Date:
                            <Input
                                id="endDate"
                                bsSize="lg"
                                type="datetime-local"
                                name="endDate"
                                onChange={this.props.handleChangeEndDate}
                            />
                        </Label>
                    </div>
                </div>
            </div>
        )
    }
}

class AddQuestion extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            questionText: "",
            grade: 0.0,
            penalty: 0.0,
            options: [],
            counter: 1,
            alertControl: "",

        }
    }


    handleChangeQuestionText = (e) => {
        this.setState({questionText: e.target.value})
    }
    alertMessage = () => {
        let control = this.state.alertControl
        if (control === "emptyOptionText") {
            return <Alert color="danger">Option Text is Empty!</Alert>
        } else if (control === "noAnswer") {
            return <Alert color="danger">Question Must has an Answer!</Alert>
        } else {
            return null
        }

    }
    addOption = (t, a, clear) => {
        if (t !== "") {
            const text = cloneDeep(t)
            const answer = cloneDeep(a)
            const options = this.state.options
            if (answer) {
                options.forEach(option => {
                        if (option.isAnswer === true) {
                            option.isAnswer = false
                        }
                    }
                )
            }
            options.push({
                optionId: this.state.counter,
                optionText: text,
                isAnswer: answer,
            })
            const counter = this.state.counter + 1
            this.setState({counter: counter, options: options})
            this.setState({alertControl: ""})
            clear()
        } else {
            this.setState({alertControl: "emptyOptionText"})
        }

    }
    handleChangeAnswer = (id) => {
        let options = this.state.options
        options.forEach(option => {
            if (option.optionId === id) {
                option.isAnswer = true
            } else {
                option.isAnswer = false
            }
        })
        this.setState({options: options})
    }
    deleteOption = (id) => {
        let options = this.state.options
        let newOptions = []
        options.forEach(option => {
            if (option.optionId !== id) {
                newOptions.push(option)
            }
        })
        this.setState({options: newOptions})
    }
    handleChangeOptionText = (e, id) => {
        let options = this.state.options
        options.forEach(option => {
            if (option.optionId === id) {
                option.optionText = e.target.value
            }
        })
        this.setState({options: options})
    }

    renderAddedOptions = () => {
        if (this.state.counter === 1) {
            return null
        } else {
            return (
                <div className="border addedOptions">

                    <Table>
                        <thead>
                        <tr>
                            <th>Option Text</th>
                            <th>Is Answer</th>
                            <th>Select as Answer</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.options.map(option => {
                            return (
                                <tr key={option.optionId}>
                                    <td><Input id={option.optionId} disabled={false}
                                               defaultValue={option.optionText}
                                               onChange={(e) => this.handleChangeOptionText(e, option.optionId)}/>
                                    </td>
                                    <td>{option.isAnswer && <h5>True</h5>}
                                        {!option.isAnswer && <h5>False</h5>}</td>
                                    <td><Button className="btn btn-primary" color="primary"
                                                onClick={() => this.handleChangeAnswer(option.optionId)}>Select</Button>
                                    </td>
                                    <td><Button className="btn btn-danger" color="danger"
                                                onClick={() => this.deleteOption(option.optionId)}>Delete</Button>
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </Table>


                </div>
            )
        }

    }

    clearStates = () => {
        document.getElementById("questionText").value = ""
        document.getElementById("grade").value = ""
        this.setState({questionText: "", options: [], counter: 1, grade: 0.0, penalty: 0.0, alertControl: ""})
    }
    handleChangeGrade = (e) => {
        const grade = e.target.value
        const penalty = grade * 33 / 100
        this.setState({grade: grade, penalty: penalty})
    }

    render() {
        return (
            <div className="addQuestion">
                <div>
                    <Label>
                        Question Text:
                        <Input type="text" onChange={this.handleChangeQuestionText} id="questionText"/>
                    </Label>
                    <Label>
                        Grade:
                        <Input type="text" onChange={this.handleChangeGrade} id="grade"/>
                    </Label>
                </div>
                {this.renderAddedOptions()}
                <div>
                    {this.alertMessage()}
                </div>
                <AddOption
                    addOption={(e, f, k) => this.addOption(e, f, k)}/>
                <div className="col text-right">
                    <Button
                        color="success"
                        size="lg"
                        className="button"
                        onClick={() => this.props.addQuestion(this.state.questionText, this.state.grade, this.state.penalty, this.state.options, () => this.clearStates())}>Add
                        Question</Button>
                </div>
            </div>
        )
    }
}

class AddOption extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            optionText: "",
            isAnswer: false,
        }
    }

    handleChaneOptionText = (e) => {
        this.setState({optionText: e.target.value})
    }
    handleChangeIsAnswer = () => {
        const isAnswer = !this.state.isAnswer
        this.setState({isAnswer: isAnswer})
    }
    clearStates = () => {
        document.getElementById("optionText").value = ""
        document.getElementById("isAnswer").checked = false
        this.setState({optionText: "", isAnswer: false})
    }

    render() {
        return (
            <div className="addOption">
                <div>
                    <Label>
                        Option Text:
                        <Input type="text" onChange={this.handleChaneOptionText} id="optionText"/>
                    </Label>
                    <Label className="deneme">
                        isAnswer:
                        <Input type="checkbox" className="deneme " onChange={this.handleChangeIsAnswer} id="isAnswer"/>
                    </Label>
                </div>
                <div className="col text-right">
                    <Button
                        color="success" size="lg" className="button"
                        onClick={() => this.props.addOption(this.state.optionText, this.state.isAnswer, () => this.clearStates())}>Add
                        Option</Button>
                </div>
            </div>
        )
    }
}

export default withRouter(CreateExam)