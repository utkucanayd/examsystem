package com.cloud.examsystem.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "es_Student")
@Data
public class Student extends User{
    private String studentNumber;

}
