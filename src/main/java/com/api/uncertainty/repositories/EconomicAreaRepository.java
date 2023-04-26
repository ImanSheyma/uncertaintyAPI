package com.api.uncertainty.repositories;

import com.api.uncertainty.models.EconomicArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EconomicAreaRepository extends JpaRepository<EconomicArea, Integer> {
    Optional<EconomicArea> findByAreaName(String area);
    List<EconomicArea> findAll();
}
