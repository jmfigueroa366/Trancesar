package view;

import dao.ConductorDAO;
import model.Conductor;
import services.ConductorService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class MenuConductor {

    public void mostrarMenu() throws IOException, Exception {

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        ConductorService cs = new ConductorService();

        int opc = 0;

        while (opc != 5) {

            String m = null;

            System.out.println("==============================");
            System.out.println("|   GESTIÓN DE CONDUCTOR     |");
            System.out.println("==============================");
            System.out.println("|  1. Registrar conductor    |");
            System.out.println("|  2. Buscar conductor       |");
            System.out.println("|  3. Listar conductor       |");
            System.out.println("|  4. Eliminar conductor     |");
            System.out.println("|  5. Salir                  |");
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
                    System.out.println("REGISTRAR CONDUCTOR");

                    System.out.print("Numero de identidad: ");
                    String id = leer.readLine();

                    System.out.print("Nombre: ");
                    String nombre = leer.readLine();

                    System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(leer.readLine());

                    System.out.print("Numero Licencia: ");
                    String numeroLicencia = leer.readLine();

                    System.out.print("Categoria Licencia: ");
                    String categoriaLicencia = leer.readLine();

                    cs.validarRegistro(numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento);
                    break;

                case 2:
                    System.out.print("Ingrese ID: ");
                    m = leer.readLine();

                    try {
                        Conductor c = cs.buscarConductor(m);

                        System.out.println("CONDUCTOR ENCONTRADO");
                        System.out.println("ID: " + c.getId());
                        System.out.println("Nombre: " + c.getNombre());
                        System.out.println("Fecha: " + c.getFechaNacimiento());
                        System.out.println("Licencia: " + c.getNumeroLicencia());
                        System.out.println("Categoria: " + c.getCategoriaLicencia());

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<Conductor> lista = cs.listarConductor();

                        for (Conductor cl : lista) {
                            System.out.println("ID: " + cl.getId());
                            System.out.println("Nombre: " + cl.getNombre());
                            System.out.println("Fecha: " + cl.getFechaNacimiento());
                            System.out.println("Licencia: " + cl.getNumeroLicencia());
                            System.out.println("Categoria: " + cl.getCategoriaLicencia());
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
                        cs.eliminarConductor(m);
                        System.out.println("CONDUCTOR ELIMINADO");
                    } catch (Exception e) {
                        System.out.println("NO EXISTE");
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
