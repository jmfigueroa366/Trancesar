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
public class PasajeroAdultoMayor extends Pasajero {
    
    
        public PasajeroAdultoMayor(int id, String nombre, LocalDate fechaNacimiento, double Descuento) {
        super(id, nombre, fechaNacimiento, 0.30);
    }
    
    @Override
    public double calcularDescuento() {
        return Descuento;
    }

    @Override
    public void imprimirDetalle() {
         int edad = Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
         System.out.println("PASAJERO ADULTO MAYOR");
         System.out.println("Cedula: " + getId());
         System.out.println("Nombre: " + getNombre());
         System.out.println("Edad: " + edad + "años");
         System.out.println("Descuento: " + (calcularDescuento()*100) + "%");
    } 
}
