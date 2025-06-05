package modeloEntrenador;


import java.util.ArrayList;
import java.util.Scanner;

import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloPokemon.Pokemon;

/**
 * Representa a un Entrenador preparado para batalla, con los pokemones que utilizara en el duelo,
 * 
 */

public class EntrenadorPreparado extends Entrenador {

    public ArrayList<Pokemon> pokemonesBatalla;

    public EntrenadorPreparado (String nombre) {
        super(nombre);
        this.pokemonesBatalla = new ArrayList<Pokemon>();
    }


    /**
     * Este metodo se llama si el entrenador tiene al menos 1 pokemon en su lista de pokemons.
     * Agrega un Pokémon a la lista de pokemones para batalla del entrenador.
     * Puede agregar hasta 3 pokemones a la lista de batalla.
     * Si el entrenador no tiene pokemones, lanza una excepción EntrenadorSinPokemonesException.
     */
    public void preparaParaBatalla(Entrenador entrenador) throws EntrenadorSinPokemonesException  {
        int i=0;
        if(entrenador.pokemones.size()==0)
        	throw new EntrenadorSinPokemonesException(entrenador);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrenador " + entrenador.getNombre() + " Quiere agregar un pokemon a la lista de batalla? (si/no)");
        String respuesta = scanner.nextLine();

        int a = entrenador.pokemones.size(); // Cantidad de pokemones del entrenador
        int b = 0; // Cantidad de pokemones en la lista de batalla

        while ((i < a && b < 3) && respuesta.equals("si")) {
        System.out.println("Quiere agregar este pokemon? (si/no)" + entrenador.pokemones.get(i).getNombre());
        String respuesta2 = scanner.nextLine();
            if (respuesta2.equals("si")) {
                this.pokemonesBatalla.add(entrenador.pokemones.get(i));
                System.out.println("Pokemon agregado a la lista de batalla: " + entrenador.pokemones.get(i).getNombre());
                b++;
                i++;
                if (b < 3 && i < a) {
                    System.out.println("Entrenador " + entrenador.getNombre() + " Quiere agregar un pokemon a la lista de batalla? (si/no)");
                    respuesta = scanner.nextLine();
                }
            }
            else {
                System.out.println("Pokemon no agregado a la lista de batalla: " + entrenador.pokemones.get(i).getNombre());
                i++;
            }   
        }       	
    }

}
