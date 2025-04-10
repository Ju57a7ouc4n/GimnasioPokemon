package modeloHechizos;
import modeloPokemon.Pokemon;

public class HechizoNiebla extends Hechizo {

	public HechizoNiebla() {
		super();
	}

	@Override
	public void usarHechizo(Pokemon pokemon) {
		pokemon.serHechizadoNiebla();
	}
}
