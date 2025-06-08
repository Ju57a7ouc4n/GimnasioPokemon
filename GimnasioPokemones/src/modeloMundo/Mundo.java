package modeloMundo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Mercados.Mercado;
import Vista.Ventana_Gimnasio;
import Vista.Ventana_Inicio;
import modeloEntrenador.Entrenador;
import modeloExcepciones.CompraImposibleException;
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
				this.torneo = cargador.cargarPartida(archivo);
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
	
	public void reiniciarGimnasio(Ventana_Gimnasio vista){
		torneo.deleteObservers();
		torneo.addObserver(vista);
		torneo.forzarActualizacion();
	}
	
	public void comprarPokemon(String entrenador, String tipo, String nombre) throws CompraImposibleException{
		Entrenador ref=torneo.getEntrenador(entrenador);
		if(ref!=null)
			mercado.compraPokemon(ref, tipo, nombre);
		
	}
	
	public void forzarActualizacion(){
		torneo.forzarActualizacion();
	}
}

