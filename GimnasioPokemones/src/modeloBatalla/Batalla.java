package modeloBatalla;

import modeloArenas.GestorDeArenas;
import modeloEntrenador.Entrenador;
import modeloEntrenador.EntrenadorPreparado;
import modeloArenas.IArena;
import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloPokemon.Pokemon;
public class Batalla extends Thread {
	Entrenador entrenadorA;
	Entrenador entrenadorB;
	EntrenadorPreparado entrenador1;
	EntrenadorPreparado entrenador2;
    private GestorDeArenas gestorArenas;

    public Batalla(Entrenador entrenador1, Entrenador entrenador2, GestorDeArenas gestorArenas) {
    	this.entrenadorA=entrenador1;
		this.entrenadorB=entrenador2;
		this.entrenador1 = new EntrenadorPreparado(entrenadorA.getNombre());
		this.entrenador2 = new EntrenadorPreparado(entrenadorB.getNombre());
		try {
			this.entrenador1.preparaParaBatalla(this.entrenadorA);
		}
		catch(EntrenadorSinPokemonesException e){
			System.out.println(e.getMessage());
		}
		try{
			this.entrenador2.preparaParaBatalla(this.entrenadorB);
		}
		catch(EntrenadorSinPokemonesException e){
			System.out.println(e.getMessage());
		}
        this.gestorArenas = GestorDeArenas.getInstance();
    }

    @Override
    public void run() { 
        try {
            IArena arena = gestorArenas.asignarArenaLibre();
            Entrenador ganador = enfrentarEntrenadores(entrenador1, entrenador2, arena);
            /*
             * ACA EL CONTROLADOR LE AVISA A LA VENTANA QUIEN ES EL GANADOR
            */
            gestorArenas.liberarArena(arena);
        } catch (InterruptedException e) {
            System.out.println("Batalla interrumpida");
     //   } catch (EntrenadorSinPokemonesException e) {
       //     System.out.println(e.getMessage());
       }
    }


    private Entrenador enfrentarEntrenadores(Entrenador entrenador1, Entrenador entrenador2, IArena arena){
        if (entrenador1.pokemones.isEmpty()) {
        	entrenador2.ganarDuelo(arena.getPremio());
            return entrenador2;
        }
        if (entrenador2.pokemones.isEmpty()) {
        	entrenador1.ganarDuelo(arena.getPremio());
        	return entrenador1;
        }
        int i = 0, j = 0;
        while (i < 3 && i < entrenador1.pokemones.size() && j < 3 && j < entrenador2.pokemones.size()) {
            Pokemon p1 = entrenador1.pokemones.get(i);
            Pokemon p2 = entrenador2.pokemones.get(j);

            while (p1.estaVivo() && p2.estaVivo()) {
                p1.atacar(p2);
                if (p2.estaVivo()) {
                    p2.atacar(p1);
                }
            }

            if (p1.estaVivo()) {
                p1.ganarExperiencia();
                j++;
            } else {
                i++;
            }
        }

        if (i == 3 || i >= entrenador1.pokemones.size()) {
            entrenador2.ganarDuelo(arena.getPremio());
            return entrenador2;
        } else {
            entrenador1.ganarDuelo(arena.getPremio());
            return entrenador1;
        }
    }
}
