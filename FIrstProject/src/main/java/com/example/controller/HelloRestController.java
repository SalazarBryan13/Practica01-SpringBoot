package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class HelloRestController {
    @GetMapping("/")
    @ResponseBody
    public String HelloWorld() {
        return  "Hello World , soy Bryan y estoy aprendiendo Spring Boot!";
    }
    
    
}
