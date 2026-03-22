/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import MODEL.Ticket;
import SERVICES.TicketService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ANDREA CAROLINA
 */
public class MenuReportes {
    
    public void menuReportes () throws Exception {
    
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        
        TicketService ts = new TicketService();
        
        int opc = 0;
        
        while (opc !=5) {
            
        System.out.println("==============================");
        System.out.println("|        GESTIÓN DE REPORTES          |");
        System.out.println("==============================");
        System.out.println("|  1. Tickets por fecha especifica  |");
        System.out.println("|  2. Tickets por tipo de vehiculo  |");
        System.out.println("|  3. Tickets por tipo de pasajero  |");
        System.out.println("|  4. Resumen del día               |");
        System.out.println("|  5. Salir                         |");
        System.out.println("==============================");
        System.out.print("   Seleccione una opción: ");
        
            try {
                
                opc=Integer.parseInt(leer.readLine());
                
            } catch (NumberFormatException e) {
                
                System.out.println("Error de tipo " + e.getMessage());
                opc=0;
            }
        
        switch (opc) {
            
            case 1:
                //Buscar tickets vendidas por una fecha en especifica
                System.out.println("==============================");
                System.out.println("|       FECHA ESPECIFICA       |");
                System.out.println("==============================");
                
                System.out.println("|  Ingrese la fecha (YYYY/MM/DD): ");
                LocalDate fecha = LocalDate.parse(leer.readLine());
                
                List<Ticket>porFecha = ts.consultarPorFecha(fecha);
        
                if (porFecha.isEmpty()) {
                System.out.println("No hay tickets en esta fecha");
                } else {
                    for (Ticket t: porFecha) {
                        t.imprimirDetalle();
                        System.out.println("-----------------------");
                    }
                }
                break;
                
            case 2:
                
                int tipoV=0;
                // Buscar tickets vendidas por tipo de vehiculo
                System.out.println("==============================");
                System.out.println("|       TIPO DE VEHICULO       |");
                System.out.println("==============================");
                System.out.println("1. Bus:      ");
                System.out.println("2. MicroBus: ");
                System.out.println("3. Buseta:   ");
                
                try {
                     tipoV= Integer.parseInt(leer.readLine());
                } catch (Exception e) {
                    System.out.println("Error en ingreso de datos " + e);
                }
                String tipoVehiculo = "";
                
                switch (tipoV) {
                    case 1:
                        tipoVehiculo = "Bus";
                        break;
                    case 2:
                        tipoVehiculo = "MicroBus";
                        break;
                    case 3:
                        tipoVehiculo = "Buseta";
                        break;
                    default:
                        System.out.println("Opcion invalidad. (1 a 3)");
                        break;
                }
        
                List<Ticket>porVehiculo = ts.consultarPorTipoVehiculo(tipoVehiculo);
                
                if (porVehiculo.isEmpty()) {
                    System.out.println("Este vehiculo no ha reportado tickets");
                    } else {
                        for (Ticket t: porVehiculo) {
                        t.imprimirDetalle();
                        System.out.println("-----------------------");
                    }
                }
                break;
                
            case 3:
             // Buscar tickets vendidas por tipo de pasajero
                System.out.println("==============================");
                System.out.println("|       TIPO DE PASAJERO       |");
                System.out.println("==============================");
                System.out.println("1. Pasajero Regular:      ");
                System.out.println("2. Pasajero Estudiante:   ");
                System.out.println("3. Pasajero Adulto Mayor: ");
                
                String tipoP = leer.readLine();
                
                String p = "";
                
                switch (tipoP) {
                    case "1":
                        p = "Pasajero Regular";
                        break;
                    case "2":
                        p = "Pasajero Estudiante";
                        break;
                    case "3":
                        p = "Pasajero Adulto Mayor";
                        break;
                    default:
                        System.out.println("Opcion invalidad. (1 a 3)");
                        break;
                }
        
                List<Ticket>porPasajero = ts.consultarPorTipoPasajero(p);
                
                if (porPasajero.isEmpty()) {
                    System.out.println("Este pasajero no ha reportado tickets");
                    } else {
                        for (Ticket t: porPasajero) {
                        t.imprimirDetalle();
                        System.out.println("-----------------------");
                    }
                }
                break;
            
            case 4:
                ts.resumenPorDia();
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("INGRESE UNA OPCION DEL MENÚ");
        }
        }
    }
}
