package raquel.calculadora.service;

import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import raquel.calculadora.api.ValidacionException;
import raquel.calculadora.api.dto.OperacionDTO;
import raquel.calculadora.api.dto.Operador;

import java.util.List;

/**
 * Clase abstracta que implementa los métodos de validación
 * de los operandos, el log de las excepciones y el log de
 * los resultados de las operaciones
 */
public abstract class AbstractOperacionService {

    //traceador
    @Autowired
    private TracerImpl tracer;
    //operación soportada
    protected final Operador operador;

    /**
     * Inicializa el servicio instanciando un objeto de la clase TracerImpl
     * y seteando el tipo de operación soportada
     * @param operador operación soportada por la implementación
     */
    public AbstractOperacionService(Operador operador) {
        this.operador = operador;
    }

    /**
     * método abstracto que deben implementar las clases que extiendan de ésta
     * @param operandos lista de números a los que aplicar la operación
     * @return objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     * @throws ValidacionException excepción con detalle del error en los operandos
     */
    public abstract OperacionDTO calcular (List<Double> operandos) throws ValidacionException;

    /**
     * valida la lista de operandos comprobando que:
     *  - no esté vacía
     *  - no contenga nulos
     * @param operandos lista de números a validar
     * @throws ValidacionException excepción custom con mensaje del error
     */
    protected void validar (List<Double> operandos) throws ValidacionException {
        if(CollectionUtils.isEmpty(operandos)){
            throw new ValidacionException("La lista de operandos no puede estar vacía");
        } else {
            boolean contieneNulos = operandos.stream().anyMatch(op -> op == null);
            if(contieneNulos){
                throw new ValidacionException("Algún operando es nulo. La operación no soporta nulos");
            }
        }
    }

    /**
     * Tracea una operación
     * @param operacionDTO objeto {@link OperacionDTO} que detalla la operación,los operandos y el resultado
     */
    protected void trace(OperacionDTO operacionDTO) {
        tracer.trace(operacionDTO);
    }

    /**
     * Tracea una excepción de validación
     * @param excepcion excepción {@link ValidacionException } a tracear
     */
    protected void trace(ValidacionException excepcion) {
        tracer.trace("Error: " + excepcion.getMessage());
    }
}
