package com.cloud.examsystem.controller;

import com.cloud.examsystem.model.User;
import com.cloud.examsystem.service.ExamSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/home")
public class ExamSystemController {

    private final ExamSystemService examSystemService;

    @Autowired
    public ExamSystemController(ExamSystemService examSystemService) {
        this.examSystemService = examSystemService;
    }


    @GetMapping
    public List<User> getUsers(){
        return examSystemService.getUsers();
    }

}
