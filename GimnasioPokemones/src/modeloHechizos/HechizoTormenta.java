package modeloHechizos;
import modeloPokemon.Pokemon;

public class HechizoTormenta extends Hechizo{
	
	public HechizoTormenta() {
		super();
	}

	@Override
	public void usarHechizo(Pokemon pokemon) {
		pokemon.serHechizadoTormenta();
	}	
}
