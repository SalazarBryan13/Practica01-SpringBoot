package com.example.controller;

import com.example.model.NumeroParForm;
import com.example.service.NumeroParService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class NumeroParController {
    
    @Autowired
    private NumeroParService numeroParService;
    
    /**
     * Muestra el formulario para ingresar un número
     */
    @GetMapping("/numeropar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("numeroParForm", new NumeroParForm());
        return "numeropar-form";
    }
    
    /**
     * Procesa el formulario y verifica si el número es par
     */
    @PostMapping("/numeropar")
    public String verificarNumeroPar(@Valid NumeroParForm numeroParForm, 
                                    BindingResult bindingResult, 
                                    Model model) {
        // Si hay errores de validación, volvemos al formulario
        if (bindingResult.hasErrors()) {
            return "numeropar-form";
        }
        
        // Llamamos al servicio para verificar si el número es par
        String resultado = numeroParService.verificarNumeroPar(numeroParForm.getNumero());
        model.addAttribute("resultado", resultado);
        model.addAttribute("esPar", numeroParService.esPar(numeroParForm.getNumero()));
        
        return "numeropar-resultado";
    }
}