package com.api.uncertainty.models;

import lombok.Data;

import java.util.Date;

@Data
public class SummaryIndex {
    private EconomicArea area;
    private double value;
    private Date date;
}
