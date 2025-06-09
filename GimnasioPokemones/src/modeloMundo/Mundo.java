package modeloMundo;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Mercados.Mercado;
import Vista.Ventana_Batallas;
import Vista.Ventana_Gimnasio;
import Vista.Ventana_Inicio;
import Vista.Ventana_Preparar_Entrenadores;
import Vista.Ventana_Tienda;
import modeloArenas.GestorDeArenas;
import modeloArenas.IArena;
import modeloBatalla.Batalla;
import modeloEntrenador.Entrenador;
import modeloExcepciones.CompraImposibleException;
import modeloModificaciones.TextAreaObserver;
import modeloPokemon.Pokemon;
import modeloSerializador.SerializadorInput;
import modeloSerializador.SerializadorOutput;
import modeloTorneo.Torneo;

public class Mundo{

	private Torneo torneo;
	private Mercado mercado;
	private final SerializadorInput cargador = new SerializadorInput();
	private final SerializadorOutput guardador = new SerializadorOutput();

	private Ventana_Inicio ventanaInicio;

	private static final String SAVE_FOLDER = "saves";

	private void crearCarpetaSavesSiNoExiste() {
	    File carpeta = new File(SAVE_FOLDER);
	    if (!carpeta.exists()) {
	        carpeta.mkdir();
	    }
	}
	
	public Mundo() {
		crearCarpetaSavesSiNoExiste();
		this.torneo = new Torneo();
		this.ventanaInicio = new Ventana_Inicio(this);
		this.ventanaInicio.setVisible(true);
		this.mercado= Mercado.getInstance();
	}

	public void cargarPartida(File archivo) {
			try {
				this.torneo = cargador.cargarPartida(archivo, this.torneo);
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error al cargar partida: " + e.getMessage());
				this.torneo = new Torneo(); // fallback
			}
	}

	public void guardarPartida(String nombreArchivo) {
		try {
			 guardador.guardarPartida(torneo,SAVE_FOLDER + "/" + nombreArchivo + ".save");
		} catch (IOException e) {
			System.err.println("Error al cargar partida: " + e.getMessage());
			this.torneo = new Torneo(); // fallback
		}
}
	
	public void agregarEntrenador(String nombre) {
		this.torneo.añadirEntrenador(new Entrenador(nombre));
	}
	
	public void eliminarEntrenador(int index) {
		this.torneo.eliminarEntrenador(index);
	}
	
	public ArrayList<Entrenador> obtenerListaEntrenadores(){
		return this.torneo.getEntrenadores();
	}

	public int obtenerEntrenadores() {
		if(this.torneo.getEntrenadores()==null)
			return 0;
		return this.torneo.getEntrenadores().size();
	}
	
	public Torneo getTorneo() {
		return torneo;
	}

	public void iniciarGimnasio() {
		Ventana_Gimnasio vista = new Ventana_Gimnasio(this);
		torneo.addObserver(vista);
	}
	
	public void iniciarPreparar(Ventana_Preparar_Entrenadores vista) {
		torneo.deleteObservers();
		torneo.addObserver(vista);
	    torneo.forzarActualizacion();
	}
	
	public void iniciarTienda(Ventana_Tienda vista) {
		torneo.deleteObservers();
		torneo.addObserver(vista);
	    torneo.forzarActualizacion();
	}
	
	public void iniciarBatallas(Ventana_Batallas vista) {
		torneo.deleteObservers();
		torneo.addObserver(vista);
	    torneo.forzarActualizacion();
	}
	
	public void reiniciarGimnasio(Ventana_Gimnasio vista){
		torneo.deleteObservers();
		torneo.addObserver(vista);
		torneo.forzarActualizacion();
	}
	
	public void comprarPokemon(String entrenador, String tipo, String nombre) throws CompraImposibleException{
		Entrenador ref=torneo.getEntrenador(entrenador);
		if(ref!=null) {
			mercado.compraPokemon(ref, tipo, nombre);
		}
		torneo.forzarActualizacion();
	}
	
	public void comprarArma(String entrenador, String tipo) throws CompraImposibleException{
		Entrenador ref=torneo.getEntrenador(entrenador);
		if(ref!=null)
			mercado.compraArma(ref, tipo);
		torneo.forzarActualizacion();
	}
	
	public void forzarActualizacion(){
		torneo.forzarActualizacion();
	}
	
	public void iniciarRonda(ArrayList<TextAreaObserver> vista) throws InterruptedException{
		this.torneo.iniciarRonda(vista);
	}
	
	public void prepararEntrenador(String entrenador, String pokemon){
		Entrenador ref= torneo.getEntrenador(entrenador);
		ref.elegirPokemonCombate(pokemon);
	}
	
	public java.util.List<String> getNombresEntrenadores() {
	    return torneo.getEntrenadores()
	                 .stream()
	                 .map(Entrenador::getNombre)
	                 .collect(Collectors.toList());
	}

	public java.util.List<String> getPokemonesDelEntrenador(int index) {
	    return torneo.getEntrenadores().get(index)
	                 .getPokemones()
	                 .stream()
	                 .map(Pokemon::getNombre)
	                 .collect(Collectors.toList());
	}

	public java.util.List<String> getPokemonesParaCombateDelEntrenador(int index) {
	    ArrayList<Pokemon> pokemonesCombate = torneo.getEntrenadores().get(index).getPokemonesCombate();
	    if (pokemonesCombate == null) {
	        return new ArrayList<>(); // o Collections.emptyList();
	    }
	    return pokemonesCombate.stream()
	                           .map(Pokemon::getNombre)
	                           .collect(Collectors.toList());
	}
	
	public void anadirPokemonACombate(int indexEntrenador, int indexPokemon) {
		this.torneo.anadirPokemonACombate(indexEntrenador, indexPokemon);
	}

	public void quitarPokemonDeCombate(int indexEntrenador, int indexPokemon) {
		this.torneo.quitarPokemonDeCombate(indexEntrenador, indexPokemon);
	}
	
	public void crearArena(String nombre, String tipo, String dificultad){
		this.torneo.getGestorDeArenas().nuevaArena(tipo, dificultad, nombre);
		
	}
	
	public java.util.List<IArena> getArenas(){
		return GestorDeArenas.getInstance().getArenas();
	} 
}

