package Vista;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Icono_Ventana {

    public static Image getIconoVentana() {
        return new ImageIcon(Icono_Ventana.class.getResource("Images/Imagen_Ventana_Inicio.png")).getImage();
    }
}