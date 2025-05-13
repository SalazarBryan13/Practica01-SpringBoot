package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.SaludoService;

@Controller
public class SaludoController {
    @Autowired
    private SaludoService service; // Inyección de dependencia

    @RequestMapping("/saludo/{nombre}")
    public @ResponseBody  String saludo(@PathVariable(value = "nombre") String nombre) {
        return service.saludo(nombre); // Llama al servicio para obtener el saludo
    }
    
}
