/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author alvar
 */
public class Buseta extends Vehiculo{

    public Buseta() {
    }
    
    public Buseta(int capacidad, float tarifa, String placa, Ruta ruta, boolean disponible){
        super(19, tarifa, placa, ruta, disponible);
    }
    
    @Override
    public void imprimirDetalle() {
        
        String disponibilidad = isDisponible() ? "DISPONIBLE" : "OCUPADO";
        
        System.out.println("MICROBUS");
        System.out.println("CAPACIDAD: " + "19 PASAJEROS");
        System.out.println("TARIFA: " + getTarifa());
        System.out.println("PLACA: " + getPlaca());
        System.out.println("RUTA: " + getRuta());
        System.out.println("ESTADO " + disponibilidad);
        
    }
    
}
