package modeloMundo;

import modeloSerializador.SerializadorInput;
import modeloSerializador.SerializadorOutput;
import modeloTorneo.Torneo;

public class Mundo {
	Torneo torneo = null;
	SerializadorInput cargadador = new SerializadorInput();
	SerializadorOutput guardador = new SerializadorOutput();
	
	public void iniciarMundo(){
		if(PASA ALGO EN LA VENTANA)
			this.torneo= new Torneo();
		else
			this.torneo= cargadador.cargarPartida();
	}
}

