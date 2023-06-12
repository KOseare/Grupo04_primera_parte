package negocio;

import java.util.Iterator;

import negocio.excepciones.EstadoException;
import negocio.excepciones.SaldoInsuficienteExeception;

public class Moroso implements States {
    private PersonaFisica personaFisica;


    public Moroso() {
    }

    public void setPersonaFisica(PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
    }

    public PersonaFisica getPersonaFisica() {
        return personaFisica;
    }
    //
    public Moroso(PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
    }

    @Override
    public void pagarFactura(IFactura factura, double importe) throws SaldoInsuficienteExeception, EstadoException {
        // recargo del 30% 
        factura.pagarFactura(importe * 1.3);//+30% crear nuevo recargo(con clase recargo)?
        actualizarEstado();
    }

    @Override
    public void agregarContrato(Contratacion contrato) {
        // no puede
    }

    @Override
    public void darBajaServicio(Contratacion contrato) {
        // no puede
    }

    @Override
    public void actualizarEstado() {
        int cont=0;
                Iterator<Factura> it = personaFisica.getFacturas().descendingIterator();
                it.next();
                if(it.next().isPagado())
                        personaFisica.setEstado(new ConContratacionesState(this.personaFisica));

    }
    public String toString(){
        return "Moroso";
    }
}
