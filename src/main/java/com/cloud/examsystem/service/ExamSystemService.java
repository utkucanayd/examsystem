package com.cloud.examsystem.service;

import com.cloud.examsystem.dto.StudentAnswerDTO;
import com.cloud.examsystem.dto.StudentExamDTO;
import com.cloud.examsystem.model.*;
import com.cloud.examsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamSystemService {
    private final UserRepository userRepository;
    private final ExamRepository examRepository;
    private final InstructorRepository instructorRepository;
    private final StudentExamRepository studentExamRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final StudentAnswerRepository studentAnswerRepository;


    @Autowired
    public ExamSystemService(UserRepository userRepository, InstructorRepository instructorRepository, ExamRepository examRepository, StudentExamRepository studentExamRepository, StudentRepository studentRepository, QuestionRepository questionRepository, OptionRepository optionRepository, StudentAnswerRepository studentAnswerRepository) {
        this.userRepository = userRepository;
        this.examRepository = examRepository;
        this.instructorRepository = instructorRepository;
        this.studentExamRepository = studentExamRepository;
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.studentAnswerRepository = studentAnswerRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Exam> getExams() {
        return examRepository.findAll();
    }

    public Exam getExam(Long examId) {
        return examRepository.getByExamId(examId).get();
    }

    public void addExam(Exam exam) {

        exam.setInstructor(instructorRepository.getOne(1L));
        examRepository.save(exam);
    }

    public void takeExam(StudentExamDTO studentExamDTO) {
        Date date=studentExamDTO.getDate();
        Double grade=studentExamDTO.getGrade();
        Exam exam = examRepository.getByExamId(studentExamDTO.getExamId()).get();
        Student student = studentRepository.getByUserId(studentExamDTO.getStudentId()).get();
        Set<StudentAnswer> studentAnswers = new HashSet<>();
        for (StudentAnswerDTO answer:
             studentExamDTO.getStudentAnswers()) {
            Option option=optionRepository.getByOptionId(answer.getOptionId()).get();
            Question question=questionRepository.getByQuestionId(answer.getQuestionId()).get();
            StudentAnswer newAnswer=new StudentAnswer();
            newAnswer.setOption(option);
            newAnswer.setQuestion(question);
            studentAnswers.add(newAnswer);
        }
        StudentExam newExam=new StudentExam();
        newExam.setExam(exam);
        newExam.setStudent(student);
        newExam.setDate(date);
        newExam.setGrade(grade);
        newExam.setStudentAnswers(studentAnswers);
        studentExamRepository.save(newExam);
    }
}
