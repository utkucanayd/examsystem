package com.cloud.examsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "es_Exam")
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;
    private String examName;
    private Date startDate;
    private Date endDate;

}
