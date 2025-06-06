package modeloSerializador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Mercados.Mercado;
import modeloArenas.GestorDeArenas;
import modeloPokemon.Pokemon;
import modeloTorneo.Torneo;

public class SerializadorInput {
	private FileInputStream file;
    private ObjectInputStream input;
    
    public void abrir() throws IOException{
    	file = new FileInputStream("Simulador.ser");
    	input = new ObjectInputStream(file);
    }
    
    public void cerrar() throws IOException{
    	if(input != null)
    		input.close();
    }
    
    public Object leerPokemon() throws IOException, ClassNotFoundException{
    	Pokemon pokemon = null;
    	if(input != null) {
    		try {
    			pokemon = (Pokemon) input.readObject();
    		}
    		catch(EOFException e){}
    	}
    	return pokemon;
    }
    
    public Object leerTorneo() throws IOException, ClassNotFoundException{
    	Torneo torneo = null;
    	if(input != null) {
    		try {
    			torneo = (Torneo) input.readObject();
    		}
    		catch(EOFException e){}
    	}
    	return torneo;
    }
    
    public Torneo cargarPartida() throws IOException, ClassNotFoundException{
    	Torneo viejoTorneo = null;
    	Torneo nuevoTorneo = new Torneo();		
    	
    	abrir();
    	
    	if(input!=null) {
    		viejoTorneo=(Torneo) leerTorneo();
    		GestorDeArenas.setInstancia(viejoTorneo.getGestorDeArenas());
    		Mercado.setInstancia(viejoTorneo.getTienda());
    		nuevoTorneo.setEntrenadores(viejoTorneo.getEntrenadores());
    	}
    	
    	cerrar();
    	
    	return nuevoTorneo;
    }
    
}