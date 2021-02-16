package com.cloud.examsystem.model;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "es_StudentExam")
@Data
public class StudentExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_exam_id")
    private Long studentExamId;

    private Date date;
    private Double grade;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Exam exam;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Set<StudentAnswer> studentAnswers;



}
