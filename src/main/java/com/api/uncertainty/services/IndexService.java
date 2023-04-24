package com.api.uncertainty.services;

import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.UncertaintyIndex;
import com.api.uncertainty.repositories.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    private IndexRepository indexRepository;

    public List<UncertaintyIndex> findAllByQuestion(Question question){
        return indexRepository.findAllByQuestion(question);
    }
}
