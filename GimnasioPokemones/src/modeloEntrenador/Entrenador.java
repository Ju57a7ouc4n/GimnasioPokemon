package modeloEntrenador;

import java.util.ArrayList;
import modeloInterfaces.Clasificable;
import modeloPokemon.Pokemon;

/**
 * Representa a un Entrenador Pokémon que puede poseer una lista de pokemones,
 * enfrentarse a otros entrenadores, comprar pokemones y calcular su categoría.
 */

public class Entrenador implements Clasificable, Cloneable, java.io.Serializable {

    private String nombre;
    public ArrayList<Pokemon> pokemones;
    private int creditos;

    /**
     * Constructor del Entrenador.
     * 
     * @param nombre   Nombre del entrenador, debe ser distinto de vacío o null.
     * Dentro del constructor se le dan 200 creditos iniciales al entrenador para compras.
     * Se instancia el ArrayList de pokemones.
     */
    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.creditos = 200; //Creditos iniciales
        this.pokemones = new ArrayList<>();
    }

    /**
     * Agrega un Pokémon a la lista del entrenador.
     * 
     * @param pokemon Pokémon a agregar.
     */
    public void agregarPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);
    }
    
    public Pokemon getPokemon(int i) {
        return this.pokemones.get(i);
    }
    
    /**
     * Metodo que muestra el ArrayList de pokemones del entrenador
     */
    public void muestraListaPokemones() {
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.println("Pokemon " + (i + 1) + ": " + pokemones.get(i).toString());
        }
    }


    /**
     * Devuelve la categoría del entrenador (suma de las categorías de sus pokemones).
     * 
     * @return Categoría del entrenador.
     */
    @Override
    public int getCategoria() {
        int suma=0;
        for (Pokemon p : pokemones) {
            suma += p.getCategoria();
        }
        return suma;
    }


    public String getNombre() {
        return nombre;
    }



    public int getCreditos() {
        return creditos;
    }

    public void agregarCreditos(double monto) {
        this.creditos += monto;
    }
    
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }



    /**
     * Clona el entrenador si todos sus pokemones pueden clonarse. Puede arrojar CloneNotSupportedException.
     * 
     * @return Clon del entrenador o null si alguno de sus pokemones no es cloneable.     * 
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone () throws CloneNotSupportedException {
        Entrenador copia = (Entrenador) super.clone();
        copia.pokemones = (ArrayList<Pokemon>) this.pokemones.clone();
        copia.pokemones.clear();
        for (int i=0; i<this.pokemones.size(); i++) {
            copia.pokemones.add((Pokemon) this.pokemones.get(i).clone());
        }
        return copia;
    }

    
    /**
     * Metodo que le da al entrenador 500 creditos, si gana la batalla
     */
    public void ganarDuelo(double monto) {
        this.creditos+=monto;
    }
    
    /**
     * Metodo que busca en la lista de pokemones del entrenador un pokemon armable.
     * @return si existe devuelve la posicion y si no existe devuelve la posicion invalida -1.
     */
    public int buscaPokemonArmable() {
        int i=0;
        while (i<pokemones.size() && (((pokemones.get(i).Esarmable()) && (pokemones.get(i).getArma() != null)) || (!(pokemones.get(i).Esarmable())))) {
            i++;
        }
        return i<pokemones.size() ? i : -1;
    }
    
    /**
     * metodo toString que devuelve las caracteristicas del entrenador, como nombre, cantidad de creditos, categoría.
     */
    @Override
    public String toString() {
        return "\nEntrenador " + this.nombre + "\n" + "Creditos " + this.creditos + "\n" + "Categoria " + this.getCategoria(); 
    }
}
