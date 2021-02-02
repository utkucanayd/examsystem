package com.cloud.examsystem.repository;

import com.cloud.examsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User,Long> {
}
