package com.cloud.examsystem.dto;

import com.cloud.examsystem.model.UserType;

public class AuthenticationResponseDto {
    private String username;
    private UserType userType;
    private Long userId;
    private String message;

    public AuthenticationResponseDto(String username, UserType userType, Long userId,String message) {
        this.username = username;
        this.userType = userType;
        this.userId = userId;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
