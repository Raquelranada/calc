package raquel.calculadora.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.api.dto.OperacionDTOBuilder;
import raquel.calculadora.api.dto.Operador;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculadoraControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void sumarTestKONoAuthorised(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<Double>> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/calculadora/sumar", request, String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
    }

    @Test
    public void restarTestKONoAuthorised(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<Double>> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/calculadora/restar", request, String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
    }

    @Test
    public void sumarTestOK(){
        OperacionDTO operacionDTO = new OperacionDTOBuilder()
                .withOperador(Operador.SUMA)
                .withOperandos(Arrays.asList(1.0, 2.7))
                .withResultado(3.7)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("test","pass");
        HttpEntity<List<Double>> request = new HttpEntity<>(Arrays.asList(1.0, 2.7), headers);
        ResponseEntity<OperacionDTO> response = restTemplate.postForEntity("/calculadora/sumar", request, OperacionDTO.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(operacionDTO, response.getBody());

    }

    @Test
    public void restarTestOK(){
        OperacionDTO operacionDTO = new OperacionDTOBuilder()
                .withOperador(Operador.RESTA)
                .withOperandos(Arrays.asList(10.0, 1.6))
                .withResultado(8.4)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("test","pass");
        HttpEntity<List<Double>> request = new HttpEntity<>(Arrays.asList(10.0, 1.6), headers);
        ResponseEntity<OperacionDTO> response = restTemplate.postForEntity("/calculadora/restar", request, OperacionDTO.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(operacionDTO, response.getBody());

    }

    @Test
    public void sumarTestKO(){

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("test","pass");
        HttpEntity<List<Double>> request = new HttpEntity<>(Arrays.asList(null, 2.7), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/calculadora/sumar", request, String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        assertEquals("Algún operando es nulo. La operación no soporta nulos", response.getBody());

    }

    @Test
    public void restarTestKO(){

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("test","pass");
        HttpEntity<List<Double>> request = new HttpEntity<>(Arrays.asList(),  headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/calculadora/restar", request, String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        assertEquals("La lista de operandos no puede estar vacía", response.getBody());


    }
}
