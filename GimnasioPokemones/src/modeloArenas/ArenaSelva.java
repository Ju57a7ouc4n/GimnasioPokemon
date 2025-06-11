package modeloArenas;

import java.io.Serializable;

public class ArenaSelva extends Arena implements Serializable {

    public ArenaSelva(String nombre) {
        super(nombre);
        this.premio = 800;
    }

	@Override
	public String getDetalle() {
		return "Una densa jungla tropical donde el combate se desarrolla entre raíces gruesas, niebla húmeda y vegetación abundante. La humedad lo impregna todo y los rugidos de bestias lejanas intimidan incluso a los más valientes.\n";
	}
    
    
}
