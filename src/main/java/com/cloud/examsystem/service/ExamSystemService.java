package com.cloud.examsystem.service;

import com.cloud.examsystem.model.User;
import com.cloud.examsystem.model.UserType;
import com.cloud.examsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSystemService {
    private final UserRepository userRepository;

    @Autowired
    public ExamSystemService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
