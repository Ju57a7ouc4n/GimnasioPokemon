package modeloMundo;

import modeloPokemon.Pokemon;
import modeloPokemon.PokemonAgua;
import modeloPokemon.PokemonFuego;
import modeloPokemon.PokemonHielo;
import modeloPokemon.PokemonPiedra;
import modeloArmas.Espada;
import modeloArmas.Hacha;
import modeloEntrenador.Entrenador;
import modeloEntrenador.EntrenadorPreparado;
import Mercados.Mercado;
import modeloBatalla.Batalla;
import modeloHechizos.HechizoNiebla;
import modeloHechizos.HechizoViento;
import modeloHechizos.HechizoTormenta;
import modeloHechizos.Hechizo;
import modeloExcepciones.EntrenadorSinPokemonesException;

public class Mundo {
    //Bloque de pruebas de funcionalidad 
    public void iniciarJuego() throws EntrenadorSinPokemonesException{
        
        Entrenador Ash = new Entrenador("Ash");
        Entrenador Misty = new Entrenador("Misty");
        
        //Prueba de compras
        //Se muestra excepcion CompraImposible
        
        /*
        Mercado tienda = new Mercado();
        tienda.compras(Ash); //Con esto Ash compra 1 pokemon tipo piedra llamado Onix
        tienda.compras(Ash); //Con esto Ash compra 1 pokemon tipo fuego llamado Charmander (no puede porque se quedo sin creditos, es decir lanza CompraImposibleException)
        tienda.compras(Misty); //Con esto Misty compra 1 pokemon tipo agua llamado Squirtle
        tienda.compras(Misty); //Con esto Misty compra 1 pokemon tipo hielo llamado Articuno

        Ash.agregarCreditos(200); //agrega 200 creditos al entrenador Ash para comprar un arma para Onix
        tienda.compras(Ash); //compra arma
        tienda.compras(Ash);
        */

        //se hace esto para no tener que hacer el proceso de compra cada vez que se quiera probar el programa.
        Ash.agregarPokemon(new PokemonPiedra ("Onix"));
        Ash.agregarPokemon(new PokemonFuego("Charmander"));
        Ash.getPokemon(0).setArma(new Espada());

        Misty.agregarPokemon(new PokemonAgua("Squirtle"));
        Misty.agregarPokemon(new PokemonHielo("Articuno"));

        //muestra lista de pokemones de ambos entrenadores
        System.out.println(Ash.toString());
        Ash.muestraListaPokemones();

        System.out.println(Misty.toString());
        Misty.muestraListaPokemones();

        //probamos clonar entrenador Ash
        try {
            Entrenador copiaAsh = (Entrenador) Ash.clone(); //no se puede clonar porque tiene pokemon de fuego
            copiaAsh.muestraListaPokemones();
        }
        catch (CloneNotSupportedException e) {
            System.out.println("\nNo se puede clonar el entrenador: contiene objetos no cloneables");
        }

        //probamos clonar entrenador Misty
        try { 
            Entrenador copiaMisty = (Entrenador) Misty.clone(); //se puede clonar
            System.out.println("\nSoy el clone del entrenador Misty");
            copiaMisty.muestraListaPokemones();
        }
        catch (CloneNotSupportedException e) {
            System.out.println("No se puede clonar al entrenador: contiene objetos no cloneables");
        }

        System.out.println("\n");
        
        //Se prueban los hechizos
        Hechizo HNiebla = new HechizoNiebla();
        Hechizo HTormenta = new HechizoTormenta();
        Hechizo HViento = new HechizoViento();

        System.out.println(Ash.getPokemon(0).toString()); //pokemon Onix antes de ser hechizado
        HNiebla.usarHechizo(Ash.getPokemon(0));
        System.out.println(Ash.getPokemon(0).toString()); //pokemon Onix despues de ser hechizado (-60% Fuerza)

        System.out.println(Misty.getPokemon(0).toString()); //pokemon Squirtle antes de ser hechizado
        HViento.usarHechizo(Misty.getPokemon(0));
        System.out.println(Misty.getPokemon(0).toString()); //pokemon Squirtle despues de ser hechizado (-10% Fuerza / -10% Vitalidad)

        System.out.println(Ash.getPokemon(1).toString()); //pokemon Charmander antes de ser hechizado
        HTormenta.usarHechizo(Ash.getPokemon(1));
        System.out.println(Ash.getPokemon(1).toString()); //pokemon Charmander despues de ser hechizado (-20% Escudo / -20% Fuerza)

        //Entrenador Brock = new Entrenador("Brock");
        //Brock.muestraListaPokemones();
        
       // Batalla batalla1 = new Batalla(Ash, Misty);
        //batalla1.duelo();

        Ash.muestraListaPokemones(); //Se muestran los pokemones de Ash luego del duelo

        Misty.muestraListaPokemones(); //Se muestran los pokemones de Misty luego del duelo
    }
}

