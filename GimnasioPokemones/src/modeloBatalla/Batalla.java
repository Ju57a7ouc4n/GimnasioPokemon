package modeloBatalla;

import java.util.Observable;

import modeloArenas.GestorDeArenas;
import modeloEntrenador.Entrenador;
import modeloExcepciones.EntrenadorSinPokemonesException;
import modeloArenas.IArena;
import modeloPokemon.Pokemon;
public class Batalla extends Thread{
	protected Entrenador entrenador1;
	protected Entrenador entrenador2;
	public Entrenador ganador = null;
    private GestorDeArenas gestorArenas;
    private final ObservablePublico observable = new ObservablePublico();
    
    public Batalla(Entrenador entrenador1, Entrenador entrenador2, GestorDeArenas gestorArenas) {
    	this.entrenador1=entrenador1;
		this.entrenador2=entrenador2;
        this.gestorArenas = GestorDeArenas.getInstance();
    }

    @Override
    public void run() { 
    	IArena arena=null;
		try {
			arena = gestorArenas.asignarArenaLibre();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        try {
            log("Comienza la batalla entre " + this.entrenador1.getNombre() + " y " + this.entrenador2.getNombre() + "\n");
        	this.ganador = enfrentarEntrenadores(this.entrenador1, this.entrenador2, arena);
        	log("¡El ganador es " + this.ganador.getNombre() + "!\n");
            gestorArenas.liberarArena(arena);
        } catch (EntrenadorSinPokemonesException e) {
           e.getMessage();
           if(e.getEntrenador().equals(entrenador1)){
        	   log("¡Pero" + this.entrenador1.getNombre() + "no tiene Pokemones en combate!\n");
        	   entrenador2.ganarDuelo(arena.getPremio());
               this.ganador = entrenador2;
               log("¡El ganador es " + this.ganador.getNombre() + "!\n");
           }
           else {
        	   log("¡Pero" + this.entrenador1.getNombre() + "no tiene Pokemones en combate!\n");
        	   entrenador1.ganarDuelo(arena.getPremio());
               this.ganador = entrenador1;
               log("¡El ganador es " + this.ganador.getNombre() + "!\n");
           }
       }
    }


    private Entrenador enfrentarEntrenadores(Entrenador entrenador1, Entrenador entrenador2, IArena arena) throws EntrenadorSinPokemonesException{
        if (entrenador1.getPokemonesCombate().isEmpty()) {
        	throw new EntrenadorSinPokemonesException(entrenador1);
        }
        if (entrenador2.getPokemonesCombate().isEmpty()) {
        	throw new EntrenadorSinPokemonesException(entrenador2);
        }
        int i = 0, j = 0;
        while (i < 3 && i < entrenador1.getPokemonesCombate().size() && j < 3 && j < entrenador2.getPokemonesCombate().size()) {
            Pokemon p1 = entrenador1.getPokemonesCombate().get(i);
            Pokemon p2 = entrenador2.getPokemonesCombate().get(j);
            log(this.entrenador1.getNombre() + " elige a " + p1.getNombre() + "\n");
            log(this.entrenador2.getNombre() + " elige a " + p2.getNombre() + "\n");
            while (p1.estaVivo() && p2.estaVivo()) {
                p1.atacar(p2);
                log(p1.getNombre() + " ataca a" + p2.getNombre() + "\n");
                if (p2.estaVivo()) {
                    p2.atacar(p1);
                    log(p2.getNombre() + " ataca a" + p1.getNombre() + "\n");
                }
            }

            if (p1.estaVivo()) {
                p1.ganarExperiencia();
                log(p2.getNombre() + "deja el combate\n");
                log(p1.getNombre() + "gana 1 exp\n");
                j++;
            } else {
                i++;
                log(p1.getNombre() + "deja el combate\n");
                log(p2.getNombre() + "gana 1 exp\n");
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
    
    public Observable getObservable() {
        return observable;
    }

    private void log(String mensaje) {
        observable.setChanged();
        observable.notifyObservers(mensaje);
    }
}  
