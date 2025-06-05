package modeloArenas;

public class DificultadFacil extends Decorator{

    public DificultadFacil(IArena Arena){
        super(Arena);
    } 

    @Override
    public double getPremio(){
        return this.arena.getPremio()*.9;
    }
}