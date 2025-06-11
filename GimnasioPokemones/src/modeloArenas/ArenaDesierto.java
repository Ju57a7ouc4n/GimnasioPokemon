package modeloArenas;

import java.io.Serializable;

public class ArenaDesierto extends Arena implements Serializable {

    public ArenaDesierto(String nombre) {
        super(nombre);
        this.premio = 1000;
    }

	@Override
	public String getDetalle() {
		return "Un campo abierto rodeado de dunas móviles y piedras secas. El calor es sofocante, y el viento levanta pequeñas tormentas de arena que dificultan la visibilidad. El suelo es áspero, y cada paso exige resistencia.\n";
	}
    
    
}
