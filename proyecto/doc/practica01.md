# Práctica 1: Primera aplicación con Spring Boot



**Nombre:** Bryan Salazar 
**Fecha:** 01/05/2025

## Descripción de la aplicación

He desarrollado una aplicación de Spring Boot que implementa un Verificador de Números Pares. Esta aplicación permite al usuario ingresar un número y determina si este es par o impar.

### Funcionalidades implementadas:

- Formulario para ingresar un número  
- Validación de datos de entrada como por ejemplo: el número requerido y rango válido  
- Servicio para verificar si un número es par  
- Página de resultado con información educativa sobre números pares e impares  
 

## Implementación

### Estructura del proyecto

La aplicación está estructurada siguiendo el patrón MVC (Modelo-Vista-Controlador):

- **Modelo:** Clase `NumeroParForm` con validaciones  
- **Vista:** Plantillas Thymeleaf (`numeropar-form.html` y `numeropar-resultado.html`)  
- **Controlador:** `NumeroParController` para manejar las solicitudes HTTP  
- **Servicio:** `NumeroParService` para la lógica de negocio  

### Detalles de los componentes

#### Modelo (`NumeroParForm.java`)

```java
public class NumeroParForm {
    @NotNull(message = "Debe ingresar un número")
    @Min(value = -1000000, message = "El número debe ser mayor o igual a -1.000.000")
    @Max(value = 1000000, message = "El número debe ser menor o igual a 1.000.000")
    private Integer numero;

    // Getters y setters
}
```

El modelo implementa validaciones para asegurar que:

- El número no sea nulo  
- El número esté dentro de un rango razonable (-1.000.000 a 1.000.000)  

#### Servicio (`NumeroParService.java`)

```java
@Service
public class NumeroParService {
    public boolean esPar(int numero) {
        return numero % 2 == 0;
    }

    public String verificarNumeroPar(int numero) {
        if (esPar(numero)) {
            return "El número " + numero + " es par.";
        } else {
            return "El número " + numero + " es impar.";
        }
    }
}
```

El servicio contiene:

- Un método `esPar()` para determinar si un número es par  
- Un método `verificarNumeroPar()` que genera un mensaje descriptivo  

#### Controlador (`NumeroParController.java`)

```java
@Controller
public class NumeroParController {
    @Autowired
    private NumeroParService numeroParService;

    @GetMapping("/numeropar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("numeroParForm", new NumeroParForm());
        return "numeropar-form";
    }

    @PostMapping("/numeropar")
    public String verificarNumeroPar(@Valid NumeroParForm numeroParForm,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "numeropar-form";
        }

        String resultado = numeroParService.verificarNumeroPar(numeroParForm.getNumero());
        model.addAttribute("resultado", resultado);
        model.addAttribute("esPar", numeroParService.esPar(numeroParForm.getNumero()));

        return "numeropar-resultado";
    }
}
```

El controlador:

- Maneja la ruta `/numeropar` para GET (muestra el formulario) y POST (procesa el formulario)  
- Valida los datos del formulario antes de procesarlos  
- Utiliza el servicio para obtener el resultado  
- Pasa los datos necesarios a la vista  

### Vistas (Thymeleaf)

- `numeropar-form.html`: Formulario para ingresar el número con validaciones  
- `numeropar-resultado.html`: Muestra el resultado y ofrece información educativa  

## Tests implementados

### Tests unitarios para el servicio

```java
@Test
public void testEsParConNumeroPar() {
    NumeroParService service = new NumeroParService();
    assertTrue(service.esPar(2));
    assertTrue(service.esPar(0));
    assertTrue(service.esPar(-4));
}

@Test
public void testEsParConNumeroImpar() {
    NumeroParService service = new NumeroParService();
    assertFalse(service.esPar(1));
    assertFalse(service.esPar(-3));
    assertFalse(service.esPar(999));
}

@Test
public void testVerificarNumeroParConPar() {
    NumeroParService service = new NumeroParService();
    assertEquals("El número 2 es par.", service.verificarNumeroPar(2));
}

@Test
public void testVerificarNumeroParConImpar() {
    NumeroParService service = new NumeroParService();
    assertEquals("El número 3 es impar.", service.verificarNumeroPar(3));
}
```

### Tests de integración para el controlador

```java
@WebMvcTest(NumeroParController.class)
public class NumeroParControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumeroParService numeroParService;

    @Test
    public void testMostrarFormulario() throws Exception {
        mockMvc.perform(get("/numeropar"))
            .andExpect(status().isOk())
            .andExpect(view().name("numeropar-form"))
            .andExpect(model().attributeExists("numeroParForm"));
    }

    @Test
    public void testVerificarNumeroParValido() throws Exception {
        when(numeroParService.verificarNumeroPar(42)).thenReturn("El número 42 es par.");
        when(numeroParService.esPar(42)).thenReturn(true);

        mockMvc.perform(post("/numeropar")
                .param("numero", "42"))
            .andExpect(status().isOk())
            .andExpect(view().name("numeropar-resultado"))
            .andExpect(model().attribute("resultado", "El número 42 es par."))
            .andExpect(model().attribute("esPar", true));
    }

    @Test
    public void testVerificarNumeroInvalido() throws Exception {
        mockMvc.perform(post("/numeropar")
                .param("numero", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("numeropar-form"))
            .andExpect(model().hasErrors());
    }
}
```

## Dockerización

Para la dockerización de la aplicación:

### Dockerfile

```dockerfile
FROM openjdk:8-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]
```

### Comandos utilizados

```bash
docker build -t bryanhert/spring-boot-demoapp .
docker run -p 8080:8080 bryanhert/spring-boot-demoapp
docker login
docker push bryanhert/spring-boot-demoapp
```

## Enlaces

- **Repositorio GitHub:**  
  https://github.com/fis-2025-A/practica01-springboot-SalazarBryan13  

- **Docker Hub:**  
  https://hub.docker.com/r/bryanhert/spring-boot-demoapp  
  https://hub.docker.com/repository/docker/bryanhert/spring-boot-demoapp/general  

## Conclusiones

A través del desarrollo de esta aplicación, he podido aprender los conceptos básicos de Spring Boot como:

- La estructura de una aplicación MVC  
- El uso de Thymeleaf para las vistas  
- La implementación de validaciones de formularios  
- La creación de tests unitarios y de integración  
- La dockerización de una aplicación Spring Boot  
