package com.cloud.examsystem.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "es_Question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private String questionText;
    private Double penalty;
    private Double grade;

}
