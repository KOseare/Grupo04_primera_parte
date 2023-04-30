package negocio;

import java.util.Date;

import negocio.excepciones.SaldoInsuficienteExeception;

/**
 * Clase que representa un medio de pago de tipo Efectivo que se aplica sobre la factura.
 */
public class Efectivo extends MedioDePagoDecorator {

    /**
     * <b>pre:</b>
     * <ul>
     * <li>La factura no debe ser null.</li>
     * </ul>
     * 
     * @param factura Factura a la que se le aplicara el medio de pago efectivo.
     */
    public Efectivo(IFactura factura) {
        super();
        assert factura != null : "La factura es null";

        this.factura = factura;
        this.descuento = 0.2;
    }

    @Override
    public double getDescuento() {
        return this.descuento;
    }

    @Override
    public double getImporteNeto() {
        return this.factura.getImporteNeto() * (1-this.descuento);
    }



    /**
     * @param contrato
     */

 



    @Override
    public String detalle() {
        return  super.detalle() + " Pago en Efectivo, descuento de " + this.descuento*100 + "%, total a pagar: " + this.getImporteNeto();
    }


    @Override
    public boolean isPagado() {
        return this.factura.isPagado();
    }

}
