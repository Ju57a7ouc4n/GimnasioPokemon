package modeloPokemon;
import modeloInterfaces.Clasificable;
import modeloInterfaces.Valuable;
import modeloInterfaces.Hostil;
import modeloHechizos.IHechizable;
import modeloArmas.Arma;

/**Clase abstracta parar representar un pokemon con atributos y metodos comunes para todos **/

public abstract class Pokemon implements Hostil, Valuable, Clasificable, Cloneable, IHechizable, java.io.Serializable {
	
	protected String nombre;
	protected int experiencia;
	protected double escudo;
	protected double vitalidad;
	protected double fuerza;
    protected int costo;
    protected boolean esArmable;
	
	protected double escudoBase;
	protected double vitalidadBase;
	protected double fuerzaBase;

	
	/**
	 * Constructor base para pokemones.					
	 * @param nombre	
	 * @param experiencia
	 * @param escudo
	 * @param vitalidad
	 * @param fuerzaAtaque
	 * @param esArmable
	 */
	


	public Pokemon (String nombre, double escudo, double vitalidad, double fuerza, int costo, boolean esArmable) {
		this.nombre = nombre;
		this.experiencia = 0; 
		this.escudo = escudo;
		this.vitalidad = vitalidad;
		this.fuerza = fuerza;
        this.costo = costo;
		this.escudoBase = escudo;
		this.vitalidadBase = vitalidad;
		this.fuerzaBase = fuerza;
        this.esArmable = esArmable;
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
     * Método abstracto será redefinido por las subclases según su tipo.
     * 
     * @param danio Cantidad de daño recibido.
     */
    public abstract void recibirDanio(double danio);

    /**
     * Restaura los valores base del Pokémon.
     */
    public abstract void recargar();

    /**
     * Template que implementa el ataque de los pokemones.
     * ejecutarAtaque(): ataca al pokemon adversario.
     * pierdeFuerza(): metodo hook que implementan los pokemones que pierden fuerza al atacar.
     * @param adversario el pokemon que recibe el ataque.
     */
    @Override
    public void atacar(Pokemon adversario){

        ejecutarAtaque(adversario);

        pierdeFuerza();

    }
    
    
    /**
     * Ataca al pokemon adversario
     * @param adversario
     */
    public abstract void ejecutarAtaque(Pokemon adversario);

    /**
     *  Metodo hook que solo implementan los pokemones que pierden fuerza al atacar.
     */
    public void pierdeFuerza(){}

    /**
     * Metodo delegado que incrementa la experiencia de un pokemon luego de derrotar a otro pokemon.
     */
    public void ganarExperiencia() {
        this.experiencia++;
    }

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
    public double getCosto() {
        return costo;
    }

    public void setCosto(int valor) {
        this.costo = valor;
    }
    
    /**
     * Indica si el Pokémon sigue con vida (vitalidad mayor a 0).
     * 
     * @return true si está vivo, false en caso contrario.
     */
    public boolean estaVivo() {
        return this.vitalidad > 0;
    }

    public void setArma(Arma arma){} // Método vacío para ser redefinido por subclases que usan armas.

    public Arma getArma() {
        return null; 
    } // Método vacío para ser redefinido por subclases que usan armas.

    /**
     * Metodo que determina si un pokemon puede poseer un arma. 
     * @return true si puede tener un arma / false si no puede tener un arma.
     */
    public boolean Esarmable() {
        return this.esArmable;
    }

    public void setEsArmable(boolean esArmable) {
        this.esArmable = esArmable;
    }

    /**
     * Retorna una copia del Pokémon si puede ser clonado.
     * 
     * @return copia del Pokémon o null si no es clonable.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Pokemon) super.clone();
    }
    
    
    public String toString() {
        return nombre + " (EXP: " + experiencia + ", VIT: " + vitalidad + ", ESC: " + escudo + ", ATQ: " + fuerza + ")";
    }  
    
}