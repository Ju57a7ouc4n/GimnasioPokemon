package modeloArmas;

import modeloPokemon.Pokemon;

/**
 * Hacha: inflige 20 puntos de daño directo.
 */
public class Hacha implements ArmaCloneable {

    @Override
    public void atacar(Pokemon adversario) {
        adversario.recibirDanio(20);
    }

    @Override
    public Arma cloneArma() {
        return (Arma) new Hacha();
    }
}