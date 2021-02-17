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
            axios.post("/api/login/user",user).then(res=>{
                console.log(res.data)
            })
        }else{
            this.setState({control:"EmptyInput"})
        }
    }
    handleUsername=(e)=>{
        this.setState({username:e.target.value})
    }
    handlePassword=(e)=>{
        this.setState({password:e.target.value})
    }
    showAlert=()=>{
        if(this.state.control==="EmptyInput"){
            return(<Alert className="alert alert-danger" color="danger">
                Please Fill in the Required Fields!
            </Alert>)
        }else{
            return null
        }
    }

    render() {
        return (
            <div className="login-wrapper">
                <div>
                    <h1>Login</h1>
                    <div>
                        {this.showAlert()}
                    </div>
                    <Form onSubmit={(e) => {
                        e.preventDefault()
                    }}>
                        <FormGroup>
                            <Label for="username">Email</Label>
                            <Input type="text" name="user" id="username" onChange={(e)=>this.handleUsername(e)} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input type="password" name="password" id="password" onChange={(e)=>this.handlePassword(e)}/>
                        </FormGroup>
                        <div className="col text-right">
                            <Button type="submit" className="btn btn-primary" size="lg" color="primary" onClick={() => this.handleSubmit()
                            }>Submit</Button>
                        </div>
                    </Form>
                </div>
            </div>

        );
    }
}

export default withRouter(Login)