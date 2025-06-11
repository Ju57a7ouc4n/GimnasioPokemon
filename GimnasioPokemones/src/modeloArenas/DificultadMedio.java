package modeloArenas;

import java.io.Serializable;

public class DificultadMedio extends Decorator implements Serializable{

    public DificultadMedio(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*1.2;
    }
}