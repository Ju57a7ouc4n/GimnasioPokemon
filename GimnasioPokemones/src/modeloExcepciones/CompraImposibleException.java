package modeloExcepciones;

/**
 * Excepción lanzada cuando un entrenador intenta comprar un Pokémon y no tiene
 * créditos suficientes.
 */
public class CompraImposibleException extends Exception {

    private double creditosDisponibles;
    private double costoPokemon;

    /**
     * Constructor de la excepción.
     * 
     * @param creditosDisponibles Créditos que posee el entrenador.
     * @param costoPokemon        Costo del Pokémon que desea comprar.
     */
    public CompraImposibleException(double creditosDisponibles, double costoPokemon) {
        super("No se puede comprar el Pokémon. Créditos: " + creditosDisponibles + ", Costo: " + costoPokemon);
        this.creditosDisponibles = creditosDisponibles;
        this.costoPokemon = costoPokemon;
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
        return costoPokemon;
    }
}
