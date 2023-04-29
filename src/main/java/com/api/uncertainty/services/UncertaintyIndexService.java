package com.api.uncertainty.services;

import com.api.uncertainty.models.Index;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.repositories.UncertaintyIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UncertaintyIndexService {
    private final UncertaintyIndexRepository uncertaintyIndexRepository;

    public List<Index> findAllByQuestion(Question question){
        return uncertaintyIndexRepository.findAllByQuestion(question);
    }

    public List<Index> findAllByQuestionAndDateRange(Question question,
                                                     Date dateStart,
                                                     Date dateEnd){
        return uncertaintyIndexRepository.findAllByQuestionAndDateAfterAndDateBefore(
                question, dateStart, dateEnd);
    }
}
