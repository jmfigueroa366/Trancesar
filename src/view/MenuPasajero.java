package VIEW;

import MODEL.Pasajero;
import SERVICES.PasajeroService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class MenuPasajero {

    public void mostrarMenu() throws IOException, Exception {

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        PasajeroService ps = new PasajeroService();

        int opc = 0;

        while (opc != 5) {

            String m = null;

            System.out.println("==============================");
            System.out.println("|     GESTIÓN DE PASAJERO     |");
            System.out.println("==============================");
            System.out.println("|  1. Registrar pasajero      |");
            System.out.println("|  2. Buscar pasajero         |");
            System.out.println("|  3. Listar pasajeros        |");
            System.out.println("|  4. Eliminar pasajero       |");
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
                    System.out.println("REGISTRAR PASAJERO");

                    System.out.print("Numero de identidad: ");
                    String id = leer.readLine();

                    System.out.print("Nombre: ");
                    String nombre = leer.readLine();

                    System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(leer.readLine());

                    System.out.print("Tipo (Estudiante/Regular): ");
                    String tipo = leer.readLine();

                    ps.validarRegistro(id, nombre, fechaNacimiento, tipo);
                    break;

                case 2:
                    System.out.print("Ingrese ID: ");
                    m = leer.readLine();

                    try {
                        Pasajero p = ps.buscarPasajero(m);

                        System.out.println("PASAJERO ENCONTRADO");
                        System.out.println("ID: " + p.getId());
                        System.out.println("Nombre: " + p.getNombre());
                        System.out.println("Fecha: " + p.getFechaNacimiento());
                        System.out.println("Tipo: " + p.getClass().getSimpleName());

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Pasajero> lista = ps.listarPasajero();

                        for (Pasajero pa : lista) {
                            System.out.println("ID: " + pa.getId());
                            System.out.println("Nombre: " + pa.getNombre());
                            System.out.println("Fecha: " + pa.getFechaNacimiento());
                            System.out.println("----------------------");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese ID: ");
                    m = leer.readLine();

                    try {
                        ps.eliminarPasajero(m);
                        System.out.println("REGISTRO ELIMINADO");
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
