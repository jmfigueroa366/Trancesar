package view;


import model.Reserva;
import services.ReservaService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import services.TicketService;
/**
 *
 * @author alvar
 */
public class MenuReservas {

    public void mostrarMenu(ReservaService rs, TicketService ticketService) throws IOException {
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

                try {
                    opcion = Integer.parseInt(leer.readLine());

                } catch (Exception e) {
                    
                    System.out.println("Error en ingreso de opciones "+ e);
                    
                }
                switch (opcion) {
                
                case 1:
                    System.out.println("\n===== CREAR RESERVA =====");

                    System.out.println("Ingrese codigo de reserva");
                    String codigo = leer.readLine();

                    System.out.println("ID del pasajero");
                    String idp = leer.readLine();

                    System.out.println("Ingrese la placa del vehiculo");
                    String placa = leer.readLine();

                    System.out.print("Fecha de reserva (YYYY-MM-DD): ");
                    String fecha = leer.readLine();

                        try {
                            rs.validarRegistro(codigo, idp, placa, fecha);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error de validación: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    break;    
                    
                case 2:
                    
                    System.out.println("\n===== CANCELAR RESERVA =====");
                    
                    System.out.println("Ingrese el codigo de la reserva");
                    String codigo_c = leer.readLine();
                    
                    try {
                            rs.cancelarReserva(codigo_c);
                            
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error de validación: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                case 3:
                    
                       System.out.println("\n===== RESERVAS ACTIVAS =====");
                        try {
                            List<Reserva> activas = rs.listarActivas();

                            if (activas.isEmpty()) {
                                System.out.println("No hay reservas activas registradas.");
                            } else {
                                System.out.printf("%-10s %-15s %-20s %-15s %-12s%n",
                                    "Código", "ID Pasajero", "Nombre Pasajero", "Placa", "Fecha");
                                System.out.println("-".repeat(75));

                                for (Reserva r : activas) {
                                    System.out.printf("%-10s %-15s %-20s %-15s %-12s%n",
                                        r.getCodigo(),
                                        r.getPasajero().getId(),
                                        r.getPasajero().getNombre(),
                                        r.getVehiculo().getPlaca(),
                                        r.getFecha_reserva()
                                    );
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                case 4:
                    
                        System.out.println("\n===== HISTORIAL POR PASAJERO =====");
                        System.out.print("Ingrese el ID del pasajero: ");
                        String idBuscar = leer.readLine();

                        try {
                            List<Reserva> historial = rs.historialPorPasajero(idBuscar);

                            if (historial.isEmpty()) {
                                System.out.println("No hay reservas para este pasajero.");
                            } else {
                                System.out.printf("%-10s %-12s %-12s %-12s %-10s%n",
                                    "Código", "Placa", "F. Reserva", "F. Creación", "Estado");
                                System.out.println("-".repeat(60));

                                for (Reserva r : historial) {
                                    System.out.printf("%-10s %-12s %-12s %-12s %-10s%n",
                                        r.getCodigo(),
                                        r.getVehiculo().getPlaca(),
                                        r.getFecha_reserva(),
                                        r.getFecha_creacion(),
                                        r.isActivo() ? "Activa" : "Cancelada"
                                    );
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                case 5:
System.out.println("\n===== CONVERTIR RESERVA A TICKET =====");
    System.out.print("Código de la reserva: ");
    String codigoReserva = leer.readLine();

    System.out.print("Número del ticket: ");
    String numeroTicket = leer.readLine();

    System.out.print("Ciudad de origen: ");
    String origenCiudad = leer.readLine();

    System.out.print("Ciudad de destino: ");
    String destinoCiudad = leer.readLine();

    try {
        rs.reservaATicket(codigoReserva, numeroTicket, origenCiudad, destinoCiudad, ticketService);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    break;

                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:

                    System.out.println("Error al ingresar la opcion ");

            }
            
       }
            
    }
}
