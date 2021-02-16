package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> getByUserId(Long userId);
}
