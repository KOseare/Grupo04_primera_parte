package negocio;


/**
 * Interfaz encargada de modelar el comportamiento que debe tener una 
 * promoci�n. Los m�todos que define se usan para implementar el Double Dispatch.
 */
public interface Promocion extends Cloneable {
    public double PromoVivienda(double d);
    public double PromoComercio(double d);
    public Object clone() throws CloneNotSupportedException;
}