package com.cloud.examsystem.model;

import com.cloud.examsystem.keys.StudentExamKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "es_StudentExam")
@Data
public class StudentExam{
    @EmbeddedId
    private StudentExamKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Student student;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private java.util.Date date;
    private Double grade;


}
