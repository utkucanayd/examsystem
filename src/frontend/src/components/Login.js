import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Alert, Button, Form, FormGroup, Input, Label, ModalFooter, Table} from "reactstrap";
import { withRouter} from "react-router-dom"

class Login extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="login-wrapper">
                <div>
                    <h1>Login</h1>
                    <Form onSubmit={(e) => {
                        e.preventDefault()
                    }}>
                        <FormGroup>
                            <Label for="username">Email</Label>
                            <Input type="text" name="user" id="username" placeholder="username"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input type="password" name="password" id="password" placeholder="password"/>
                        </FormGroup>
                        <div className="col text-right">
                            <Button type="submit" className="btn btn-primary" size="lg" color="primary" onClick={() => {
                                this.props.history.push("/examList")
                            }}>Submit</Button>
                        </div>
                    </Form>
                </div>
            </div>

        );
    }
}

export default withRouter(Login)