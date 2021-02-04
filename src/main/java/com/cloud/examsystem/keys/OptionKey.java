package com.cloud.examsystem.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class OptionKey implements Serializable {
    @Column(name = "question")
    private QuestionKey question;
    @Column(name = "option_id")
    private Long optionID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionKey)) return false;
        OptionKey optionKey = (OptionKey) o;
        return Objects.equals(question, optionKey.question) && Objects.equals(optionID, optionKey.optionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, optionID);
    }
}
