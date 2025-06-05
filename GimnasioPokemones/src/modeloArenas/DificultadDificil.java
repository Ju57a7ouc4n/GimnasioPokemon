package modeloArenas;

public class DificultadDificil extends Decorator{

    public DificultadDificil(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*1.5;
    }
}