package raquel.calculadora;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raquel.calculadora.api.CalculadoraController;
import raquel.calculadora.api.dto.OperacionDTO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CalculadoraApplicationTests {

	@Autowired
	private CalculadoraController calculadoraController;

	@Test
	void contextLoads() {
		assertNotNull(calculadoraController);
	}

	@Test
	public void main() {
		CalculadoraApplication.main(new String[] {});
	}


	@Test
	void controllerTest() {
		ResponseEntity respuesta = calculadoraController.sumar(Arrays.asList(1.2, 2.0));
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertEquals(3.2, ((OperacionDTO) respuesta.getBody()).getResultado());

		ResponseEntity respuesta2 = calculadoraController.restar(Arrays.asList(1.2, 0.2));
		assertEquals(HttpStatus.OK, respuesta2.getStatusCode());
		assertEquals(1.0, ((OperacionDTO) respuesta2.getBody()).getResultado());

	}
}
