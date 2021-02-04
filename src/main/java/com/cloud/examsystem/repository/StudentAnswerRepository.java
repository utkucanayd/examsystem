package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer,Long> {
}
