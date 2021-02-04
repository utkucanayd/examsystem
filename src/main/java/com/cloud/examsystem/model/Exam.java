package com.cloud.examsystem.model;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "es_Exam")
@Data
public class Exam {
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

    @OneToMany(mappedBy = "exam")
    private Set<StudentExam> dates;

    @OneToMany(mappedBy = "exam")
    private Set<StudentExam> grades;

    @OneToMany(mappedBy = "exam")
    private Set<Question> questions;




}
