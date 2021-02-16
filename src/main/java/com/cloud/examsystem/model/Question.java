package com.cloud.examsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "es_Question")
@Data
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_text",
    columnDefinition = "TEXT")
    private String questionText;
    private Double penalty;
    private Double grade;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Set<Option> options;


}
