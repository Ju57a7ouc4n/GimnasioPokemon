package Main;

import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloMundo.Mundo;

public class principal {
    public static void main(String[] args) throws EntrenadorSinPokemonesException {

        Mundo mundo = new Mundo();
        mundo.iniciarJuego(); // Inicia el juego y ejecuta las pruebas de funcionalidad
    }
}
