package com.cloud.examsystem.model;

import com.cloud.examsystem.keys.StudentAnswerKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "es_StudentAnswer")
@Data
public class StudentAnswer{

    @EmbeddedId
    private StudentAnswerKey id;

    @ManyToOne
    @MapsId("studentExamId")
    @JoinColumn(name = "student_exam_id")
    private StudentExam studentExam;
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name="question_id")
    private Question question;
    @ManyToOne
    @MapsId("optionId")
    @JoinColumn(name = "option_id")
    private Option option;

}
