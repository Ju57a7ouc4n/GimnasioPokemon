package modeloArmas;

import modeloPokemon.Pokemon;

/**
 * Espada: inflige 100 puntos de daño directo.
 */
public class Espada extends Arma {

    public Espada() {
        super(50);
    }

    @Override
    public void atacar (Pokemon adversario) {
        adversario.recibirDanio(100);
    }

    @Override
    public Object clone () {
        Object espada = null;
        try {
            espada = super.clone();
        }
        catch (CloneNotSupportedException e){}
        return espada;
    }

    public String toString() {
        return "Espada{" +
                "costo=" + getCosto() +
                '}';
    }

}