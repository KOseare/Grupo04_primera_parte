package negocio;

import java.util.ArrayList;

public class PersonaJuridica extends Persona {
    @Override
    public void generarFactura() {
        // TODO Implement this method
}

    @Override
    public ArrayList<Double> recibeDescuento(ArrayList<Contratacion> contratos) {
       ArrayList<Double> valores = new ArrayList<Double>();
        for(int i = 0;i<contratos.size();i++){
            if(i>2){
                valores.add(0.5);
            }else{
                valores.add(1.0);
            }
        }
        return valores;
    }
    @Override
    public String toString(){
        return "Tipo: Persona Juridica";
    }
}
