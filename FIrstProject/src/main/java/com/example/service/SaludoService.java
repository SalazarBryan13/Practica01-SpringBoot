package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String saludo(String nombre) {
        return "Hola " + nombre + ", bienvenido a la aplicación web!";
    }
    
}
