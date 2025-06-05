package modeloHechizos;


public class HechizoTormenta implements Hechizo{

	public HechizoTormenta() {
		super();
	}

	@Override
	public void usarHechizo(IHechizable hechizable) {
		hechizable.serHechizadoTormenta();
	}	
}
