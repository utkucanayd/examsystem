package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExamRepository extends JpaRepository<StudentExam,Long> {
}
