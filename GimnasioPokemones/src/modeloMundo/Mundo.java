package modeloMundo;

import java.io.IOException;
import java.util.List;

import Vista.Ventana_Gimnasio;
import Vista.Ventana_Inicio;
import modeloEntrenador.Entrenador;
import modeloSerializador.SerializadorInput;
import modeloSerializador.SerializadorOutput;
import modeloTorneo.Torneo;

public class Mundo {

	private Torneo torneo;
	private final SerializadorInput cargador = new SerializadorInput();
	private final SerializadorOutput guardador = new SerializadorOutput();

	private Ventana_Inicio ventanaInicio;

	public Mundo(boolean cargarPartida) {
		iniciarMundo(cargarPartida);
		this.ventanaInicio = new Ventana_Inicio(this);
		this.ventanaInicio.setVisible(true);
	}

	public void iniciarMundo(boolean cargarPartida) {
		if (cargarPartida) {
			try {
				this.torneo = cargador.cargarPartida();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error al cargar partida: " + e.getMessage());
				this.torneo = new Torneo(); // fallback
			}
		} else {
			this.torneo = new Torneo();
		}
	}

	public void agregarEntrenador(Entrenador e) {
		this.torneo.getEntrenadores().add(e);
		torneo.setChanged(); // para que update() en la vista se dispare
		torneo.notifyObservers(torneo.getEntrenadores());
	}

	public List<Entrenador> obtenerEntrenadores() {
		return this.torneo.getEntrenadores();
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void iniciarGimnasio() {
		new Ventana_Gimnasio(); // aquí podrías pasar el modelo si hace falta
	}
}

