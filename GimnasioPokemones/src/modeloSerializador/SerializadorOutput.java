package modeloSerializador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modeloArenas.GestorDeArenas;
import modeloArenas.IArena;
import modeloEntrenador.Entrenador;
import modeloPokemon.Pokemon;
import modeloTorneo.Torneo;

public class SerializadorOutput {
	private FileOutputStream file;
	private ObjectOutputStream output;
	
	public void abrir() throws IOException{
		file = new FileOutputStream("Simulador.ser");
		output = new ObjectOutputStream(file);
		
	}
	
	public void cerrar() throws IOException{
		if(output != null)
			output.close();
	}
	
	public void escribirTorneo(Torneo torneo) throws IOException{
		if(output!=null)
			output.writeObject(torneo);
	}
	
	void guardarPartida(Torneo torneo) throws IOException{
		
		abrir();
		
		if(output != null) {
			escribirTorneo(torneo);
		}
		
		cerrar();
	}
	
}
