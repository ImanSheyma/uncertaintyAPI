package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> findAllByArea(EconomicArea area){
        return questionRepository.findAllByArea(area);
    }
}
