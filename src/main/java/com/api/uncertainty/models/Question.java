package com.api.uncertainty.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnoreProperties("questions")
    private EconomicArea area;
    private String question;
    @OneToMany(mappedBy = "question")
    private List<UncertaintyIndex> uncertaintyIndexes;
}
