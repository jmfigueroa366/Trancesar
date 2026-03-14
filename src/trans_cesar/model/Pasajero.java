/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public abstract class Pasajero extends Persona {

    public Pasajero(int id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre, fechaNacimiento);
    }
    
    public abstract double calcularDescuento(); 

}
