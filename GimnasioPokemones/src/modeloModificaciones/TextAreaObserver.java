package modeloModificaciones;

import java.util.Observer;
import java.util.Observable;
import javax.swing.*;

public class TextAreaObserver implements Observer {
    private final JTextArea textArea;

    public TextAreaObserver(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String mensaje) {
            SwingUtilities.invokeLater(() -> textArea.append(mensaje));
        }
    }
}
