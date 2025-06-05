package modeloArenas;

public class DificultadMedio extends Decorator{

    public DificultadMedio(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*1.2;
    }
}