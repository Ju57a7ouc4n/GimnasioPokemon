package modeloPokemon;

import modeloArmas.Arma;
import modeloArmas.ArmaCloneable;

/**
 * Representa un Pokémon de tipo Piedra.
 */
public class PokemonPiedra extends Pokemon {

    private Arma arma;

    public PokemonPiedra(String nombre) {
        super(nombre, 0, 300, 600, 150);
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void atacar(Pokemon adversario) {
        if (arma != null) {
            arma.atacar(adversario);
        } else {
            double danio = this.fuerza * 0.15;
            adversario.recibirDanio(danio);
            this.fuerza *= 0.95;
        }
    }

    @Override
    public void recibirDanio(double danio) {
        if (escudo > 0) {
            escudo -= danio * 0.75;
            vitalidad -= danio * 0.25;
        } else {
            vitalidad -= danio;
        }
    }

    @Override
    public void recargar() {
        escudo = Math.min(escudoBase * 2, escudoBase * 0.8 + escudoBase * 0.1 * experiencia);
        vitalidad = Math.min(vitalidadBase * 2, vitalidadBase * 0.8 + vitalidadBase * 0.1 * experiencia);
        fuerza = Math.min(fuerzaBase * 2, fuerzaBase * 0.8 + fuerzaBase * 0.1 * experiencia);
    }

    @Override
    public PokemonPiedra clone() {
        if (arma != null && !(arma instanceof ArmaCloneable)) {
            throw new UnsupportedOperationException("Este Pokémon de Piedra no es clonable por su arma.");
        }
        PokemonPiedra copia = new PokemonPiedra(this.nombre);
        if (arma instanceof ArmaCloneable) {
            copia.setArma(((ArmaCloneable) arma).cloneArma());
        }
        return copia;
    }

    @Override
    public double getCosto() {
        return 2500; // ejemplo
    }

	@Override
	public void serHechizadoNiebla() {
		this.fuerza*=.4;
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad*=.25;
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo=0;
		this.fuerza*=.7;
	}
}