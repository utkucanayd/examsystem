package com.cloud.examsystem.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "es_Option")
@Data
public class Option {
    @Id
    @Column(name = "option_id")
    private Long optionId;

    @Column(name="option_text")
    private String optionText;

    @Column(name = "is_answer")
    private Boolean isAnswer;

}
