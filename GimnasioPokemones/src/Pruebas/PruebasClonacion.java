package Pruebas;

import modeloEntrenador.Entrenador;
import modeloPokemon.PokemonAgua;

public class PruebasClonacion {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Creamos un entrenador original con 1 Pokémon Agua
        Entrenador original = new Entrenador("Ash", 0);
        original.agregarCreditos(1000);
        original.agregarPokemon(new PokemonAgua("Squirtle"));

        // Clonamos al entrenador
        Entrenador clon = original.clone();

        // Mostramos ambos
        System.out.println("🏋️ Entrenador original:");
        original.getPokemones().forEach(p -> System.out.println(" - " + p));
        System.out.println("Créditos: " + original.getCreditos());

        System.out.println("\n🔬 Entrenador clon:");
        clon.getPokemones().forEach(p -> System.out.println(" - " + p));
        System.out.println("Créditos: " + clon.getCreditos());

        // Verificamos si son objetos diferentes
        System.out.println("\n¿Es el mismo objeto? " + (original == clon));
        System.out.println("¿El Pokémon también fue clonado? " +
            (original.getPokemones().get(0) != clon.getPokemones().get(0)));
    }
}