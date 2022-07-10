package raquel.calculadora.service;

import org.springframework.stereotype.Service;
import raquel.calculadora.api.ValidacionException;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.api.dto.Operador;

import java.util.List;

/**
 * Servicio que implementa la operación Suma
 */
@Service
public class OperacionSumaService extends AbstractOperacionService{

    public OperacionSumaService() {
        super(Operador.SUMA);
    }

    /**
     * realiza la operación, validando primero los operandos
     * (muy simple, pero podría realizar validaciones específicas de los operandos,por ejemplo)
     * @param operandos lista de números a los que aplicar la suma
     * @return objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     * @throws ValidacionException excepción con detalle del error en los operandos
     */
    @Override
    public OperacionDTO calcular(List<Double> operandos) throws ValidacionException {
        try{
            validar(operandos);
            // si la lista de operando es válida se realiza el cálculo
            return suma(operandos);
        } catch (ValidacionException e){
            trace(e);
            throw e;
        }
    }

    /**
     * realiza la suma
     * @param operandos lista de números a los que aplicar la resta
     * @return objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     */
    private OperacionDTO suma(List<Double> operandos) {
        Double resultado = operandos.stream().reduce(0.0, Double::sum);

        OperacionDTO operacionDTO = new OperacionDTO();
        operacionDTO.setOperador(operador);
        operacionDTO.setOperandos(operandos);
        operacionDTO.setResultado(resultado);

        trace(operacionDTO);
        return operacionDTO;
    }
}
