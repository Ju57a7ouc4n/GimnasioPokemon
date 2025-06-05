package modeloHechizos;


public class HechizoNiebla implements Hechizo {
	
	public HechizoNiebla() {
		super();
	}

	@Override
	public void usarHechizo(IHechizable hechizable) {
		hechizable.serHechizadoNiebla();
	}
}
