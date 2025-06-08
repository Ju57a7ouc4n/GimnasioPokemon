package modeloSerializador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Mercados.Mercado;
import modeloArenas.GestorDeArenas;
import modeloTorneo.Torneo;

public class SerializadorInput {
    
    public Torneo cargarPartida(File archivo) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(archivo);
        ObjectInputStream input = new ObjectInputStream(file);
        Torneo viejoTorneo = (Torneo) input.readObject();
        input.close();

        GestorDeArenas.setInstancia(viejoTorneo.getGestorDeArenas());
        Mercado.setInstancia(viejoTorneo.getTienda());

        Torneo nuevoTorneo = new Torneo();
        nuevoTorneo.setEntrenadores(viejoTorneo.getEntrenadores());

        return nuevoTorneo;
    }
    
}