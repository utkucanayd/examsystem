package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Student extends EsUser {
    @Column
    private String studentNumber;

    @OneToMany
    private Set<StudentExam> dates;

    @OneToMany
    private Set<StudentExam> grades;


}
