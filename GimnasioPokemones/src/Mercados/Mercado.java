package Mercados;

import modeloArmas.*;
import modeloPokemon.*;
import modeloEntrenador.Entrenador;

import java.io.Serializable;
import java.util.Scanner;
import modeloExcepciones.CompraImposibleException;

public class Mercado implements Serializable{
	private static Mercado instancia;
	
	 private Mercado(){
		 super();
	 }
	    
	 public static Mercado getInstance() {
		 if (instancia == null) {
			 instancia = new Mercado();
	     }
	     return instancia;
	 }

    public void compras(Entrenador entrenador){
        Scanner scanner = new Scanner(System.in);
        String respuesta;
        System.out.println("Bienvenido " + entrenador.getNombre() + " al Mercado! Que desea comprar? (arma/pokemon)");
        respuesta = scanner.nextLine();

        while (!respuesta.equalsIgnoreCase("arma") && !respuesta.equalsIgnoreCase("pokemon")) {
            System.out.println("Opcion no valida. Por favor, elija 'arma' o 'pokemon'.");
            respuesta = scanner.nextLine();
        }
        
        if (respuesta.equalsIgnoreCase("Pokemon"))
            try{
                compraPokemones(entrenador);
            }
        	catch(CompraImposibleException e){
        	    System.out.printf(entrenador.getNombre() + " " + e.getMessage() + "\n");
        	}
        else if (respuesta.equalsIgnoreCase("Arma")) {
            int posPokemonArmable = entrenador.buscaPokemonArmable(); // Variable para almacenar la posición del Pokémon Armable si es que existe el pokemon en la lista de pokemones
                if (posPokemonArmable != -1) {
                    try {
                        compraArmas(entrenador, posPokemonArmable);
                        }
                    catch(CompraImposibleException e) {
                        System.out.printf(entrenador.getNombre() + " " + e.getMessage() + "\n");
                    }
                }
                else {
                    System.out.println("No puedes comprar armas si no tienes un Pokemon de tipo piedra.");
                }
            }
    }

    public void compraPokemones(Entrenador entrenador) throws CompraImposibleException {
        Pokemon pokemon = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que tipo de pokemon desea comprar? (Agua/Fuego/Hielo/Piedra)");
        String tipo = scanner.nextLine();

        while (!tipo.equalsIgnoreCase("agua") && !tipo.equalsIgnoreCase("fuego") && !tipo.equalsIgnoreCase("hielo") && !tipo.equalsIgnoreCase("piedra")) {
            System.out.println("Opcion no valida. Por favor, elija 'agua', 'fuego', 'hielo' o 'piedra'.");
            tipo = scanner.nextLine();
        }

        String nombre = null;
        int valor=0;

        if (tipo.equalsIgnoreCase("agua")) {
            if (entrenador.getCreditos() < 100) {
                throw new CompraImposibleException(entrenador.getCreditos(), 100, "Pokemon agua");
            }
            System.out.println("Ingrese el nombre del Pokemon de tipo Agua: ");
            nombre = scanner.nextLine();
            pokemon = new PokemonAgua(nombre);
            valor = 100;
        }
        else if (tipo.equalsIgnoreCase("fuego")) {
            if (entrenador.getCreditos() < 120) {
                throw new CompraImposibleException(entrenador.getCreditos(), 120, "Pokemon fuego");
            }
            System.out.println("Ingrese el nombre del Pokemon de tipo Fuego: ");
            nombre = scanner.nextLine();
            pokemon = new PokemonFuego(nombre);
            valor = 120;
        }
        else if (tipo.equalsIgnoreCase("hielo")) {
            if (entrenador.getCreditos() < 100) {
                throw new CompraImposibleException(entrenador.getCreditos(), 100, "Pokemon hielo");
            }
            System.out.println("Ingrese el nombre del Pokemon de tipo Hielo: ");
            nombre = scanner.nextLine();
            pokemon = new PokemonHielo(nombre);
            valor = 100;
        }
        else if (tipo.equalsIgnoreCase("piedra")) {
            if (entrenador.getCreditos() < 200) {
                throw new CompraImposibleException(entrenador.getCreditos(), 200, "Pokemon piedra");
            }
            System.out.println("Ingrese el nombre del Pokemon de tipo Piedra: ");
            nombre = scanner.nextLine();
            pokemon = new PokemonPiedra(nombre);
            valor = 200;
        }
        entrenador.setCreditos(entrenador.getCreditos() - valor);
        System.out.println("pokemon comprado y agregado a la lista de pokemones: " + pokemon.toString());
        entrenador.agregarPokemon(pokemon);
    }

    public void compraArmas(Entrenador entrenador,int posPokemonArmable) throws CompraImposibleException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que tipo de arma desea comprar? (Hacha/Espada)");
        String tipo = scanner.nextLine();

        while (!tipo.equalsIgnoreCase("hacha") && !tipo.equalsIgnoreCase("espada")) {
            System.out.println("Opcion no valida. Por favor, elija 'hacha' o 'espada'.");
            tipo = scanner.nextLine();
        }

        Arma arma = null;
        int valor = 0;
        
        if (tipo.equalsIgnoreCase("espada")) {
            if (entrenador.getCreditos() < 50) {
                throw new CompraImposibleException(entrenador.getCreditos(), 50, "Espada");
            }
            valor = 50;
            arma = new Espada();
        }
        else if (tipo.equalsIgnoreCase("hacha")) {
            if (entrenador.getCreditos() < 80) {
                throw new CompraImposibleException(entrenador.getCreditos(), 80, "Hacha");
            }
            valor = 80;
            arma = new Hacha();
        }
        entrenador.setCreditos(entrenador.getCreditos() - valor);
        entrenador.getPokemon(posPokemonArmable).setArma(arma);
        System.out.println("Arma comprada y agreagada al pokemon " + entrenador.getPokemon(posPokemonArmable).getNombre());
    }
    
    public static void setInstancia(Mercado nueva){
    	instancia=nueva;
    }
}
