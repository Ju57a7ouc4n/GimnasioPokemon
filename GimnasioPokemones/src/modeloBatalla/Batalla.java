import modeloEntrenador.Entrenador;
import modeloArena.Arena;
import modeloArena.GestorArenas;
import modeloExcepciones.EntrenadorSinPokemonesException;

public class Batalla extends Thread {
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private semaphore semaforo;
    private final GestorArenas gestorArenas;

    public Batalla(Entrenador entrenador1, Entrenador entrenador2, semaphore semaforo, GestorArenas gestorArenas) {
        this.semaforo = semaforo;
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.gestorArenas = gestorArenas.getInstance();
    }

    @Override
    public void run() {
        try {
            semaforo.acquire(); // Espera hasta que haya una arena libre
            Arena arena = gestorArenasestor.asignarArenaLibre();
            Entrenador ganador = enfrentarEntrenadores(this.entrenador1, this.entrenador2);
            /*
             * ACA EL CONTROLADOR LE PIDE A LA VENTANA QUE MUESTRE EL GANADOR
             */
            gestorArenas.liberarArena(arena);
            semaforo.release();
        } catch (InterruptedException e) {
            system.out.println("Batalla interrumpida");
        }
    }


    private Entrenador enfrentarEntrenadores(Entrenador entrenador1, Entrenador entrenador2) throws EntrenadorSinPokemonesException {
        if (entrendaor1.pokemones.isEmpty())
            throw new EntrenadorSinPokemonesException(entrenador1);
        if (entrenador2.pokemones.isEmpty())
            throw new EntrenadorSinPokemonesException(entrenador2);

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
            entrenador2.gestorArenas.arena.getPremio();
            return entrenador2;
        } else {
            entrenador1.gestorArenas.arena.getPremio();
            return entrenador1;
        }
    }
}
