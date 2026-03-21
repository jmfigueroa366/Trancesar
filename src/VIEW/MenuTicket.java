/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;
import DAO.TicketDAO;
import MODEL.Ticket;
import SERVICES.TicketService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class MenuTicket {
     public static void main(String[] args) throws IOException, Exception {
        
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        
        TicketService ts = new TicketService();
        
        int opc = 0;
        
        while (opc !=5) {  
            
        String m = null; 
            
        System.out.println("==============================");
        System.out.println("|       GESTIÓN DE TICKET          |");
        System.out.println("==============================");
        System.out.println("|  1. Registrar ticket             |");
        System.out.println("|  2. Buscar ticket                |");
        System.out.println("|  3. Listar ticket                |");
        System.out.println("|  4. Eliminar ticket              |");
        System.out.println("|  5. Salir                          |");
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
                //ingreso de registros
                System.out.println("==============================");
                System.out.println("|       REGISTRAR TICKET           |");
                System.out.println("==============================");
                
                System.out.println("|  Numero de Ticket: ");
                String NumTicket = leer.readLine();
                
                System.out.println("|  Ciudad de Origen:  ");
                String OrigenCiudad = leer.readLine();
                
                System.out.println("|  Ciudad de Destino: ");
                String DestinoCiudad = leer.readLine();
                
                System.out.println("| Numero de identidad del pasajero: ");
                String IdP = leer.readLine();
                
                System.out.println("| Placa del vehiculo: ");
                String PlacaVehiculo = leer.readLine();
                
                ts.validarRegistro(NumTicket, OrigenCiudad, DestinoCiudad, IdP, PlacaVehiculo);
                
                break;
                
            case 2:
                
                System.out.println("==============================");
                System.out.println("|         BUSCAR TICKET            |");
                System.out.println("==============================");
                System.out.print("|  Ingrese el numero de identidad: ");
                m = leer.readLine();
                System.out.println("==============================");
                
                try {
                    
                    Ticket t = ts.BuscarNumeroTicket(m);
                    
                        System.out.println("╔=============================");
                        System.out.println("|       TICKET ENCONTRADO          |");
                        System.out.println("==============================");
                        System.out.println("|  Numero de Ticket:                 " + t.getNumeroTicket());
                        System.out.println("|  Fecha de compra:                  " + t.getFechaCompra());
                        System.out.println("|  Ciudad de Origen:                 " + t.getOrigenCiudad());
                        System.out.println("|  Ciudad de Destino:                " + t.getDestinoCiudad());
                        System.out.println("|  Precio Final del ticket:          " + t.getPrecioFinal());
                        System.out.println("|  Numero de identidad del pasajero: " + t.getPasajero().getId());
                        System.out.println("|  Placa del vehiculo:               " + t.getVehiculo().getPlaca());
                        System.out.println("==============================");

                    
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
                break;
                
            case 3:
                
                try {
                    
                    List <Ticket> lista = ts.listarTicket();
                    
                    System.out.println("==============================");
                    System.out.println("|         LISTA DE TICKET         |");
                    System.out.println("==============================");
                    
                    for (Ticket tl : lista) {
                        System.out.println("|  Numero de Ticket:                 " + tl.getNumeroTicket());
                        System.out.println("|  Fecha de compra:                  " + tl.getFechaCompra());
                        System.out.println("|  Ciudad de Origen:                 " + tl.getOrigenCiudad());
                        System.out.println("|  Ciudad de Destino:                 " + tl.getDestinoCiudad());
                        System.out.println("|  Precio Final del ticket:          " + tl.getPrecioFinal());
                        System.out.println("|  Numero de identidad del pasajero: " + tl.getPasajero().getId());
                        System.out.println("|  Placa del vehiculo:               " + tl.getVehiculo().getPlaca());
                        System.out.println("===========================");
                    }
                                                                        //
                    System.out.println("===============================");
                    
                } catch (Exception e) {
                    System.out.println("Error de tipo " + e.getMessage());
                }
                
                break;
            
            case 4:
                
                        System.out.println("==============================");
                        System.out.println("|        ELIMINAR TICKET            |");
                        System.out.println("==============================");
                        System.out.print("|  Ingrese la numero de identidad: ");
                        m = leer.readLine();
                        System.out.println("==============================");
                        
                        try {
                            ts.eliminarTicket(m);
                            System.out.println("TICKET ELIMINADO");
                        } catch (Exception e) {
                            System.out.println("NO EXISTE ESTE REGISTRO ");
                        }
                
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
