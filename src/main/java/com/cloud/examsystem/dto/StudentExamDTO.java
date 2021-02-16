package com.cloud.examsystem.dto;

import com.cloud.examsystem.model.StudentAnswer;

import java.util.Date;
import java.util.Set;

public class StudentExamDTO {
    private Long examId;
    private Long studentId;
    private Date date;
    private Double grade;
    private Set<StudentAnswerDTO> studentAnswers;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Set<StudentAnswerDTO> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Set<StudentAnswerDTO> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }
}
