/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(double Descuento, String id, String nombre, LocalDate fechaNacimiento) {
        super(0.15, id, nombre, fechaNacimiento);
    }
    
    @Override
    public double calcularDescuento() {
        return Descuento;
    }

    @Override
    public void imprimirDetalle() {
         int edad = Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
         System.out.println("PASAJERO ESTUDIANTE");
         System.out.println("Cedula: " + getId());
         System.out.println("Nombre: " + getNombre());
         System.out.println("Edad: " + edad + "años");
         System.out.println("Descuento: " + (calcularDescuento()*100) + "%");
    }  
}
