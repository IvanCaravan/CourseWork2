package com.example.coursework2.service;

import com.example.coursework2.exception.NotEnoughQuestionsException;
import com.example.coursework2.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class JavaExaminerServiceTest {

    private ExaminerService examinerService;
    private QuestionService questionService;
    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    public void init() {
        questionService = Mockito.mock(QuestionService.class);
        examinerService = new JavaExaminerService(questionService);

        question1 = new Question("test", "test");
        question2 = new Question("hello", "answer");
        question3 = new Question("another", "another");

    }

    @Test
    public void testGetQuestionWhenServiceReturnsUniqueQuestions() {
        when(questionService.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);
        when(questionService.getAll()).thenReturn(List.of(question1, question2, question3));

        Collection<Question> questions = examinerService.getQuestions(3);

        Assertions.assertEquals(3, questions.size());
        Mockito.verify(questionService, times(3)).getRandomQuestion();
    }

    @Test
    public void testGetQuestionWhenRequestsMoreThanExists() {
        when(questionService.getAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(3));
    }

    @Test
    public void testGetQuestionWhenServiceReturnsDuplicateQuestions() {
        when(questionService.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question1)
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question2)
                .thenReturn(question3);

        when(questionService.getAll()).thenReturn(List.of(question1, question2, question3));

        Collection<Question> questions = examinerService.getQuestions(3);

        Assertions.assertEquals(3, questions.size());
        Mockito.verify(questionService, times(6)).getRandomQuestion();

    }
}
