package modeloBatalla;

import modeloArenas.GestorDeArenas;
import modeloEntrenador.Entrenador;
import modeloArenas.IArena;
import modeloPokemon.Pokemon;
public class Batalla extends Thread {
	protected Entrenador entrenador1;
	protected Entrenador entrenador2;
	public Entrenador ganador = null;
    private GestorDeArenas gestorArenas;

    public Batalla(Entrenador entrenador1, Entrenador entrenador2, GestorDeArenas gestorArenas) {
    	this.entrenador1=entrenador1;
		this.entrenador2=entrenador2;
        this.gestorArenas = GestorDeArenas.getInstance();
    }

    @Override
    public void run() { 
        try {
            IArena arena = gestorArenas.asignarArenaLibre();
            this.ganador = enfrentarEntrenadores(this.entrenador1, this.entrenador2, arena);
            gestorArenas.liberarArena(arena);
        } catch (InterruptedException e) {
            System.out.println("Batalla interrumpida");
     //   } catch (EntrenadorSinPokemonesException e) {
       //     System.out.println(e.getMessage());
       }
    }


    private Entrenador enfrentarEntrenadores(Entrenador entrenador1, Entrenador entrenador2, IArena arena){
        if (entrenador1.getPokemonesCombate().isEmpty()) {
        	entrenador2.ganarDuelo(arena.getPremio());
            return entrenador2;
        }
        if (entrenador2.getPokemonesCombate().isEmpty()) {
        	entrenador1.ganarDuelo(arena.getPremio());
        	return entrenador1;
        }
        int i = 0, j = 0;
        while (i < 3 && i < entrenador1.getPokemonesCombate().size() && j < 3 && j < entrenador2.getPokemonesCombate().size()) {
            Pokemon p1 = entrenador1.getPokemonesCombate().get(i);
            Pokemon p2 = entrenador2.getPokemonesCombate().get(j);

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

        if (i == 3 || i >= entrenador1.getPokemonesCombate().size()) {
            entrenador2.ganarDuelo(arena.getPremio());
            for(Pokemon pokemon : entrenador2.getPokemonesCombate()) {
            	pokemon.recargar();
            	entrenador2.pokemones.add(pokemon);
            }
            entrenador2.getPokemonesCombate().clear();
            return entrenador2;
        } else {
            entrenador1.ganarDuelo(arena.getPremio());
            for(Pokemon pokemon : entrenador1.getPokemonesCombate()){
            	pokemon.recargar();
            	entrenador1.pokemones.add(pokemon);
            }
            entrenador1.getPokemonesCombate().clear();
            return entrenador1;
        }
    }
}
