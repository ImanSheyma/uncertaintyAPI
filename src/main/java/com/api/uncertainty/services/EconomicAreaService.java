package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.repositories.EconomicAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EconomicAreaService {
    private final EconomicAreaRepository economicAreaRepository;

    public List<EconomicArea> findAll(){
        return economicAreaRepository.findAll();
    }
    public Optional<EconomicArea> findByAreaName(String area){
        return economicAreaRepository.findByAreaName(area);
    }
}
