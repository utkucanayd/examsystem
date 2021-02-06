package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam,Long> {
}
