package com.example.coursework2.service;

import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService{

    private final QuestionRepository questionRepository;

    private final Random random;

    @Autowired
    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
    }

    protected JavaQuestionService(QuestionRepository questionRepository, Random random) {
        this.questionRepository = questionRepository;
        this.random = random;
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = new ArrayList<>(questionRepository.getAll());
        Integer index = random.nextInt(questions.size());
        return questions.get(index);
    }
}
