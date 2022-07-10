package raquel.calculadora.api;

/**
 * Excepción custom usada en la validación de los operadores
 */
public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
