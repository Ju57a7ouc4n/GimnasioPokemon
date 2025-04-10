package modeloInterfaces;
import modeloPokemon.Pokemon;

public interface Hostil {
	/**
	 * Ejecuta un ataque contra otro pokemon
	 * @param adversario el pokemon que recibe el ataque
	 */
	void atacar(Pokemon adversario);
}
