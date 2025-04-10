package modeloHechizos;
import modeloPokemon.Pokemon;

public abstract class Hechizo {
	/**
	 * Clase abstracta para poder generar las cartas de hechizo
	 */	
	public Hechizo() {
		super();
	}
	
	public abstract void usarHechizo(Pokemon pokemon);
}