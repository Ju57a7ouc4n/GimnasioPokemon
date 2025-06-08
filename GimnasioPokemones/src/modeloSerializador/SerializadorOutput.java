package modeloSerializador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import modeloTorneo.Torneo;

public class SerializadorOutput {
	
	public void guardarPartida(Torneo torneo, String rutaCompleta) throws IOException{
		
		FileOutputStream file = new FileOutputStream(rutaCompleta);
		ObjectOutputStream output = new ObjectOutputStream(file);
		output.writeObject(torneo);
		output.close();
	}
	
}
