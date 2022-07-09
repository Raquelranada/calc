package raquel.calculadora.api.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OperacionDTO {

    private Operador operador;

    private List<Double> operandos;

    private Double resultado;

    @Override
    public String toString() {
        if(CollectionUtils.isEmpty(operandos) && operador == null) {
            return String.format("El operador no está definido ni tiene operandos");
        }
        else if(CollectionUtils.isEmpty(operandos)) {
            return String.format("La operación %s no tiene operandos", operador.text);
        } else if (operador == null) {
            return String.format("El operador no está definido");
        } else if (resultado == null) {
            return String.format("No se ha podido calcular el resultado");
        } else {
            String operacion = operandos.stream()
                    .filter(op -> op != null)
                    .map(op -> op.toString())
                    .collect(Collectors.joining(" " + operador.text + " "));

            operacion = String.join(" ",operacion, " = ", resultado.toString());
            return  operacion;
        }

    }


    public enum Operador {
        SUMA("+"),
        RESTA("-");

        private final String text;

        /**
         * @param text
         */
        Operador(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    } //AMPLIAR OPERACIONES A FUTURO

}
