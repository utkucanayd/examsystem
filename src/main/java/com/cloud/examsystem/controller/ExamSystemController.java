package com.cloud.examsystem.controller;

import com.cloud.examsystem.dto.StudentExamDTO;
import com.cloud.examsystem.model.Exam;
import com.cloud.examsystem.model.StudentExam;
import com.cloud.examsystem.service.ExamSystemService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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



    @PostMapping("/create/exam")
    @SneakyThrows
    public ResponseEntity<?> addExam(@RequestBody Exam exam){
        examSystemService.addExam(exam);
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/take/exam")
    @SneakyThrows
    public ResponseEntity<?> takeExam(@RequestBody StudentExamDTO studentExamDTO){
        examSystemService.takeExam(studentExamDTO);
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/exams")
    @SneakyThrows
    public ResponseEntity<?> getValidExams(){
        List<Exam> exams=examSystemService.getValidExams();
        return ResponseEntity.ok(exams);
    }
    @GetMapping(value = "/exam/{examId}")
    @SneakyThrows
    public ResponseEntity<?> getExam(@PathVariable("examId") Long examId){
        return ResponseEntity.ok(examSystemService.getExam(examId));
    }

}
