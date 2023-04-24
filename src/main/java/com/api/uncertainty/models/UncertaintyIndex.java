package com.api.uncertainty.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class UncertaintyIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne()
    @JsonIgnoreProperties("uncertaintyIndexes")
    private Question question;
    private double value;
    private Date date;
}
