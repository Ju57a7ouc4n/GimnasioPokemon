package modeloHechizos;
import modeloPokemon.Pokemon;

public class HechizoViento extends Hechizo{
	
	public HechizoViento() {
		super();
	}

	@Override
	public void usarHechizo(Pokemon pokemon) {
		pokemon.serHechizadoViento();
	}	
}
