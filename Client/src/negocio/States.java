package negocio;

import negocio.excepciones.EstadoException;
import negocio.excepciones.SaldoInsuficienteExeception;

public interface States {
    
    public void pagarFactura(IFactura factura,double importe) throws SaldoInsuficienteExeception, EstadoException;
    public void agregarContrato(Contratacion contrato);
    public void darBajaServicio(Contratacion contrato);
    public void actualizarEstado();
}
