package modeloPokemon;

/**
 * Representa un Pokémon de tipo Fuego.
 * Su ataque provoca daño igual al 20% de su fuerza y luego sufre una reducción del 25% en fuerza (mínimo 10).
 * El escudo absorbe el 75% del daño, y la vitalidad el 25%.
 * En la recarga vuelve al 80% de los valores base, más un 5% por cada punto de experiencia.
 */
public class PokemonFuego extends Pokemon {

    public PokemonFuego(String nombre) {
        super(nombre, 0, 200, 530, 80);
    }

    @Override
    public void atacar(Pokemon adversario) {
        double danio = this.fuerza * 0.20;
        adversario.recibirDanio(danio);
        this.fuerza = Math.max(10, this.fuerza * 0.75);
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
        this.escudo = escudoBase * 0.8 + escudoBase * 0.05 * experiencia;
        this.vitalidad = vitalidadBase * 0.8 + vitalidadBase * 0.05 * experiencia;
        this.fuerza = fuerzaBase * 0.8 + fuerzaBase * 0.05 * experiencia;
    }

    @Override
    public PokemonFuego clone() {
        throw new UnsupportedOperationException("Los Pokémon de Fuego no son clonables.");
    }

    @Override
    public double getCosto() {
        return 2000; // ejemplo
    }

	@Override
	public void serHechizadoNiebla() {
		this.fuerza*=.5;
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad*=.5;
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo*=.8;
		this.fuerza*=.8;
	}
}