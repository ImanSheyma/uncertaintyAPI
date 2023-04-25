package com.api.uncertainty.services;

import com.api.uncertainty.RTools.HodricPrescottFilter;
import com.api.uncertainty.RTools.SeasonalAdjustment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RToolsService {
    public List<Double> SeasonalAdjustment(List<Double> timeSeries){
        return SeasonalAdjustment.tramoSeas(timeSeries);
    }

    public List<Double> getHodrickPrescottFilterTrend(List<Double> timeSeries, Double lambda){
        return HodricPrescottFilter.getTrend(timeSeries, lambda);
    }

    public List<Double> getHodrickPrescottFilterCycle(List<Double> timeSeries, Double lambda){
        return HodricPrescottFilter.getCycle(timeSeries, lambda);
    }
}
