package modeloPokemon;

import Utils.Redondear;


/**
 * Representa un Pokémon de tipo Fuego.
 * Su ataque provoca daño igual al 20% de su fuerza y luego sufre una reducción del 25% en fuerza (mínimo 10).
 * El escudo absorbe el 75% del daño, y la vitalidad el 25%.
 * En la recarga vuelve al 80% de los valores base, más un 5% por cada punto de experiencia.
 */
public class PokemonFuego extends Pokemon { 

    public PokemonFuego(String nombre) {
        super(nombre, 200, 530, 80, 120, false);
    }

    @Override
    public void ejecutarAtaque(Pokemon adversario) {
        double danio = Redondear.redondear(this.fuerza * .2);
        adversario.recibirDanio(danio); // El ataque causa un daño equivalente al 20% de la fuerza actual

    }

    @Override
    public void pierdeFuerza(){

        if((this.fuerza * .75) >= 10){
            this.fuerza *= .75;
        } else{
            this.fuerza = 10;
        }
        this.fuerza = Redondear.redondear(this.fuerza);
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
        this.escudo = this.escudoBase * 0.8 + this.escudoBase * 0.05 * this.experiencia;
        this.vitalidad = this.vitalidadBase * 0.8 + this.vitalidadBase * 0.05 * this.experiencia;
        this.fuerza = this.fuerzaBase * 0.8 + this.fuerzaBase * 0.05 * this.experiencia;
        this.escudo = Redondear.redondear(this.escudo);
        this.vitalidad = Redondear.redondear(this.vitalidad);
        this.fuerza = Redondear.redondear(this.fuerza);
    }

	@Override
	public void serHechizadoNiebla() {
		this.fuerza = Redondear.redondear(this.fuerza * .5);
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad = Redondear.redondear(this.vitalidad * .5);        ;
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo = Redondear.redondear(this.escudo * .8);
		this.fuerza = Redondear.redondear(this.fuerza * .8);;
	}

    @Override
    public PokemonFuego clone () throws CloneNotSupportedException {
        throw new CloneNotSupportedException(); 
    }

}