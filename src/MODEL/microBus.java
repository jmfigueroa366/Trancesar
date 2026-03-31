/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author alvar
 */
public class MicroBus extends Vehiculo{

    public MicroBus() {
    }
    
    public MicroBus(int capacidad, float tarifa, String placa, Ruta ruta, boolean disponible){
        super(25, tarifa, placa, ruta, disponible);
    }

    @Override
    public void imprimirDetalle() {
        
        String disponibilidad = isDisponible() ? "DISPONIBLE" : "OCUPADO";
        
        System.out.println("MICROBUS");
        System.out.println("CAPACIDAD: " + "25 PASAJEROS");
        System.out.println("TARIFA: " + getTarifa());
        System.out.println("PLACA: " + getPlaca());
        System.out.println("RUTA: " + getRuta());
        System.out.println("ESTADO " + disponibilidad);
    }
}
