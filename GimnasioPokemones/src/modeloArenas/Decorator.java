package modeloArenas;

import java.io.Serializable;

public abstract class Decorator implements IArena, Serializable{
    public IArena arena;

	public Decorator(IArena arena) {
		super();
		this.arena = arena;
	}

	@Override
	public String getNombre() {
		return this.arena.getNombre();
	}

	@Override
	public String getDetalle() {
		return this.arena.getDetalle();
	}

	@Override
	public boolean estaLibre() {
		return this.arena.estaLibre();
	}

	@Override
	public void ocupar() {
		this.arena.ocupar();
	}

	@Override
	public void liberar() {
		this.arena.liberar();
	}
    
    
}
