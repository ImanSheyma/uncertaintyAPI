package com.api.uncertainty.services;

import com.api.uncertainty.repositories.UncertaintyIndexRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UncertaintyIndexServiceTest {
    @Mock
    private UncertaintyIndexRepository uncertaintyIndexRepository;
    private UncertaintyIndexService uncertaintyIndexService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        uncertaintyIndexService = new UncertaintyIndexService(uncertaintyIndexRepository);
    }

    @Test
    void testFindAllByQuestion(){
    }

    @Test
    void testFindAllByQuestionAndDateRange(){
    }
}
