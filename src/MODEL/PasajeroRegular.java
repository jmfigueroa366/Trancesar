/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre, fechaNacimiento);
    }
    
    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
         int edad = Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
         System.out.println("DATOS DEL PASAJERO");
         System.out.println("Cedula: " + getId());
         System.out.println("Nombre: " + getNombre());
         System.out.println("Edad: " + edad + "años");
         System.out.println("Descuento: " + (calcularDescuento()*100) + "%");
    }  
}
