package modeloArmas;

import modeloPokemon.Pokemon;

/**
 * Hacha: inflige al azar entre 50 y 150 puntos de daño directo.
 */
public class Hacha extends Arma {

    public Hacha() {
        super(80); // Costo de 50
    }

    @Override
    public void atacar (Pokemon adversario) {

        int danio = (int) (Math.random() * 101) + 50; // Genera un daño entre 50 y 150

        adversario.recibirDanio(danio);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }

    public String toString() {
        return "Hacha{" +
                "costo=" + getCosto() +
                '}';
    }
}