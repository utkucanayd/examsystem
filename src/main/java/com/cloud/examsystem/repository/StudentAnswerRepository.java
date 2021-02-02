package com.cloud.examsystem.repository;

import com.cloud.examsystem.keys.StudentAnswerKey;
import com.cloud.examsystem.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, StudentAnswerKey> {
}
