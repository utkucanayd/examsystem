package com.cloud.examsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "es_Question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_text",
    nullable = false,
    columnDefinition = "TEXT")
    private String questionText;
    private Double penalty;
    private Double grade;
    @OneToMany
    @JoinColumn(nullable = false)
    private Set<Option> options;

    @OneToMany
    @JoinColumn(nullable = false)
    private Set<StudentAnswer> studentAnswers;

}
