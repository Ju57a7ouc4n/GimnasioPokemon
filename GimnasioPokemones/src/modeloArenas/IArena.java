package modeloArenas;

public interface IArena{
	double getPremio();
	
	String getNombre();

	String getDetalle();

	boolean estaLibre();

	void ocupar();

	void liberar();

}
