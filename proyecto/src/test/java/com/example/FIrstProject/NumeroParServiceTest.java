package com.example.FIrstProject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.service.NumeroParService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumeroParServiceTest {

    private final NumeroParService numeroParService = new NumeroParService();

    @Test
    @DisplayName("Verificar que 0 es par")
    void testCeroesPar() {
        assertTrue(numeroParService.esPar(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100, 1000, -2, -4, -6})
    @DisplayName("Verificar que números pares devuelven true")
    void testNumerosPares(int numero) {
        assertTrue(numeroParService.esPar(numero));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 99, 1001, -1, -3, -5})
    @DisplayName("Verificar que números impares devuelven false")
    void testNumerosImpares(int numero) {
        assertFalse(numeroParService.esPar(numero));
    }

    @Test
    @DisplayName("Verificar mensaje para número par")
    void testMensajeNumeroPar() {
        String mensaje = numeroParService.verificarNumeroPar(42);
        assertThat(mensaje).contains("42").contains("es par");
    }

    @Test
    @DisplayName("Verificar mensaje para número impar")
    void testMensajeNumeroImpar() {
        String mensaje = numeroParService.verificarNumeroPar(43);
        assertThat(mensaje).contains("43").contains("es impar");
    }
}