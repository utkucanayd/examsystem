package com.cloud.examsystem.model;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "es_Exam")
@Data

public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Set<Question> questions;
    @OneToMany
    @JoinColumn(nullable = false)
    private Set<StudentExam> studentExams;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Instructor instructor;


}
