package modeloArenas;

import java.io.Serializable;

public abstract class Arena implements IArena, Serializable{
    protected String nombre;
    protected double premio;
    protected boolean ocupada = false;

    public Arena(String nombre) {
        this.nombre = nombre;
    }

    @Override
	public String getNombre() {
        return nombre;
    }
    
    @Override
	public abstract String getDetalle();
    
    @Override
	public double getPremio() {
        return this.premio;
    }

    @Override
	public synchronized boolean estaLibre() {
        return !ocupada;
    }

    @Override
	public synchronized void ocupar() {
        ocupada = true;
    }

    @Override
	public synchronized void liberar() {
        ocupada = false;
    }

}
