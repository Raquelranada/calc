package raquel.calculadora.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.service.OperacionRestaService;
import raquel.calculadora.service.OperacionSumaService;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CalculadoraController {

    private final OperacionSumaService sumaService;
    private final OperacionRestaService restaService;

    @Operation(summary = "Calcula la suma de una lista de números ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Operación efectuada correctamente"),
            @ApiResponse(responseCode = "401", description = "Operación no autorizada"),
            @ApiResponse(responseCode = "409", description = "Error en la operación")
    })
    @PostMapping("/sumar")
    public ResponseEntity sumar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Operandos" , required = true,
                    content = @Content(schema = @Schema(implementation = List.class))
            )
            @RequestBody List<Double> sumandos
    ) {
        try {
            OperacionDTO resultado = sumaService.calcular(sumandos);
            return ResponseEntity.ok().body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Calcula la resta de una lista de números ")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Operación efectuada correctamente"),
            @ApiResponse(responseCode = "401", description = "Operación no autorizada"),
            @ApiResponse(responseCode = "409", description = "Error en la operación")
    })
    @PostMapping("/restar")
    public ResponseEntity restar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Operandos" , required = true,
                    content = @Content(schema = @Schema(implementation = List.class))
            )
            @RequestBody List<Double> operandos
    ) {
        try {
            OperacionDTO resultado = restaService.calcular(operandos);
            return ResponseEntity.ok().body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
