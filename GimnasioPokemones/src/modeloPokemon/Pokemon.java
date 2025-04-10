package modeloPokemon;
import modeloInterfaces.Clasificable;
import modeloInterfaces.Valuable;
import modeloInterfaces.Hostil;
import modeloHechizos.IHechizable;

/**Clase abstracta parar epresentar un pokemon con atributos y metodos comunes para todos **/

public abstract class Pokemon implements Hostil, Valuable, Clasificable, Cloneable, IHechizable {
	
	protected String nombre;
	protected int experiencia;
	protected double escudo;
	protected double vitalidad;
	protected double fuerza;
	
	protected double escudoBase;
	protected double vitalidadBase;
	protected double fuerzaBase;
	//podriamos sacarlos
	
	/**
	 * Constructor base para pokemon 					
	 * @param nombre	
	 * @param experiencia
	 * @param escudo
	 * @param vitalidad
	 * @param fuerzaAtaque
	 */
	
	
	public Pokemon (String nombre, int experiencia, double escudo, double vitalidad, double fuerza) {
		this.nombre = nombre;
		this.experiencia = experiencia;
		this.escudo = escudo;
		this.vitalidad = vitalidad;
		this.fuerza = fuerza;
		this.escudoBase = escudo;
		this.vitalidadBase = vitalidad;
		this.fuerzaBase = fuerza;
	}
	
    public void ganarExperiencia() {
    	/**
    	 * Aumenta la experiencia del Pokémon en 1 punto.
    	 */
        this.experiencia++;
    }

    /**
     * Devuelve el nombre del Pokémon.
     * 
     * @return nombre del Pokémon.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la vitalidad actual.
     * 
     * @return vitalidad.
     */
    public double getVitalidad() {
        return vitalidad;
    }

    /**
     * Aplica daño al Pokémon, ajustando escudo y vitalidad.
     * Este método será redefinido por las subclases según su tipo.
     * 
     * @param danio Cantidad de daño recibido.
     */
    public abstract void recibirDanio(double danio);

    /**
     * Restaura los valores base del Pokémon.
     */
    public abstract void recargar();

    /**
     * Devuelve la categoría del Pokémon (basada en su experiencia).
     * 
     * @return categoría como int.
     */
    @Override
    public int getCategoria() {
        return experiencia;
    }

    /**
     * Devuelve el costo del Pokémon.
     * 
     * @return costo como double.
     */
    @Override
    public abstract double getCosto();
    
    /**
     * Indica si el Pokémon sigue con vida (vitalidad mayor a 0).
     * 
     * @return true si está vivo, false en caso contrario.
     */
    public boolean estaVivo() {
        return this.vitalidad > 0;
    }


    /**
     * Ataca al Pokémon adversario.
     * 
     * @param adversario El Pokémon a atacar.
     */
    @Override
    public abstract void atacar(Pokemon adversario);

    /**
     * Retorna una copia del Pokémon si puede ser clonado.
     * 
     * @return copia del Pokémon o null si no es clonable.
     */
    @Override
    public Pokemon clone() throws CloneNotSupportedException {
        return (Pokemon) super.clone();
    }
    
    @Override
    public String toString() {
        return nombre + " (EXP: " + experiencia + ", VIT: " + vitalidad + ", ESC: " + escudo + ", ATQ: " + fuerza + ")";
    }
}