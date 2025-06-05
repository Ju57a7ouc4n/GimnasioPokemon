package modeloPokemon;

import Utils.Redondear;


/**
 * Representa un Pokémon de tipo Agua.
 * El ataque causa daño equivalente al 10% de la vitalidad actual del Pokémon.
 * Su defensa ignora la existencia del escudo y todo el daño va directamente a la vitalidad.
 * En la recarga, recupera un 50% de cada uno de sus valores base y suma un 10% adicional por cada punto de experiencia.
 */
public class PokemonAgua extends Pokemon {

    public PokemonAgua(String nombre) {
        super(nombre, 100, 500, 120, 100, false);
    }

    @Override
    public void ejecutarAtaque(Pokemon adversario){
        double danio = Redondear.redondear(this.fuerza * .1);
        adversario.recibirDanio(danio); // El ataque causa un daño equivalente al 10% de la vitalidad actual
        
    }

    
    @Override
    public void recibirDanio(double danio) {
        if (escudo > 0) {
            this.escudo -= danio *.5;
            this.vitalidad -= danio * .5;
        }
        else {
            this.vitalidad -= danio;
        }
        this.escudo = Redondear.redondear(this.escudo);
        this.vitalidad = Redondear.redondear(this.vitalidad);
    }

    @Override
    public void recargar() {
        this.escudo = this.escudoBase;
        this.vitalidad = this.vitalidadBase;
        this.fuerza = this.fuerzaBase;
    }

	@Override
	public void serHechizadoNiebla() {
		this.vitalidad *= .5;
        this.vitalidad = Redondear.redondear(this.vitalidad);
	}

	@Override
	public void serHechizadoViento() {
        this.vitalidad = Redondear.redondear(this.vitalidad * .9);
        this.fuerza = Redondear.redondear(this.fuerza * .9);
    }

	@Override
	public void serHechizadoTormenta() {
        this.escudo = Redondear.redondear(this.escudo * .1);
	}

    @Override
    public PokemonAgua clone () {
        PokemonAgua copia = null;
        try {
            copia = (PokemonAgua) super.clone();
        }
        catch (CloneNotSupportedException e){}
        return copia;
    }

}