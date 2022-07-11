package raquel.calculadora.service;

import org.springframework.stereotype.Service;
import raquel.calculadora.api.ValidacionException;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.api.dto.OperacionDTOBuilder;
import raquel.calculadora.api.dto.Operador;

import java.util.List;

/**
 * Servicio que implementa la operación Resta
 */
@Service
public class OperacionRestaService extends AbstractOperacionService{

    public OperacionRestaService() {
        super(Operador.RESTA);
    }


    /**
     * realiza la operación, validando primero los operandos
     * @param operandos lista de números a los que aplicar la resta
     * @return objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     * @throws ValidacionException excepción con detalle del error en los operandos
     */
    @Override
    public OperacionDTO calcular(List<Double> operandos) throws ValidacionException {
        try{
            validar(operandos);
            // si la lista de operando es válida se realiza el cálculo
            return resta(operandos);
        } catch (ValidacionException e){
            trace(e);
            throw e;
        }
    }

    /**
     * realiza la resta
     * @param operandos lista de números a los que aplicar la resta
     * @return objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     */
    private OperacionDTO resta(List<Double> operandos) {
        Double resultado = operandos.stream().reduce((op1, op2) -> op1 - op2 ).get();

        OperacionDTO operacionDTO = new OperacionDTOBuilder()
                .withOperador(operador)
                .withOperandos(operandos)
                .withResultado(resultado)
                .build();

        trace(operacionDTO);
        return operacionDTO;
    }
}
