package com.api.uncertainty.repositories;

import com.api.uncertainty.models.EconomicArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EconomicAreaRepository extends JpaRepository<EconomicArea, Integer> {
    Optional<EconomicArea> findByArea(String area);
}
