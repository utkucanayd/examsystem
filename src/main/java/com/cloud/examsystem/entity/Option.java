package com.cloud.examsystem.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "es_Option")
@Data
public class Option {
    private Long optionId;
    private String optionText;
    private Boolean isAnswer;

}
