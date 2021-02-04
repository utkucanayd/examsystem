package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
