package raquel.calculadora.api.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperacionDTOTest {
    private static String sinDefinir = "El operador no está definido ni tiene operandos";
    private static String restaSinOperandos = "La operación - no tiene operandos";
    private static String sinOperador = "El operador no está definido";
    private static String sinResultado = "No se ha podido calcular el resultado";

    @Test
    public void testToString(){

        OperacionDTO operacionDTO = new OperacionDTOBuilder().build();
        assertEquals(sinDefinir, operacionDTO.toString());

        operacionDTO.setOperador(Operador.RESTA);
        assertEquals(restaSinOperandos, operacionDTO.toString());

        operacionDTO.setOperador(null);
        operacionDTO.setOperandos(Arrays.asList(2.0, 3.5));
        assertEquals(sinOperador, operacionDTO.toString());

        operacionDTO.setOperador(Operador.SUMA);
        operacionDTO.setOperandos(Arrays.asList(2.0, 3.5));
        assertEquals(sinResultado, operacionDTO.toString());

        operacionDTO.setOperador(Operador.SUMA);
        operacionDTO.setOperandos(Arrays.asList(2.0, 3.5));
        operacionDTO.setResultado(5.5);
        assertEquals("2.0 + 3.5  =  5.5", operacionDTO.toString());
    }
}
