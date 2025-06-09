package modeloTorneo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Mercados.Mercado;
import modeloArenas.GestorDeArenas;
import modeloBatalla.Batalla;
import modeloEntrenador.Entrenador;
import modeloPokemon.Pokemon;

import java.io.Serializable;

public class Torneo extends Observable implements Serializable{
	protected ArrayList<Entrenador> entrenadores;
	protected GestorDeArenas gestorDeArenas;
	protected Mercado tienda;
	public Entrenador ganadorDelTorneo=null;
	
	public Torneo(){
		tienda = Mercado.getInstance();
		gestorDeArenas = GestorDeArenas.getInstance();
		this.entrenadores = new ArrayList<Entrenador>(); 
	}
	public Torneo getTorneo(){
		return this;
	}
	
	@Override
	public synchronized void addObserver(Observer arg0) {
		super.addObserver(arg0);
	}

	@Override
	protected synchronized void clearChanged() {
		super.clearChanged();
	}

	@Override
	public synchronized int countObservers() {
		return super.countObservers();
	}

	@Override
	public synchronized void deleteObserver(Observer arg0) {
		super.deleteObserver(arg0);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public synchronized boolean hasChanged() {
		return super.hasChanged();
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg0) {
		super.notifyObservers(arg0);
	}

	@Override
	public synchronized void setChanged() {
		super.setChanged();
	}

	public GestorDeArenas getGestorDeArenas(){
		return this.gestorDeArenas;
	}

	public ArrayList<Entrenador> getEntrenadores() {
		return this.entrenadores;
	}

	public Entrenador getEntrenador(String nombre) {
		int i=0;
		while(entrenadores.get(i)!=null && i<entrenadores.size() && !(entrenadores.get(i).getNombre().equals(nombre)))
			i++;
		if(entrenadores.get(i).getNombre().equals(nombre))
			return entrenadores.get(i);
		else
			return null;
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
	    	for (int i = 0; i < entrenadores.size(); i += 2) {
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
	
    public void añadirEntrenador(Entrenador e) {
        entrenadores.add(e);
        setChanged();
        notifyObservers(entrenadores);
    }
	
    public void eliminarEntrenador(int index) {
        entrenadores.remove(index);
        setChanged();
        notifyObservers(entrenadores);
    }
    
    public void forzarActualizacion(){
    	setChanged();
        notifyObservers(entrenadores);
    }
    
	public void anadirPokemonACombate(int indexEntrenador, int indexPokemon) {
	    Entrenador entrenador = this.getEntrenadores().get(indexEntrenador);
	    Pokemon p = entrenador.getPokemones().get(indexPokemon);
	    
	    if (!entrenador.getPokemonesCombate().contains(p)) {
	        entrenador.getPokemonesCombate().add(p);
	        entrenador.getPokemones().remove(indexPokemon);
	        this.setChanged();
	        this.notifyObservers();
	    }
	}

	public void quitarPokemonDeCombate(int indexEntrenador, int indexPokemon) {
	    Entrenador entrenador = this.getEntrenadores().get(indexEntrenador);
	    Pokemon p = entrenador.getPokemonesCombate().get(indexPokemon);
	    
	    entrenador.getPokemonesCombate().remove(indexPokemon);
	    entrenador.getPokemones().add(p);
	    this.setChanged();
	    this.notifyObservers();
	}
	
}
