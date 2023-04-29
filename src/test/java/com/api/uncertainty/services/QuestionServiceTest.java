package com.api.uncertainty.services;

import com.api.uncertainty.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    private QuestionService questionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    void testFindAllByArea(){
    }
}
