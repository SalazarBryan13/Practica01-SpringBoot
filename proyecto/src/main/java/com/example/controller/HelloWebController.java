package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWebController {
    //generar la ruta de  la app web
    @RequestMapping("/hola")
    public String helloHandler() {
        return "hello"; // nombre de la vista
}
}