import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import { withRouter} from "react-router-dom"
import axios from "axios";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            username:"",
            password:"",
            control:"",
        }
    }

    handleSubmit=()=>{
        if(this.state.username!=="" && this.state.password!=="" ){
            const user={
                username: this.state.username,
                password: this.state.password,
            }
            if(user.username==="student" && user.password==="1234"){
                this.props.history.push("/examList")
            }else if(user.username==="instructor" && user.password==="1234"){
                this.props.history.push("/createExam")
            }else{
                this.setState({control:"InvalidUser"})
            }
        }else{
            this.setState({control:"EmptyField"})
        }
    }
    handleUsername=(e)=>{
        this.setState({username:e.target.value})
    }
    handlePassword=(e)=>{
        this.setState({password:e.target.value})
    }
    showAlert=()=>{
        if(this.state.control==="EmptyField"){
            return(<Alert className="alert alert-danger" color="danger">
                Please Fill in the Required Fields!
            </Alert>)
        }else if(this.state.control==="InvalidUser"){
            return(<Alert className="alert alert-danger" color="danger">
                Username or Password is Wrong!
            </Alert>)
        }
        else{
            return null
        }
    }

    render() {
        return (
            <div className="login-wrapper">
                <div>
                    <h1>Login</h1>
                    {this.showAlert()}
                    <Form onSubmit={(e) => {
                        e.preventDefault()
                    }}>
                        <FormGroup>
                            <Label for="username">Email</Label>
                            <Input type="text" name="user" id="username" placeholder="username" onChange={(e)=>this.handleUsername(e)}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input type="password" name="password" id="password" placeholder="password" onChange={(e)=>this.handlePassword(e)}/>
                        </FormGroup>
                        <div className="col text-right">
                            <Button type="submit" className="btn btn-primary" size="lg" color="primary" onClick={() => {
                                this.handleSubmit()
                            }}>Submit</Button>
                        </div>
                    </Form>
                </div>
            </div>

        );
    }
}

export default withRouter(Login)