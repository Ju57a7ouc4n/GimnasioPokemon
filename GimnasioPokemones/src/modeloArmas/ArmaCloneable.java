package modeloArmas;

import modeloPokemon.Pokemon;

public interface ArmaCloneable {
	Arma cloneArma();

	void atacar(Pokemon adversario);
}