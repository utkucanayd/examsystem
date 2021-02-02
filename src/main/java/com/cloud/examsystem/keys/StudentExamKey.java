package com.cloud.examsystem.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class StudentExamKey implements Serializable {
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentExamKey)) return false;
        StudentExamKey that = (StudentExamKey) o;
        return Objects.equals(examId, that.examId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, userId);
    }
}
