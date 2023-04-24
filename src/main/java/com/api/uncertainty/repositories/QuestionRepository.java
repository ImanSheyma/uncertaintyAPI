package com.api.uncertainty.repositories;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByArea(EconomicArea Area);
}
