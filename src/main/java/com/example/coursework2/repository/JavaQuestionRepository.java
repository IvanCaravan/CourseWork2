package com.example.coursework2.repository;

import com.example.coursework2.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository{
    private final Set<Question> questions;

    public JavaQuestionRepository(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }
}
