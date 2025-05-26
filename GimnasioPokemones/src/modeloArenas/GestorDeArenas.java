import java.util.List;

public class GestorDeArenas {
    private final List<Arena> arenas;
    private static GestorDeArenas instancia;

    private GestorDeArenas(List<Arena> arenas, int cantArenas) {
        String nombre;
        String ubicacion;
        String dificultad;
        for (int i = 0; i < cantArenas; i++) {
            /*
            * ACA EL CONTROLADOR LE PIDE DATOS A LA VENTANA PARA CREAR LAS ARENAS
            */
            this.arenas.add(new Arena(nombre, ubicacion, dificultad));
            /*
            * ACA EL CONTROLADOR AVISA POR LA VENTANA QUE SE CREO UNA NUEVA ARENA
            */
        }
    }
    
    public static GestorDeArenas getInstance(List<Arena> arenas, int cantArenas) {
        if (instancia == null) {
            instancia = new GestorDeArenas(arenas,cantArenas);
        }
        return instancia;
    }
    
    // Busca una arena libre de forma sincronizada
    public synchronized Arena asignarArenaLibre() {
        for (Arena arena : arenas) {
            if (arena.estaLibre()) {
                arena.ocupar();
                return arena;
            }
        }
    }

    public synchronized void liberarArena(Arena arena) {
        arena.liberar();
    }
}
