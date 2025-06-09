package modeloSerializador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Mercados.Mercado;
import modeloArenas.GestorDeArenas;
import modeloEntrenador.Entrenador;
import modeloTorneo.Torneo;

public class SerializadorInput {
    
    public Torneo cargarPartida(File archivo, Torneo torneoNuevo) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(archivo);
        ObjectInputStream input = new ObjectInputStream(file);
        Torneo nuevoTorneo = torneoNuevo;
        for (Entrenador e : nuevoTorneo.getEntrenadores()) {
            if (e.getPokemonesCombate() == null) {
                e.setPokemonesCombate(new ArrayList<>());
            }
        }
        Torneo viejoTorneo = (Torneo) input.readObject();
        nuevoTorneo.setEntrenadores(viejoTorneo.getEntrenadores());

        GestorDeArenas.setInstancia(viejoTorneo.getGestorDeArenas());
        Mercado.setInstancia(viejoTorneo.getTienda());
        
        
        input.close();

        return nuevoTorneo;
    }
    
}