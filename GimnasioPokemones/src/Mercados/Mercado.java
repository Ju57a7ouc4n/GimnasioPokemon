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

	    public void compraPokemon (Entrenador entrenador, String tipo, String nombre) throws CompraImposibleException {
	        Pokemon pokemon = null;
	        int valor = 0;
	        switch (tipo.toLowerCase()) {
	            case "agua":
	                if (entrenador.getCreditos() < 100) {
	                    throw new CompraImposibleException(entrenador.getCreditos(), 100, "Pokemon agua");
	                }
	                pokemon = new modeloPokemon.PokemonAgua(nombre);
	                valor = 100;
	                break;
	            case "fuego":
	                if (entrenador.getCreditos() < 150) {
	                    throw new CompraImposibleException(entrenador.getCreditos(), 120, "Pokemon fuego");
	                }
	                pokemon = new modeloPokemon.PokemonFuego(nombre);
	                valor = 150;
	                break;
	            case "hielo":
	                if (entrenador.getCreditos() < 200) {
	                    throw new CompraImposibleException(entrenador.getCreditos(), 100, "Pokemon hielo");
	                }
	                pokemon = new modeloPokemon.PokemonHielo(nombre);
	                valor = 200;
	                break;
	            case "piedra":
	                if (entrenador.getCreditos() < 250) {
	                    throw new CompraImposibleException(entrenador.getCreditos(), 200, "Pokemon piedra");
	                }
	                pokemon = new modeloPokemon.PokemonPiedra(nombre);
	                valor = 250;
	                break;
	        }
	        if (pokemon != null) {
	            entrenador.setCreditos(entrenador.getCreditos() - valor);
	            entrenador.agregarPokemon(pokemon);
	            System.out.println(entrenador.getPokemones());
	        }
	    }

	    public void compraArma(Entrenador entrenador, String tipoArma) throws CompraImposibleException {
	        int posPokemonArmable = entrenador.buscaPokemonArmable();
	        if (posPokemonArmable == -1)
	            throw new CompraImposibleException(0, 0, "No tienes Pokémon de tipo piedra para equipar un arma.");
	        Arma arma = null;
	        int valor = 0;
	        switch (tipoArma.toLowerCase()) {
	            case "espada":
	                if (entrenador.getCreditos() < 50)
	                    throw new CompraImposibleException(entrenador.getCreditos(), 50, "Espada");
	                arma = new Espada();
	                valor = 50;
	                break;
	            case "hacha":
	                if (entrenador.getCreditos() < 80)
	                    throw new CompraImposibleException(entrenador.getCreditos(), 80, "Hacha");
	                arma = new Hacha();
	                valor = 80;
	                break;
	        }
	        if (arma != null){
	            entrenador.setCreditos(entrenador.getCreditos() - valor);
	            entrenador.getPokemon(posPokemonArmable).setArma(arma);
	        }
	    }    
    public static void setInstancia(Mercado nueva){
    	instancia=nueva;
    }
}
