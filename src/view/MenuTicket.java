package view;

import model.Ticket;
import services.TicketService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class MenuTicket {

    public void mostrarMenu() throws IOException, Exception {

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        TicketService ts = new TicketService();

        int opc = 0;

        while (opc != 5) {

            String m = null;

            System.out.println("==============================");
            System.out.println("|       GESTIÓN DE TICKET     |");
            System.out.println("==============================");
            System.out.println("|  1. Registrar ticket        |");
            System.out.println("|  2. Buscar ticket           |");
            System.out.println("|  3. Listar ticket           |");
            System.out.println("|  4. Eliminar ticket         |");
            System.out.println("|  5. Salir                   |");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");

            try {
                opc = Integer.parseInt(leer.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                opc = 0;
            }

            switch (opc) {

                case 1:
                    System.out.println("REGISTRAR TICKET");

                    System.out.print("Numero de Ticket: ");
                    String numTicket = leer.readLine();

                    System.out.print("Ciudad de Origen: ");
                    String origenCiudad = leer.readLine();

                    System.out.print("Ciudad de Destino: ");
                    String destinoCiudad = leer.readLine();

                    System.out.print("ID Pasajero: ");
                    String idP = leer.readLine();

                    System.out.print("Placa Vehiculo: ");
                    String placaVehiculo = leer.readLine();

                    ts.validarRegistro(numTicket, origenCiudad, destinoCiudad, idP, placaVehiculo);
                    break;

                case 2:
                    System.out.print("Ingrese Numero de Ticket: ");
                    m = leer.readLine();

                    try {
                        Ticket t = ts.BuscarNumeroTicket(m);

                        System.out.println("TICKET ENCONTRADO");
                        System.out.println("Numero: " + t.getNumeroTicket());
                        System.out.println("Fecha: " + t.getFechaCompra());
                        System.out.println("Origen: " + t.getOrigenCiudad());
                        System.out.println("Destino: " + t.getDestinoCiudad());
                        System.out.println("Precio: " + t.getPrecioFinal());
                        System.out.println("Pasajero: " + t.getPasajero().getId());
                        System.out.println("Vehiculo: " + t.getVehiculo().getPlaca());

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Ticket> lista = ts.listarTicket();

                        for (Ticket tl : lista) {
                            System.out.println("Numero: " + tl.getNumeroTicket());
                            System.out.println("Fecha: " + tl.getFechaCompra());
                            System.out.println("Origen: " + tl.getOrigenCiudad());
                            System.out.println("Destino: " + tl.getDestinoCiudad());
                            System.out.println("Precio: " + tl.getPrecioFinal());
                            System.out.println("Pasajero: " + tl.getPasajero().getId());
                            System.out.println("Vehiculo: " + tl.getVehiculo().getPlaca());
                            System.out.println("----------------------");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese Numero de Ticket: ");
                    m = leer.readLine();

                    try {
                        ts.eliminarTicket(m);
                        System.out.println("TICKET ELIMINADO");
                    } catch (Exception e) {
                        System.out.println("NO EXISTE ESTE REGISTRO");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
