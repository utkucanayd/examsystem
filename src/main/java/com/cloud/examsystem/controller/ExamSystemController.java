package com.cloud.examsystem.controller;

import com.cloud.examsystem.model.Exam;
import com.cloud.examsystem.service.ExamSystemService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<?> addExam(@RequestBody Exam exam){
        
        examSystemService.addExam(exam);
        return ResponseEntity.ok("OK");
    }

}
