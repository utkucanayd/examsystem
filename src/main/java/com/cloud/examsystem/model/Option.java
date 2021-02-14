package com.cloud.examsystem.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "es_option")
@Data
public class Option implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="option_id")
    private Long optionId;

    @Column(name="option_text")
    private String optionText;

    @Column(name = "is_answer")
    private Boolean isAnswer;

    @OneToMany
    @JoinColumn(nullable = false)
    private Set<StudentAnswer> studentAnswers;


}
