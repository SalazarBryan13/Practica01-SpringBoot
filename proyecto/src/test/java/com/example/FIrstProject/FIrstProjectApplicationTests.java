package com.example.FIrstProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.service.SaludoService;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class FIrstProjectApplicationTests {

	@Autowired
	SaludoService saluda;
	@Test
	public void contextLoads() throws Exception {
		// Test para verificar que el contexto de la aplicación se carga correctamente
		assertThat(saluda).isNotNull();
	}

	@Test
	public void serviceSaludo()throws Exception {
		assertThat(saluda.saludo("Juan")).isEqualTo("Hola Juan desde el servicio!");
	}
}
