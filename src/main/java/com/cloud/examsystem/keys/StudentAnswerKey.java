package com.cloud.examsystem.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class StudentAnswerKey implements Serializable {

    @Column(name="student_exam_id")
    private StudentExamKey studentExamId;
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "option_id")
    private Long optionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentAnswerKey)) return false;
        StudentAnswerKey that = (StudentAnswerKey) o;
        return studentExamId.equals(that.studentExamId) && questionId.equals(that.questionId) && optionId.equals(that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentExamId, questionId, optionId);
    }
}
