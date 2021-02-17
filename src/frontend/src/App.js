import './styles/App.css';
import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import CreateExam from "./components/CreateExam";
import "./styles/CreateExam.css"
import ExamList from "./components/ExamList";
import Exam from "./components/Exam";
import Login from "./components/Login";
import {BrowserRouter, Route} from "react-router-dom";
import Switch from "react-bootstrap/Switch";

function App() {
  return (
      <div className="page h-100">
        <BrowserRouter>
          <Switch>
            <Route path="/" exact component={Login}  />
            <Route path="/login" exact>
              <Login/>
            </Route>
            <Route path="/createExam">
              <CreateExam/>
            </Route>
            <Route path="/examList">
              <ExamList/>
            </Route>
            <Route path="/exam/:examId" component={Exam}/>
          </Switch>
        </BrowserRouter>
      </div>

  )
}

export default App;
