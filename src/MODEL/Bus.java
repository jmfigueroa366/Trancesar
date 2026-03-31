/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author alvar
 */
public class Bus extends Vehiculo{

    public Bus() {
    }
    
    public Bus(int capacidad, float tarifa, String placa, Ruta ruta, boolean disponible){
        super(45, tarifa, placa, ruta, disponible);
    }
    
    @Override
    public void imprimirDetalle() {
        
    }
    
}
