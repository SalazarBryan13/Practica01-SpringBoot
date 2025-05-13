

package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class NumeroParService {
    
    /**
     * Verifica si un número es par
     * @param numero El número a verificar
     * @return true si el número es par, false en caso contrario
     */
    public boolean esPar(int numero) {
        return numero % 2 == 0;
    }
    
    /**
     * Genera un mensaje indicando si el número es par o impar
     * @param numero El número a verificar
     * @return Un mensaje indicando si el número es par o impar
     */
    public String verificarNumeroPar(int numero) {
        if (esPar(numero)) {
            return "El número " + numero + " es par.";
        } else {
            return "El número " + numero + " es impar.";
        }
    }
} 
