package trans_cesar;

import java.util.Scanner;

public class view {
      Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== SISTEMA TRANSCESAR ===");
            System.out.println("1. Registrar Vehículo");
            System.out.println("2. Registrar Pasajero");
            System.out.println("3. Vender Ticket");
            System.out.println("4. Ver Reportes");
            System.out.println("0. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    registrarPasajero();
                    break;
                case 3:
                    venderTicket();
                    break;
                case 4:
                    mostrarReportes();
                    break;
            }

        } while (opcion != 0);
}
}