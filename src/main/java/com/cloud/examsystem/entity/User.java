package com.cloud.examsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "es_User")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    private UserType userType;
    private String name;
}
