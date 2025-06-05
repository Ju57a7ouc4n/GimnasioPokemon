package modeloArmas;


import modeloInterfaces.Hostil;
import modeloInterfaces.Valuable;

public abstract class Arma implements Hostil, Valuable, Cloneable {

    private int costo;

    public Arma(int costo) {
        this.costo = costo;
    }   

    public double getCosto() {
        return costo;
    }

    @Override 
    public Object clone () throws CloneNotSupportedException {
        return (Arma) super.clone();
    }

}