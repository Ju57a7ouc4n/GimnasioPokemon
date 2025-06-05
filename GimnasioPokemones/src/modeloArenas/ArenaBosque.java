package modeloArenas;

public class ArenaBosque extends Arena {

    public ArenaBosque(String nombre) {
        super(nombre);
        this.premio = 700;
    }

	@Override
	public String getDetalle() {
		return "Una arena cubierta por un manto verde, con árboles altos, enredaderas colgantes y un suelo blando cubierto de hojas. La luz del sol se filtra entre las copas, creando una atmósfera tranquila pero misteriosa. Los sonidos de criaturas del bosque resuenan constantemente. \n";
	}
}
