import modeloBatalla.Batalla;

public abstract class Arena {
    protected String nombre;
    protected String ubicacion;
    protected String dificultad;
    protected int PREMIO;
    protected boolean ocupada = false;

    public Arena(String nombre, String ubicacion, String dificultad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDetalle() {
        return ubicacion;
    }

    public int getPremio(){
        if (this.dificultad.equals("Fácil")) {
            return (PREMIO*.9); // Descuento para dificultad fácil
        }
        else{
            if (this.dificultad.equals("Medio"))
                return (PREMIO*1.2); // Aumento para dificultad media
            else
                return (PREMIO*1.5); // Dificultad difícil
        }
    }

    public synchronized boolean estaLibre() {
        return !ocupada;
    }

    public synchronized void ocupar() {
        ocupada = true;
    }

    public synchronized void liberar() {
        ocupada = false;
    }

}
