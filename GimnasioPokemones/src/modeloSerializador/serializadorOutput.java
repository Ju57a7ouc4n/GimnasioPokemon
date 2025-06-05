package modeloSerializador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modeloArenas.IArena;
import modeloEntrenador.Entrenador;
import modeloPokemon.Pokemon;

public class serializadorOutput {
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
	
	public void escribirPokemon(Pokemon pokemon) throws IOException{
		if(output!=null)
			output.writeObject(pokemon);
	}
	
	public void escribirEntrenador(Entrenador entrenador) throws IOException{
		if(output!=null)
			output.writeObject(entrenador);
	}
	
	public void escribirBatalla(IArena arena) throws IOException{
		if(output!=null)
			output.writeObject(arena);
	}
}
