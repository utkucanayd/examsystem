package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Table(name = "es_instructor")
@Entity
@Data
public class Instructor extends User {

    @OneToMany
    @JoinColumn(nullable = false)
    private Set<Exam> exams;

}
