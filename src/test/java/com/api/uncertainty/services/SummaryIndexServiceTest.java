package com.api.uncertainty.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SummaryIndexServiceTest {
    @Mock
    private EconomicAreaService economicAreaService;
    @Mock
    private QuestionService questionService;
    @Mock
    private UncertaintyIndexService uncertaintyIndexService;
    private SummaryIndexService summaryIndexService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        summaryIndexService = new SummaryIndexService(economicAreaService,
                questionService, uncertaintyIndexService);
    }

    @Test
    void testGetIndexes(){
    }

    @Test
    void testGetIndexesByArea(){
    }

    @Test
    void testGetIndexesByDateRange(){
    }

    @Test
    void testGetIndexesByAreaAndDateRange(){
    }

}
