package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "es_Student")
@Data
public class Student extends User{
    @Column(name = "student_number")
    private String studentNumber;

    @OneToMany(mappedBy = "student")
    private Set<StudentExam> dates;

    @OneToMany(mappedBy = "student")
    private Set<StudentExam> grades;


}
