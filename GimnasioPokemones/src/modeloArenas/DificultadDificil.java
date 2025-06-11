package modeloArenas;

import java.io.Serializable;

public class DificultadDificil extends Decorator implements Serializable{

    public DificultadDificil(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*1.5;
    }
}