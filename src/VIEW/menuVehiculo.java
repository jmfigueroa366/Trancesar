package VIEW;

import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import MODEL.Vehiculo;
import MODEL.Ruta;
import SERVICES.rutaServices;
import SERVICES.vehiculoServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class menuVehiculo {

    public void mostrarMenu() throws IOException, Exception {

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        vehiculoServices vs = new vehiculoServices();

        int opc = 0;

        while (opc != 5) {

            String p = null;
            Vehiculo v = null;

            System.out.println("==============================");
            System.out.println("|   GESTIÓN DE VEHÍCULOS     |");
            System.out.println("==============================");
            System.out.println("|  1. Registrar vehículo     |");
            System.out.println("|  2. Buscar vehículo        |");
            System.out.println("|  3. Eliminar vehículo      |");
            System.out.println("|  4. Listar vehículos       |");
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
                    System.out.println("REGISTRAR VEHÍCULO");

                    System.out.println("Tipo de vehículo:");
                    System.out.println("1. Bus");
                    System.out.println("2. Buseta");
                    System.out.println("3. MicroBus");
                    System.out.print("Seleccione: ");
                    int tipo = Integer.parseInt(leer.readLine());

                    System.out.print("Placa: ");
                    String placa = leer.readLine();

                    rutaServices rs = new rutaServices();

                    try {
                        List<Ruta> rutas = rs.listar();

                        System.out.println("RUTAS DISPONIBLES:");
                        for (Ruta r : rutas) {
                            System.out.println(r.getCodigo() + " - " +
                                    r.getC_origen() + " → " + r.getC_destino());
                        }

                    } catch (Exception e) {
                        System.out.println("No hay rutas: " + e.getMessage());
                    }

                    System.out.print("Código de ruta: ");
                    String codigoRuta = leer.readLine();

                    System.out.print("Tarifa: ");
                    float tarifa = Float.parseFloat(leer.readLine());

                    System.out.print("Disponible (true/false): ");
                    boolean disponible = Boolean.parseBoolean(leer.readLine());

                    try {
                        Ruta rutaSeleccionada = rs.buscar(codigoRuta);

                        switch (tipo) {
                            case 1:
                                v = new Bus(45, tarifa, placa, rutaSeleccionada, disponible);
                                break;
                            case 2:
                                v = new Buseta(19, tarifa, placa, rutaSeleccionada, disponible);
                                break;
                            case 3:
                                v = new MicroBus(25, tarifa, placa, rutaSeleccionada, disponible);
                                break;
                            default:
                                System.out.println("Opción inválida");
                        }

                        if (v != null) {
                            vs.validarRegistro(v);
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.print("Ingrese placa: ");
                    p = leer.readLine();

                    try {
                        v = vs.validarBusqueda(p);

                        v.imprimirDetalle();
                        
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 3:
                    System.out.print("Ingrese placa: ");
                    p = leer.readLine();

                    try {
                        vs.eliminar(p);
                        System.out.println("VEHÍCULO ELIMINADO");
                    } catch (Exception e) {
                        System.out.println("NO EXISTE");
                    }

                    break;

                case 4:
                    try {
                        List<Vehiculo> lista = vs.validarListado();

                        for (Vehiculo ve : lista) {
                            
                            ve.imprimirDetalle();
                            
                            System.out.println("----------------------");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
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
