package com.example.coursework2.service;

import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class JavaQuestionServiceTest {
    private QuestionService javaQuestionService;
    private QuestionRepository questionRepository;

    private Random random;

    @BeforeEach
    public void init() {
        questionRepository = Mockito.mock(QuestionRepository.class);
        random = Mockito.mock(Random.class);

        javaQuestionService = new JavaQuestionService(questionRepository, random);
    }

    @Test
    public void questionIsAdded() {
        Question question1 = new Question("hello", "test");

        when(questionRepository.add(question1)).thenReturn(question1);
        Question actual = question1;

        Assertions.assertEquals(actual, question1);
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = new Question("test", "test");
        Question question2 = new Question("hello", "answer");
        Question question3 = new Question("another", "another");

        when(questionRepository.getAll()).thenReturn(List.of(question1, question2, question3));
        when(random.nextInt(anyInt())).thenReturn(2);


        Question actualQuestion = javaQuestionService.getRandomQuestion();

        Assertions.assertEquals(question3, actualQuestion);

    }
}
