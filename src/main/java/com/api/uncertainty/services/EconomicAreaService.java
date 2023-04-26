package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.repositories.EconomicAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomicAreaService {
    @Autowired
    private EconomicAreaRepository economicAreaRepository;

    public List<EconomicArea> findAll(){
        return economicAreaRepository.findAll();
    }
    public Optional<EconomicArea> findByAreaName(String area){
        return economicAreaRepository.findByAreaName(area);
    }
}
