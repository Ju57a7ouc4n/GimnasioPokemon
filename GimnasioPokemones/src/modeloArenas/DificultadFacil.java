package modeloArenas;

import java.io.Serializable;

public class DificultadFacil extends Decorator implements Serializable{

    public DificultadFacil(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*.9;
    }
}