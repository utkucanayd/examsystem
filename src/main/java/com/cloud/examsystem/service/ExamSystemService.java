package com.cloud.examsystem.service;

import com.cloud.examsystem.model.Exam;
import com.cloud.examsystem.model.Instructor;
import com.cloud.examsystem.model.User;
import com.cloud.examsystem.repository.ExamRepository;
import com.cloud.examsystem.repository.InstructorRepository;
import com.cloud.examsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSystemService {
    private final UserRepository userRepository;
    private final ExamRepository examRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public ExamSystemService(UserRepository userRepository, InstructorRepository instructorRepository,ExamRepository examRepository) {
        this.userRepository = userRepository;
        this.examRepository=examRepository;
        this.instructorRepository=instructorRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<Exam> getExams(){
        return examRepository.findAll();
    }
    public Exam getExam(Long examId){
        return examRepository.getByExamId(examId).get();
    }

    public void addExam(Exam exam){

        exam.setInstructor(instructorRepository.getOne(1L));
        examRepository.save(exam);
    }

}
