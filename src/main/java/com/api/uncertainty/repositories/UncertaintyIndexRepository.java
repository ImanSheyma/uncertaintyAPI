package com.api.uncertainty.repositories;

import com.api.uncertainty.models.Index;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.UncertaintyIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UncertaintyIndexRepository extends JpaRepository<UncertaintyIndex, Integer> {
    List<Index> findAllByQuestion(Question question);
    List<Index> findAllByQuestionAndDateAfterAndDateBefore(Question question,
                                                           Date dateStart, Date dateEnd);
}
