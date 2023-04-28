package com.api.uncertainty.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class EconomicArea {
    @Id
    private int id;
    private String areaName;
    private int weight;

    @OneToMany(mappedBy = "area")
    private List<Question> questions;
}
