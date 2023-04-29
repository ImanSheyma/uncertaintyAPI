package com.api.uncertainty.services;

import com.api.uncertainty.repositories.EconomicAreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EconomicAreaServiceTest {
    @Mock
    private EconomicAreaRepository economicAreaRepository;
    private EconomicAreaService economicAreaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        economicAreaService = new EconomicAreaService(economicAreaRepository);
    }

    @Test
    void testFindByAreaName(){
    }
}
