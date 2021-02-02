package com.cloud.examsystem.entity;

public enum UserType {
    STUDENT("STUDENT"),
    INSTRUCTOR("INSTRUCTOR");
    private final String type;
    private UserType(String value)
    {
        this.type=value;
    }
    private String getValue()
    {
        return this.type;
    }


}
