package modeloExcepciones;

import modeloEntrenador.Entrenador;

/**
 * Excepción lanzada cuando un entrenador intenta batallar sin tener pokemones.
 */
public class EntrenadorSinPokemonesException extends Exception {

    private Entrenador entrenador;

    /**
     * Constructor de la excepción.
     * 
     * @param entrenador Entrenador que no posee pokemones.
     */
    public EntrenadorSinPokemonesException(Entrenador entrenador) {
        super("El entrenador " + entrenador.getNombre() + " no tiene pokemones para luchar.");
        this.entrenador = entrenador;
    }

    /**
     * Retorna el entrenador sin pokemones.
     * 
     * @return entrenador sin pokemones.
     */
    public Entrenador getEntrenador() {
        return entrenador;
    }
}
