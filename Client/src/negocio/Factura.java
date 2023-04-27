package negocio;

import java.util.ArrayList;
import java.util.Date;

public class Factura implements Cloneable,IFactura {
    private double monto,descuentos;
    private Date fecha;
    private boolean pagado;

    private ArrayList<Contratacion> contratos = new ArrayList<Contratacion>();
    

    public Factura(double importe, Date fecha) {
        this.monto = importe;
        this.fecha = fecha;
        this.pagado = false;
        this.descuentos = 0;
    }

    public double getMonto() {
        return monto;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public double getDescuentos() {
        return descuentos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isPagado() {
        return pagado;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Factura clon;
        try {
            clon = (Factura) super.clone();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw e;
        }

    }
}
