package com.example.textTrivia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Entity
@Table(name="question")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "textId")
    private Text text;

    @ManyToOne
    @JoinColumn(name = "token_id", nullable = false)
    @JsonBackReference
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @NotEmpty
    private String questionContent;

    @NotEmpty
    private String firstAnswer;

    @NotEmpty
    private String secondAnswer;

    @NotEmpty
    private String thirdAnswer;

    @NotEmpty
    private String fourthAnswer;

    @javax.validation.constraints.NotNull
    private Integer numberCorrectAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Text getText() {
        return text;
    }

    public void setText(@NotNull Text text) {
        this.text = text;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public String getFourthAnswer() {
        return fourthAnswer;
    }

    public void setFourthAnswer(String fourthAnswer) {
        this.fourthAnswer = fourthAnswer;
    }

    public Integer getNumberCorrectAnswer() {
        return numberCorrectAnswer;
    }

    public void setNumberCorrectAnswer(Integer numberCorrectAnswer) {
        this.numberCorrectAnswer = numberCorrectAnswer;
    }


    public static final class QuestionBuilder {
        private Long id;
        private Text text;
        private Token token;
        private @NotEmpty String questionContent;
        private @NotEmpty String firstAnswer;
        private @NotEmpty String secondAnswer;
        private @NotEmpty String thirdAnswer;
        private @NotEmpty String fourthAnswer;
        private @javax.validation.constraints.NotNull Integer numberCorrectAnswer;

        private QuestionBuilder() {
        }

        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public QuestionBuilder withText(Text text) {
            this.text = text;
            return this;
        }

        public QuestionBuilder withToken(Token token) {
            this.token = token;
            return this;
        }

        public QuestionBuilder withQuestionContent(String questionContent) {
            this.questionContent = questionContent;
            return this;
        }

        public QuestionBuilder withFirstAnswer(String firstAnswer) {
            this.firstAnswer = firstAnswer;
            return this;
        }

        public QuestionBuilder withSecondAnswer(String secondAnswer) {
            this.secondAnswer = secondAnswer;
            return this;
        }

        public QuestionBuilder withThirdAnswer(String thirdAnswer) {
            this.thirdAnswer = thirdAnswer;
            return this;
        }

        public QuestionBuilder withFourthAnswer(String fourthAnswer) {
            this.fourthAnswer = fourthAnswer;
            return this;
        }

        public QuestionBuilder withNumberCorrectAnswer(Integer numberCorrectAnswer) {
            this.numberCorrectAnswer = numberCorrectAnswer;
            return this;
        }

        public Question build() {
            Question question = new Question();
            question.setId(id);
            question.setText(text);
            question.setToken(token);
            question.setQuestionContent(questionContent);
            question.setFirstAnswer(firstAnswer);
            question.setSecondAnswer(secondAnswer);
            question.setThirdAnswer(thirdAnswer);
            question.setFourthAnswer(fourthAnswer);
            question.setNumberCorrectAnswer(numberCorrectAnswer);
            return question;
        }
    }
}

