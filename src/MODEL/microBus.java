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
        super(capacidad, tarifa, placa, ruta, disponible);
    }
}
