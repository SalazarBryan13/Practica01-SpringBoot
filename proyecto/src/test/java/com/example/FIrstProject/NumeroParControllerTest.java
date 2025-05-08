package com.example.FIrstProject;
import com.example.service.NumeroParService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.controller.NumeroParController;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NumeroParController.class)
class NumeroParControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumeroParService numeroParService;

    @Test
    @DisplayName("Debe mostrar el formulario")
    void testMostrarFormulario() throws Exception {
        mockMvc.perform(get("/numeropar"))
                .andExpect(status().isOk())
                .andExpect(view().name("numeropar-form"))
                .andExpect(model().attributeExists("numeroParForm"));
    }

    @Test
    @DisplayName("Debe rechazar valores vacíos")
    void testRechazarValoresVacios() throws Exception {
        mockMvc.perform(post("/numeropar"))
                .andExpect(status().isOk())
                .andExpect(view().name("numeropar-form"));
    }

    @Test
    @DisplayName("Debe verificar número par correctamente")
    void testVerificarNumeroPar() throws Exception {
        when(numeroParService.esPar(42)).thenReturn(true);
        when(numeroParService.verificarNumeroPar(42)).thenReturn("El número 42 es par.");

        mockMvc.perform(post("/numeropar")
                .param("numero", "42"))
                .andExpect(status().isOk())
                .andExpect(view().name("numeropar-resultado"))
                .andExpect(model().attribute("resultado", "El número 42 es par."))
                .andExpect(model().attribute("esPar", true));
    }

    @Test
    @DisplayName("Debe verificar número impar correctamente")
    void testVerificarNumeroImpar() throws Exception {
        when(numeroParService.esPar(43)).thenReturn(false);
        when(numeroParService.verificarNumeroPar(43)).thenReturn("El número 43 es impar.");

        mockMvc.perform(post("/numeropar")
                .param("numero", "43"))
                .andExpect(status().isOk())
                .andExpect(view().name("numeropar-resultado"))
                .andExpect(model().attribute("resultado", "El número 43 es impar."))
                .andExpect(model().attribute("esPar", false));
    }

    @Test
    @DisplayName("Debe rechazar números fuera de rango")
    void testRechazarNumerosFueraDeRango() throws Exception {
        mockMvc.perform(post("/numeropar")
                .param("numero", "1000001"))
                .andExpect(status().isOk())
                .andExpect(view().name("numeropar-form"));
    }
}
