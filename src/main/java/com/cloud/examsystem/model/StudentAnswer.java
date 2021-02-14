package com.cloud.examsystem.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "es_StudentAnswer")
@Data
public class StudentAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_answer_id")
    private Long studentAnswerId;


}
