package com.api.uncertainty.services;

import com.api.uncertainty.models.Index;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.UncertaintyIndex;
import com.api.uncertainty.repositories.UncertaintyIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UncertaintyIndexService {
    @Autowired
    private UncertaintyIndexRepository uncertaintyIndexRepository;

    public List<Index> findAllByQuestion(Question question){
        return uncertaintyIndexRepository.findAllByQuestion(question);
    }
}
