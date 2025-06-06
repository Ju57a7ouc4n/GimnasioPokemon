package modeloTorneo;

import java.util.ArrayList;

import Mercados.Mercado;
import modeloArenas.GestorDeArenas;
import modeloBatalla.Batalla;
import modeloEntrenador.Entrenador;
import java.io.Serializable;

public class Torneo implements Serializable {
	protected ArrayList<Entrenador> entrenadores;
	protected GestorDeArenas gestorDeArenas;
	protected Mercado tienda;
	public Entrenador ganadorDelTorneo=null;
	
	public Torneo(){
		tienda = Mercado.getInstance();
		gestorDeArenas = GestorDeArenas.getInstance();
		/*
		 * A PARTIR DE LA VENTANA ACA SE AÑADEN LOS ENTRENADORES
		 * */
	}
	
	public GestorDeArenas getGestorDeArenas(){
		return this.gestorDeArenas;
	}

	public ArrayList<Entrenador> getEntrenadores() {
		return this.entrenadores;
	}

	public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
		this.entrenadores = entrenadores;
	}

	public Mercado getTienda() {
		return this.tienda;
	}
	
	public void iniciarRonda() throws InterruptedException {
	    if(this.entrenadores.size()>1){
	    	ArrayList<Batalla> batallas = new ArrayList<>();
	    	ArrayList<Entrenador> ganadores = new ArrayList<>();

	    	for (int i = 0; i < this.entrenadores.size(); i += 2) {
	            Batalla batalla = new Batalla(this.entrenadores.get(i), this.entrenadores.get(i + 1), gestorDeArenas);
	            batallas.add(batalla);
	            batalla.start();
	    	}

	    	for (Batalla batalla : batallas) {
	    		batalla.join();
	    		ganadores.add(batalla.ganador);
	    	}

	    	this.entrenadores.clear();
	    	this.entrenadores.addAll(ganadores);
	    }
	    else
	    	this.ganadorDelTorneo= this.entrenadores.get(0);
	}
	
}
