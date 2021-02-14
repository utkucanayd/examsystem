package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    Optional<Exam> getByExamId(Long id);
}
