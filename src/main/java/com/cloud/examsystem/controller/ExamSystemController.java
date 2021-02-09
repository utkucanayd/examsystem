package com.cloud.examsystem.controller;

import com.cloud.examsystem.model.User;
import com.cloud.examsystem.service.ExamSystemService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class ExamSystemController {

    private final ExamSystemService examSystemService;

    @Autowired
    public ExamSystemController(ExamSystemService examSystemService) {
        this.examSystemService = examSystemService;
    }


    @GetMapping("/user")
    @SneakyThrows
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(examSystemService.getUsers());
    }

    @PostMapping("/adduser")
    @SneakyThrows
    public ResponseEntity<?> addUser(@RequestBody User user){
        System.out.println(user.getName());
        examSystemService.addUser(user);
        return ResponseEntity.ok("OK");
    }

}
