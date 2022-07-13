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
class OperacionRestaServiceTest {

    @Mock
    TracerImpl tracer;

    @InjectMocks
    OperacionRestaService restaService;

    @Test
    void calcular_ExcepcionesValidacion() {
        assertThrows(ValidacionException.class, () -> {
            restaService.calcular(Arrays.asList(null, 212.1));});
        assertThrows(ValidacionException.class, () -> {
            restaService.calcular(null);});
    }

    @Test
    void calcular(){
        OperacionDTO resta = restaService.calcular(Arrays.asList(10.0, 2.7));
        OperacionDTO operacionDTO = new OperacionDTOBuilder()
                .withOperador(Operador.RESTA)
                .withOperandos(Arrays.asList(10.0, 2.7))
                .withResultado(7.3)
                .build();

        assertEquals(resta, operacionDTO);
        verify(tracer, atLeastOnce()).trace(any(OperacionDTO.class));
        verify(tracer, never()).trace(any(ValidacionException.class));
    }
}