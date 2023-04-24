package com.api.uncertainty.exceptions;

public class AreaNotFoundException extends Exception{
    public AreaNotFoundException(String area){
        super("economic area " + area + " not found");
    }
}
