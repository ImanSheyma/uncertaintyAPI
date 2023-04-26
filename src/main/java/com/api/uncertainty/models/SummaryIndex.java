package com.api.uncertainty.models;

import lombok.Data;

import java.util.Date;

@Data
public class SummaryIndex {
    private EconomicArea area;
    private double value;
    private Date date;

    public SummaryIndex(){
    }

    public SummaryIndex(EconomicArea area, double value, Date date){
        this.area = area;
        this.value = value;
        this.date = date;
    }
}
