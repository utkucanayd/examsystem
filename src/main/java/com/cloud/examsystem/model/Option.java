package com.cloud.examsystem.model;


import com.cloud.examsystem.keys.OptionKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "es_Option")
@Data
public class Option {
    @EmbeddedId
    private OptionKey Id;

    @Column(name="option_text")
    private String optionText;

    @Column(name = "is_answer")
    private Boolean isAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
