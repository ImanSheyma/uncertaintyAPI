package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.repositories.EconomicAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EconomicAreaService {
    @Autowired
    private EconomicAreaRepository economicAreaRepository;

    public Optional<EconomicArea> findByArea(String area){
        return economicAreaRepository.findByArea(area);
    }
}
