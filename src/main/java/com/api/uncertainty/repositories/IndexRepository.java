package com.api.uncertainty.repositories;

import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.UncertaintyIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRepository extends JpaRepository<UncertaintyIndex, Integer> {
    List<UncertaintyIndex> findAllByQuestion(Question question);
}
