package modeloExcepciones;

/**
 * Excepción lanzada cuando un entrenador intenta comprar un Pokémon y no tiene
 * créditos suficientes.
 */
public class CompraImposibleException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5774629654707624036L;
	private double creditosDisponibles;
    private double costo;

    /**
     * Constructor de la excepción.
     * 
     * @param creditosDisponibles Créditos que posee el entrenador.
     * @param costoPokemon        Costo del Pokémon que desea comprar.
     */
    public CompraImposibleException(double creditosDisponibles, double costo, String mensaje) {
        super("Compra imposible: creditos disponibles " + creditosDisponibles + " Costo " + mensaje + " " + costo);
        this.creditosDisponibles = creditosDisponibles;
        this.costo = costo;
    }

    /**
     * Retorna los créditos disponibles del entrenador.
     * 
     * @return créditos disponibles.
     */
    public double getCreditosDisponibles() {
        return creditosDisponibles;
    }

    /**
     * Retorna el costo del Pokémon que se intentó comprar.
     * 
     * @return costo del Pokémon.
     */
    public double getCostoPokemon() {
        return costo;
    }
}
