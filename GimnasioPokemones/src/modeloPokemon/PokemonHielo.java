package modeloPokemon;

/**
 * Representa un Pokémon de tipo Hielo.
 */
public class PokemonHielo extends Pokemon {

    public PokemonHielo(String nombre) {
        super(nombre, 0, 120, 400, 100);
    }

    @Override
    public void atacar(Pokemon adversario) {
        double danio = this.fuerza * 0.15;
        adversario.recibirDanio(danio);
        this.fuerza *= 0.95;
    }

    @Override
    public void recibirDanio(double danio) {
        if (escudo > 0) {
            escudo -= danio;
        } else {
            vitalidad -= danio;
        }
    }

    @Override
    public void recargar() {
        this.vitalidad += 200;
        this.fuerza += 100;
        this.escudo += 100;
    }

    @Override
    public PokemonHielo clone() {
        return new PokemonHielo(this.nombre);
    }

    @Override
    public double getCosto() {
        return 1800; // ejemplo
    }

	@Override
	public void serHechizadoNiebla() {
		this.vitalidad*=.4;	
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad*=.8;
		this.fuerza*=.8;
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo*=.2;
	}
    
    
}