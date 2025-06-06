package modeloArenas;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorDeArenas implements Serializable{
    private ArrayList<IArena> arenas;
    private static GestorDeArenas instancia;

    private GestorDeArenas(){
        this.arenas = new ArrayList<IArena>();
    }
    
    public static GestorDeArenas getInstance() {
        if (instancia == null) {
            instancia = new GestorDeArenas();
        }
        return instancia;
    }
    
    public void nuevaArena(int ubicacion, int dificultad, String nombre){
        IArena arena = null;
        switch (ubicacion){
        	case 0: switch (dificultad) {
        		case 0: arena = new DificultadFacil(new ArenaBosque(nombre));
        				break;
        		case 1: arena = new DificultadMedio(new ArenaBosque(nombre));
        				break;
        		case 2: arena = new DificultadDificil(new ArenaBosque(nombre));
        				break;
        	}
        	break;
        	case 1: switch (dificultad) {
    			case 0: arena = new DificultadFacil(new ArenaDesierto(nombre));
    					break;
    			case 1: arena = new DificultadMedio(new ArenaDesierto(nombre));
    					break;
    			case 2: arena = new DificultadDificil(new ArenaDesierto(nombre));
    					break;
        			}
        	break;
        	case 2: switch (dificultad) {
        		case 0: arena = new DificultadFacil(new ArenaSelva(nombre));
    					break;
        		case 1: arena = new DificultadMedio(new ArenaSelva(nombre));
    					break;
        		case 2: arena = new DificultadDificil(new ArenaSelva(nombre));
    					break;
        			}
        	break;
        }
        this.arenas.add(arena);
    }

  public synchronized IArena asignarArenaLibre() throws InterruptedException {
        while (!hayArenasLibres())
            wait(); // Espera si no hay ninguna libre
            for (IArena arena : arenas) {
                if (arena.estaLibre()) {
                    arena.ocupar();
                    return arena;
                }
            }
        throw new InterruptedException();
    }

    public synchronized void liberarArena(IArena arena) {
        arena.liberar();
        notifyAll(); // Notifica a los hilos esperando
    }

    public synchronized boolean hayArenasLibres(){
        for (IArena arena : arenas) {
            if (arena.estaLibre())
                return true;
        }
        return false;
    }
    
    public static void setInstancia(GestorDeArenas nueva){
    	instancia=nueva;
    }
}