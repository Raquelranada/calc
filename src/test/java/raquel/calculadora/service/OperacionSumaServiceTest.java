package raquel.calculadora.service;

import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raquel.calculadora.api.ValidacionException;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.api.dto.OperacionDTOBuilder;
import raquel.calculadora.api.dto.Operador;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperacionSumaServiceTest {

    @Mock
    TracerImpl tracer;

    @InjectMocks
    OperacionSumaService sumaService;

    @Test
    void calcular_ExcepcionesValidacion() {
        assertThrows(ValidacionException.class, () -> {sumaService.calcular(Arrays.asList(null, 2.7));});
        assertThrows(ValidacionException.class, () -> {sumaService.calcular(null);});
    }

    @Test
    void calcular(){
        OperacionDTO suma = sumaService.calcular(Arrays.asList(1.0, 2.7));
        OperacionDTO operacionDTO = new OperacionDTOBuilder()
                .withOperador(Operador.SUMA)
                .withOperandos(Arrays.asList(1.0, 2.7))
                .withResultado(3.7)
                .build();

        assertEquals(suma, operacionDTO);
        verify(tracer, atLeastOnce()).trace(any(OperacionDTO.class));
        verify(tracer, never()).trace(any(ValidacionException.class));
    }
}