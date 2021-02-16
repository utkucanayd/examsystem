package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Table(name = "es_student")
@Entity
@Data
public class Student extends User implements Serializable {
    @Column
    private String studentNumber;



}
