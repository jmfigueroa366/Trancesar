
package model;

public class Bus extends Vehiculo{

    public Bus() {
    }
    
    public Bus(int capacidad, float tarifa, String placa, Ruta ruta, boolean disponible){
        super(45, tarifa, placa, ruta, disponible);
    }
    
    @Override
    public void imprimirDetalle() {
        
        String disponibilidad = isDisponible() ? "DISPONIBLE" : "OCUPADO";
        
        System.out.println("MICROBUS");
        System.out.println("CAPACIDAD: " + "45 PASAJEROS");
        System.out.println("TARIFA: " + getTarifa());
        System.out.println("PLACA: " + getPlaca());
        System.out.println("RUTA: " + getRuta());
        System.out.println("ESTADO " + disponibilidad);
    }
    
}
