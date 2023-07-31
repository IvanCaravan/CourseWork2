package com.example.coursework2.service;

import com.example.coursework2.exception.NotEnoughQuestionsException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JavaExaminerService implements ExaminerService{

    private final QuestionService javaQuestionService;

    public JavaExaminerService(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAll().size()) {
            throw new NotEnoughQuestionsException("Requested more questions than exists");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
