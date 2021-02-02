package com.cloud.examsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "es_User")
@Data
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    private String name;
}
