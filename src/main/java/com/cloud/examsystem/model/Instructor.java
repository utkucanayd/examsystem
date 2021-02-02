package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "es_Instructor")
@Data
public class Instructor extends User{

    @OneToMany
    private Set<Exam> exams;
}
