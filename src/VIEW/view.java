package VIEW;

import java.util.Scanner;

public class view {

    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() throws Exception {

        int opcion;

        do {
            System.out.println("\n=== SISTEMA TRANSCESAR ===");
            System.out.println("1. Menu conductor");
            System.out.println("2. Menu pasajero");
            System.out.println("3.menu reportes");
            System.out.println("4. Menu ticket");
            System.out.println("5. Menu rutas");
            System.out.println("6. Menu vehiculos");
            System.out.println("7.menu reserva");
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
                    new MenuReportes().menuReportes();
                    break;

                case 4:
                    new MenuTicket().mostrarMenu();// corregido nombre
                    break;

                case 5:
                    new menuRutas().mostrarMenu();// corregido nombre
                    break;
                case 6:
                    new menuVehiculo().mostrarMenu();
                    break;
                case 7:
                    
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 8);
    }

    // 🔥 MAIN AGREGADO
    public static void main(String[] args) throws Exception {

        view menu = new view();
        menu.mostrarMenu();
    }
}