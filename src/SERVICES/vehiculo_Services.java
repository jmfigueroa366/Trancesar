/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;

import MODEL.Vehiculo;
import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;

/**
 *
 * @author alvar
 */
public class vehiculo_Services {

    public void capacidad(int capacidad) {
        
        Vehiculo v = null;
        
        if (v instanceof Bus && v.getCapacidad()>45) {
            System.out.println("El bus no puede tener mas de 45 pasajeros");
        }
        
        if (v instanceof Buseta && v.getCapacidad()>19) {
            System.out.println("La buseta no puede tener mas de 19 pasajeros");
        }
        
        if (v instanceof MicroBus && v.getCapacidad()>25) {
            System.out.println("El microBus no puede tener una capacidad mayor a 25 pasajeros");
        }
        
    }

}
