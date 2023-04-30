package negocio;

/**
 * Clase que representa la implementacion Promocion de tipo Dorada
 */
public class Dorada implements Promocion {

    /**
     * Retorna el descuento de la promocion para una contrataci�n de tipo Vivienda.
     * 
     * <b>pre:</b>
     * <ul>
     * <li>El precio base (d) es mayor a 0.</li>
     * </ul>
     * 
     * @param d El precio base de la contrataci�n
     * @return El descuento promocional
     */
    @Override
    public double PromoVivienda(double d) {
        assert d >= 0 : "El precio base debe ser mayor o igual a 0";
        return 1500;
    }

    /**
     * Retorna el descuento de la promocion para una contrataci�n de tipo Comercio.
     * 
     * <b>pre:</b>
     * <ul>
     * <li>El precio base (d) es mayor a 0.</li>
     * </ul>
     * 
     * @param d El precio base de la contrataci�n
     * @return El descuento promocional
     */
    @Override
    public double PromoComercio(double d) {
        assert d >= 0 : "El precio base debe ser mayor o igual a 0";
        return 2500;
    }
    
    @Override
    public String toString(){
        return "Dorada ";
    }
    
}
