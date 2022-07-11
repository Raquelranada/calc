package raquel.calculadora.api.dto;

import java.util.List;

/**
 * Builder para el DTO {@link OperacionDTO}
 */
public class OperacionDTOBuilder {
    private Operador operador;
    private List<Double> operandos;
    private Double resultado;

    public OperacionDTOBuilder withOperador(Operador operador) {
        this.operador = operador;
        return this;
    }

    public OperacionDTOBuilder withOperandos(List<Double> operandos) {
        this.operandos = operandos;
        return this;
    }

    public OperacionDTOBuilder withResultado(Double resultado) {
        this.resultado = resultado;
        return this;
    }

    public OperacionDTO build() {
        // aquí se pueden añadir las validaciones oportunas antes de crear el nuevo dto
        return new OperacionDTO(operador, operandos, resultado);
    }
}