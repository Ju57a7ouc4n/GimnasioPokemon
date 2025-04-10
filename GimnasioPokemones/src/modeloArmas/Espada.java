package modeloArmas;

import modeloPokemon.Pokemon;

/**
 * Espada: inflige 10 puntos de daño directo.
 */
public class Espada implements ArmaCloneable {

    @Override
    public void atacar(Pokemon adversario) {
        adversario.recibirDanio(10);
    }

    @Override
    public Arma cloneArma() {
        return (Arma) new Espada();
    }
}