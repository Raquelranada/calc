package raquel.calculadora.api;

import io.corp.calculator.TracerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    private TracerImpl tracer = new TracerImpl();

    @Operation(summary = "Calcula la suma de dos números ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Operación efectuada correctamente"),
            @ApiResponse(responseCode = "401", description = "Operación no autorizada"),
            @ApiResponse(responseCode = "409", description = "Error en la operación")
    })
    @GetMapping("/sumar")
    public Integer sumar() {

        tracer.trace(1);
        return 1;
    }
}
