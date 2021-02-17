import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import {Redirect, withRouter} from "react-router-dom"
import React from "react";
class InstructorPage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Button className="btn btn-primary" color="primary" onClick={()=>{
                    this.props.history.push("/createExam")
                }}>
                    Create Exam
                </Button>
            </div>
        );
    }

}
export default withRouter(InstructorPage)