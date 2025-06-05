package Utils;

/**
 * Clase utilitaria para redondear valores a dos decimales.
 * 
 */
public class Redondear {

    /**
     * Redondea un valor a dos decimales.
     * 
     * @param valor el valor a redondear.
     * @return el valor redondeado a dos decimales.
     */
    public static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

}
