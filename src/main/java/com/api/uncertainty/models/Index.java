package com.api.uncertainty.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class Index {
    private double value;
    private Date date;

    public Index(){
    }

    public Index(double value, Date date){;
        this.value = value;
        this.date = date;
    }
}
