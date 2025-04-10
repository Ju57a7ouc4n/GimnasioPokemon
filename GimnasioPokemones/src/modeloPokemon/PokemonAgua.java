package modeloPokemon;

/**
 * Representa un Pokémon de tipo Agua.
 * El ataque causa daño equivalente al 10% de la vitalidad actual del Pokémon.
 * Su defensa ignora la existencia del escudo y todo el daño va directamente a la vitalidad.
 * En la recarga, recupera un 50% de cada uno de sus valores base y suma un 10% adicional por cada punto de experiencia.
 */
public class PokemonAgua extends Pokemon {

    public PokemonAgua(String nombre) {
        super(nombre, 0, 250, 500, 60);
    }

    @Override
    public void atacar(Pokemon adversario) {
        double danio = this.vitalidad * 0.10;
        adversario.recibirDanio(danio);
    }

    @Override
    public void recibirDanio(double danio) {
        // Ignora el escudo, el daño va directo a la vitalidad
        this.vitalidad -= danio;
    }

    @Override
    public void recargar() {
        this.escudo = escudoBase * 0.5 + escudoBase * 0.1 * experiencia;
        this.vitalidad = vitalidadBase * 0.5 + vitalidadBase * 0.1 * experiencia;
        this.fuerza = fuerzaBase * 0.5 + fuerzaBase * 0.1 * experiencia;
    }

    @Override
    public PokemonAgua clone() {
        return new PokemonAgua(this.nombre);
    }

    @Override
    public double getCosto() {
        return 1900; // costo estimado
    }
    @Override
    public String toString() {
        return nombre + " [vida: " + vitalidad + ", experiencia: " + experiencia + "]";
    }

	@Override
	public void serHechizadoNiebla() {
		this.vitalidad*=.5;
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad*=.9;
		this.fuerza*=.9;
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo*=.1;
	}

    
}