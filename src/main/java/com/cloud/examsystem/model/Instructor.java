package com.cloud.examsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Table(name = "es_instructor")
@Entity
@Data
public class Instructor extends User implements Serializable {



}
