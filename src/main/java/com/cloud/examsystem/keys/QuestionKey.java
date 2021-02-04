package com.cloud.examsystem.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class QuestionKey implements Serializable {

    @Column(name = "question_id")
    private long questionId;
    @Column(name = "exam_id")
    private long examId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionKey)) return false;
        QuestionKey that = (QuestionKey) o;
        return questionId == that.questionId && examId == that.examId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, examId);
    }
}
