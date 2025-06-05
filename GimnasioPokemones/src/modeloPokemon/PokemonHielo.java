package modeloPokemon;

import Utils.Redondear;


/**
 * Representa un Pokémon de tipo Hielo.
 */
public class PokemonHielo extends Pokemon {

    public PokemonHielo(String nombre) {
        super(nombre, 120, 400, 100, 100, false);
    }

    @Override
    public void ejecutarAtaque(Pokemon adversario){
        double danio = Redondear.redondear(this.fuerza * .15);
        adversario.recibirDanio(danio);

    }

    @Override
    public void pierdeFuerza(){
        this.fuerza = Redondear.redondear(this.fuerza * .95);       
    }
    

    @Override
    public void recibirDanio(double danio) {
        if ((this.escudo - danio) > 0) {
            this.escudo -= danio;
        } else {
            this.vitalidad -= danio - this.escudo;
            this.escudo = 0;            
        }
    }

    @Override
    public void recargar() {
        this.vitalidad += 200;
        this.fuerza += 100;
        this.escudo += 100;
    }

	@Override
	public void serHechizadoNiebla() {
		this.vitalidad = Redondear.redondear(this.vitalidad * .4);	
	}

	@Override
	public void serHechizadoViento() {
		this.vitalidad = Redondear.redondear(this.vitalidad * .8);
		this.fuerza = Redondear.redondear(this.fuerza * .8);
	}

	@Override
	public void serHechizadoTormenta() {
		this.escudo = Redondear.redondear(this.escudo * .2);
	}

    @Override
    public PokemonHielo clone () {
        PokemonHielo copia = null;
        try {
            copia = (PokemonHielo) super.clone();
        }
        catch (CloneNotSupportedException e){}
        return copia;
    }
    

    
}