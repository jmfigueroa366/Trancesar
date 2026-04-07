
package MODEL;

import MODEL.Pasajero;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(double Descuento, String id, String nombre, LocalDate fechaNacimiento) {
        super(0.30, id, nombre, fechaNacimiento);
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
