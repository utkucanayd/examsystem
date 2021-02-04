package com.cloud.examsystem.model;


import com.cloud.examsystem.keys.QuestionKey;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "es_Question")
@Data
public class Question {
    @EmbeddedId
    private QuestionKey Id;

    @Column(name = "question_text",
    nullable = false,
    columnDefinition = "TEXT")
    private String questionText;

    private Double penalty;
    private Double grade;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;

    @OneToMany
    private Set<Option> options;


}
