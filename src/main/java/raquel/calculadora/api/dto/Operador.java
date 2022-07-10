package raquel.calculadora.api.dto;

public enum Operador {
    SUMA("+"),
    RESTA("-");

    public final String text;

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
