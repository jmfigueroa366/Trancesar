/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author alvar
 */
public class MenuReservas {

    public MenuReservas() throws IOException {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        
        int opcion=0;
        
        while (opcion!=6) {
        
            System.out.println("\n===== MENÚ DE RESERVAS =====");
                System.out.println("1. Crear nueva reserva");
                System.out.println("2. Cancelar reserva");
                System.out.println("3. Listar reservas activas");
                System.out.println("4. Historial de reservas por pasajero");
                System.out.println("5. Convertir reserva a ticket");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = Integer.parseInt(leer.readLine());

                switch (opcion) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                default:

                    System.out.println("Error al ingresar la opcion ");

            }
            
       }
            
    }
}
