import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import moment from "moment";
import axios from "axios";
import Exam from "./Exam";
import {withRouter} from "react-router-dom";




class ExamList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            exams: [],
            path: -1,
        }

    }

    componentDidMount = () => {
        axios.get("/api/exams").then(
            res => {
                console.log(res.data)
                this.setState({exams: res.data})

            }
        )
    }

    render() {
        return (
            <div>
                <Table>
                    <thead>
                    <tr>
                        <th>Exam Name</th>
                        <th>Instructor</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Take</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.exams.map(exam => {
                        return (
                            <tr key={exam.examId}>
                                <td>{exam.examName}</td>
                                <td>{exam.instructor.name}</td>
                                <td>{moment(exam.startDate).format("DD/MM/YYYY hh:mm")}</td>
                                <td>{moment(exam.endDate).format("DD/MM/YYYY hh:mm")}</td>
                                <td><Button onClick={()=>{
                                    this.props.history.push(`/exam/${exam.examId}`)
                                }} className="btn btn-primary" color="primary">Take</Button></td>
                            </tr>
                        )
                    })}
                    </tbody>
                </Table>
            </div>
        );
    }
}
export default withRouter(ExamList)