package negocio;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import java.util.GregorianCalendar;

import negocio.excepciones.EstadoException;
import negocio.excepciones.SaldoInsuficienteExeception;

import presentacion.MainControlador;

/**
 * Clase que representa al sistema general de la empresa.
 */
public class SistemaSeguridad {
    private ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
    private ArrayList<Persona> clientes = new ArrayList<Persona>();
    private static SistemaSeguridad _instancia = null;
    private ServicioTecnico serviciotecnico;

    private SistemaSeguridad() {
    }

    public void setClientes(ArrayList<Persona> clientes) {
        this.clientes = clientes;
    }

    public static SistemaSeguridad getSistema() {
        if (_instancia == null) {
        	_instancia = new SistemaSeguridad();
        	_instancia.serviciotecnico = new ServicioTecnico();
        }

        return _instancia;
    }

    public void agregarCliente(Persona cliente) {
        clientes.add(cliente);
    }

    public void eliminarCliente(Persona cliente) {
        clientes.remove(cliente);
    }

    public ArrayList<Persona> getClientes() {
        return clientes;
    }

    public void setServiciotecnico(ServicioTecnico serviciotecnico) {
        this.serviciotecnico = serviciotecnico;
    }

    public ServicioTecnico getServiciotecnico() {
        return serviciotecnico;
    }

    /**
     * Genera el reporte detallado de la factura.
     *
     * @return Reporte de la factura
     */
    public String generarReporte() {
        String reporte = "";
        for (Persona cliente : this.clientes) {
            reporte += cliente.ultimaFactura().detalle();
        }
        return reporte;
    }

    /**
     * Genera el clon de una factura.
     *
     * <b>pre:</b>
     * <ul>
     * <li>La factura debe estar instanciada (no es null).</li>
     * </ul>
     *
     * <b>post:</b>
     * <ul>
     * <li>regresa un clon de la factura solicitada o regresa una excepcion.</li>
     * </ul>
     *
     * @param factura Factura a duplicar
     * @return Factura duplicada
     */
    public IFactura solicitarDuplicado(Factura factura) throws CloneNotSupportedException {
        Factura clon;
        try {
            clon = (Factura) factura.clone();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw e;
        }
    }
    
    public void pagarFactura (Persona persona,IFactura factura,double monto) throws SaldoInsuficienteExeception,
                                                                                     EstadoException {
           persona.pagarFactura(factura, monto);
    }
    
    public void agregarContrato (Persona persona, String tipo, Domicilio domicilio, boolean camara, boolean antipanico, boolean movil) throws EstadoException {
    	Contratacion contrato;
    	ArrayList<ServicioAdicional> servicios = new ArrayList<>();
    	if (tipo.equals("Comercio")) {
    		contrato = new MonitoreoComercio(domicilio);
    	} else {
    		contrato = new MonitoreoVivienda(domicilio);
    	}
    	
    	if (camara)
    		servicios.add(new Camara(1));
    	if (antipanico)
    		servicios.add(new BotonAntiPanico(1));
    	if (movil)
    		servicios.add(new MovilDeAcompaniamiento(new GregorianCalendar(1990,01,01,00,00), new GregorianCalendar(1990,01,01,23,59)));
    	
    	contrato.setServiciosAdicionales(servicios);
    	persona.agregarContrato(contrato);
    }
    
    public void bajaContratacion (Persona persona, Contratacion c) throws EstadoException {
    	persona.darBajaServicio(c);
    }
    
    public void solicitarTecnico (MainControlador observer){

            new Thread(new ServicioTecnicoRunnable(this.serviciotecnico,observer)).start();
    }
    
    public void altaTecnico (String nombre) {
    	Tecnico tecnico = new Tecnico(nombre);
    	this.serviciotecnico.agregarTecnico(tecnico);
    }
    
    public void nuevoAbonado (String tipo, String nombre, String dni) {
    	Persona p;
    	if (tipo.equals("Juridica")) {
    		p = new PersonaJuridica(nombre, dni);
    	} else {
    		p = new PersonaFisica(nombre, dni);
    	}
    	this.clientes.add(p);
    }

    public void nuevoUsuario(String usuario, String clave, Persona persona) {
    	Usuarios u = new Usuarios(usuario, clave, persona);
    	this.usuarios.add(u);
    }

    public void eliminarUsuario (Usuarios u) {
    	this.usuarios.remove(u);
    }

    public boolean validarUsuario(String usuario, String clave) {
        boolean res = false;
    	for (Usuarios u : this.usuarios) {
            if (u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                res = true;
                break;
            }
    	}
        return res;
    }

    public Persona getPersonaUsuario(String usuario, String clave){
        Persona persona = null;
        for (Usuarios u : this.usuarios) {
            if (u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                persona = u.getPersona();
                break;
            }
        }
        return persona;
    }
    public void actualizarMes () {
    	for (Persona persona : this.clientes) {
        persona.facturacionMensual();
    }
    }
}
