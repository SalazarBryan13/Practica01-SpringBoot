package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.SaludoService;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

@Controller
public class SaludoControllerForm {
    @Autowired
    private SaludoService service; // Inyección de dependencia

    @GetMapping("/saludoform")
    public String saludoForm(UserData userData) {
        return "formRegistro"; // nombre de la vista
    }

    @PostMapping("/saludoform")
    public String checkPersonInfo(@ModelAttribute @Valid UserData userData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formRegistro"; // nombre de la vista
        }
        model.addAttribute("mensaje", service.saludo(userData.getNombre())); // Llama al servicio para obtener el saludo
        return "saludo"; // nombre de la vista

    }
}
