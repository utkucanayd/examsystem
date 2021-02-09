package com.cloud.examsystem.dto;

import com.cloud.examsystem.model.Question;

import java.util.Date;
import java.util.Set;

public class ExamDTO {
    private String examName;
    private Date startDate;
    private Date endDate;
    private Set<Question> questions;

}
