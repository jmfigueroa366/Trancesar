/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String descuento) {
        super(descuento);
    }

    public PasajeroAdultoMayor(String descuento, int id, String nombre, int edad) {
        super(descuento, id, nombre, edad);
    }

    @Override
    public double calcularDescuento() {
        return 0.30; //30% de Descuento 
    }

}
