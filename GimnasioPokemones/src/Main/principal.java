package Main;

import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloMundo.Mundo;
import javax.swing.SwingUtilities;

public class principal {
    public static void main(String[] args) throws EntrenadorSinPokemonesException {
        		SwingUtilities.invokeLater(() -> {
        			new Mundo(false); // false si querés iniciar nuevo torneo
        		});
    }
}
