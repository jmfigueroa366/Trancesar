/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import MODEL.Persona;
import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public abstract class Pasajero extends Persona {
    final double Descuento;

    public Pasajero(double Descuento, String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre, fechaNacimiento);
        this.Descuento = Descuento;
    }
    
    public abstract double calcularDescuento(); 

}
