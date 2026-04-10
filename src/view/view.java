package view;
import services.ReservaService;
import services.TicketService;
import dao.ReservaDAO;
import dao.TicketDAO;
import dao.PasajeroDAO;
import dao.VehiculoDAO;
import java.util.Scanner;

public class view {
    Scanner sc = new Scanner(System.in);
    
    public void mostrarMenu() throws Exception {
        
        // Inicializar DAOs y servicios una sola vez
        ReservaDAO reservaDAO = new ReservaDAO();
        PasajeroDAO pasajeroDAO = new PasajeroDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        TicketDAO ticketDAO = new TicketDAO();
        TicketService ticketService = new TicketService(ticketDAO, pasajeroDAO, vehiculoDAO);

        ReservaService rs = new ReservaService(reservaDAO, pasajeroDAO, vehiculoDAO, ticketDAO);
        int opcion=0;
        
        while (opcion!=8) {            
            
            
        do {
            System.out.println("\n=== SISTEMA TRANSCESAR ===");
            System.out.println("1. Menu conductor");
            System.out.println("2. Menu pasajero");
            System.out.println("3. Menu ticket");
            System.out.println("4. Menu rutas");
            System.out.println("5. Menu vehiculos");
            System.out.println("6. Menu reservas");
            System.out.println("7.Menu reportes");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    new MenuConductor().mostrarMenu();
                    break;
                case 2:
                    new MenuPasajero().mostrarMenu();
                    break;
                case 3:
                   new MenuTicket().mostrarMenu(ticketService);
                    break;
                case 4:
                    new menuRutas().mostrarMenu();
                    break;
                case 5:
                    new menuVehiculo().mostrarMenu();
                    break;
                case 6:
                new MenuReservas().mostrarMenu(rs, ticketService);
                    break;
                case 7:
                    new MenuReportes().menuReportes(ticketService);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 8); 
      }
    }
    
    public static void main(String[] args) throws Exception {
        view menu = new view();
        menu.mostrarMenu();
    }
}