package modeloHechizos;

public class HechizoViento implements Hechizo{
	
	public HechizoViento() {
		super();
	}

	@Override
	public void usarHechizo(IHechizable hechizable) {
		hechizable.serHechizadoViento();
	}	
}
