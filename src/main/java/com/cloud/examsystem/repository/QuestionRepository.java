package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
