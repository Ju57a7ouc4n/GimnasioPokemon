package modeloPokemon;

import Utils.Redondear;
import modeloArmas.*;

/**
 * Representa un Pokémon de tipo Piedra.
 */
public class PokemonPiedra extends Pokemon {

    private Arma arma = null;

    public PokemonPiedra(String nombre) {
        super(nombre, 300, 600, 150, 200, true);
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public Arma getArma() {
        return this.arma;
    }

    @Override
    public void ejecutarAtaque(Pokemon adversario) {
        if (arma != null) {
            arma.atacar(adversario);
        } else {
            double danio = Redondear.redondear(this.fuerza * .15);
            adversario.recibirDanio(danio);
        }
    }

    @Override
    public void pierdeFuerza() {
        this.fuerza = Redondear.redondear(this.fuerza * .95);
    }

    @Override
    public void recibirDanio(double danio) {
        if ((this.escudo - (danio * .75)) > 0) {
            this.escudo -= danio * 0.75;
            this.vitalidad -= danio * 0.25;
            this.escudo = Redondear.redondear(this.escudo);
        } else {
            this.vitalidad -= (danio *.75) - this.escudo;
            this.escudo = 0;
        }
        this.vitalidad = Redondear.redondear(this.vitalidad);
    }

    @Override
    public void recargar() {
        this.escudo = Math.min(this.escudoBase * 2, this.escudoBase * 0.8 + this.escudoBase * 0.1 * this.experiencia);
        this.vitalidad = Math.min(this.vitalidadBase * 2, this.vitalidadBase * 0.8 + this.vitalidadBase * 0.1 * this.experiencia);
        this.fuerza = Math.min(this.fuerzaBase * 2, this.fuerzaBase * 0.8 + this.fuerzaBase * 0.1 * this.experiencia);
        this.escudo = Redondear.redondear(this.escudo);
        this.vitalidad = Redondear.redondear(this.vitalidad);
        this.fuerza = Redondear.redondear(this.fuerza);
    }

	@Override
	public void serHechizadoNiebla() {
		this.fuerza = Redondear.redondear(this.fuerza * .4);
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad = Redondear.redondear(this.vitalidad * .25);
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo = 0;
		this.fuerza = Redondear.redondear(this.fuerza * .7);
	}

    @Override
    public PokemonPiedra clone () {
        PokemonPiedra copia = null;
        try {
            copia = (PokemonPiedra) super.clone();
            copia.arma = (Arma) this.arma.clone();
        } catch (CloneNotSupportedException e){}
        return copia;
    }

    @Override
    public String toString() {
        if (arma == null) {
            return super.toString() + " No tiene arma\n";
        }
        else{
            return super.toString() + " " + arma.toString();
        }
    }
}