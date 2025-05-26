package modeloEntrenador;

import java.util.ArrayList;
import java.util.List;

import modeloExcepciones.CompraImposibleException;
import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloInterfaces.Clasificable;
import modeloPokemon.Pokemon;

public class Entrenador implements Clasificable, Cloneable {

    private String nombre;
    private List<Pokemon> pokemones;
    private double creditos;

    /**
     * Constructor del Entrenador.
     * 
     * @param nombre   Nombre del entrenador.
     * @param creditos Créditos iniciales.
     */
    public Entrenador(String nombre, double creditos) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.pokemones = new ArrayList<>();
    }

    /**
     * Agrega un Pokémon al equipo del entrenador.
     * 
     * @param pokemon Pokémon a agregar.
     */
    public void agregarPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);
    }

    /**
     * Intenta comprar un Pokémon si tiene créditos suficientes.
     * 
     * @param pokemon Pokémon a comprar.
     * @throws CompraImposibleException si no hay créditos suficientes.
     */
    public void comprarPokemon(Pokemon pokemon) throws CompraImposibleException {
        if (creditos >= pokemon.getCosto()) {
            creditos -= pokemon.getCosto();
            agregarPokemon(pokemon);
        } else {
            throw new CompraImposibleException(creditos, pokemon.getCosto());
        }
    }

    /**
     * Devuelve la categoría del entrenador (suma de las categorías de sus pokemones).
     * 
     * @return Categoría del entrenador.
     */
    @Override
    public int getCategoria() {
        return pokemones.stream().mapToInt(Pokemon::getCategoria).sum();
    }

    public String getNombre() {
        return nombre;
    }

    public double getCreditos() {
        return creditos;
    }

    public void agregarCreditos(double monto) {
        this.creditos += monto;
    }

    public List<Pokemon> getPokemones() {
        return pokemones;
    }

    /**
     * Clona el entrenador si todos sus pokemones pueden clonarse.
     * 
     * @return Clon del entrenador o null si alguno de sus pokemones no es clonable.
     * @throws CloneNotSupportedException 
     */
    @Override
    public Entrenador clone() throws CloneNotSupportedException {
        try {
            Entrenador clon = new Entrenador(this.nombre + "_Clon", 0);
            for (Pokemon p : this.pokemones) {
                clon.agregarPokemon(p.clone()); // puede lanzar UnsupportedOperationException
            }
            clon.agregarCreditos(this.creditos);
            return clon;
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", pokemones=" + pokemones.size() +
                '}';
    }
}
