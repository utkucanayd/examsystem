package com.cloud.examsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "es_StudentExam")
@Data
public class StudentExam {
    private java.util.Date Date;

}
