package com.cloud.examsystem.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "es_StudentAnswer")
@Data
public class StudentAnswer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_answer_id")
    private Long studentAnswerId;


}
