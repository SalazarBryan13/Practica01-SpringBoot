package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.SaludoService;

@Controller
public class SaludoControllerPlantilla {

    @Autowired
    private SaludoService service; // Inyección de dependencia

    @RequestMapping("/saludoplantilla/{nombre}")
    public String saludo(@PathVariable(value = "nombre") String nombre,Model model) {
        model.addAttribute("mensaje", service.saludo(nombre)); 
        return "saludo";
    }
    
}
