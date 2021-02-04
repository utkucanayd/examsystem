package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Instructor extends EsUser {

    @OneToMany
    private Set<Exam> exams;
}
