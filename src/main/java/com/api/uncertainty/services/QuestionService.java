package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAllByArea(EconomicArea area){
        return questionRepository.findAllByArea(area);
    }
}
