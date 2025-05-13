package com.example.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class NumeroParForm {
    
    @NotNull(message = "Debe ingresar un número")
    @Min(value = -1000000, message = "El número debe ser mayor o igual a -1.000.000")
    @Max(value = 1000000, message = "El número debe ser menor o igual a 1.000.000")
    private Integer numero;
    
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}