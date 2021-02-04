package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
