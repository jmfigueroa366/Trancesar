/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import DAO.VehiculoDAO;
import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import MODEL.Vehiculo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author alvar
 */
public class Menu {
    
    public Menu()throws IOException {
        
        BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
        
        int opc = 0;
        
        
        while (opc !=5) {            
            
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       GESTIÓN DE VEHÍCULOS         ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1. Registrar vehículo             ║");
        System.out.println("║  2. Buscar vehículo                ║");
        System.out.println("║  3. Eliminar vehículo              ║");
        System.out.println("║  4. Listar vehículos               ║");
        System.out.println("║  5. Salir                          ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("   Seleccione una opción: ");
        
            try {
                
                opc=Integer.parseInt(leer.readLine());
                
            } catch (NumberFormatException e) {
                
                System.out.println("Error de tipo " + e);
                opc=0;
            }
        
        switch (opc) {
            
            case 1:
                //ingreso de registros
                System.out.println("╔════════════════════════════════════╗");
                System.out.println("║       REGISTRAR VEHÍCULO           ║");
                System.out.println("╠════════════════════════════════════╣");
                System.out.println("║  Tipo de vehículo:                 ║");
                System.out.println("║  1. Bus                            ║");
                System.out.println("║  2. Buseta                         ║");
                System.out.println("║  3. MicroBus                       ║");
                System.out.println("╚════════════════════════════════════╝");
                System.out.print("   Seleccione el tipo: ");
                int tipo = Integer.parseInt(leer.readLine());
                
                System.out.println("╔════════════════════════════════════╗");
                System.out.println("║       DATOS DEL VEHÍCULO           ║");
                System.out.println("╠════════════════════════════════════╣");

                System.out.print("║  Placa: ");
                String placa = leer.readLine();

                System.out.print("║  Ruta: ");
                String ruta = leer.readLine();

                System.out.print("║  Capacidad: ");
                int capacidad = Integer.parseInt(leer.readLine());

                System.out.print("║  Tarifa: ");
                float tarifa = Float.parseFloat(leer.readLine());

                System.out.print("║  Disponible (true/false): ");
                boolean disponible = Boolean.parseBoolean(leer.readLine());

                System.out.println("╚════════════════════════════════════╝");
                
                //identificacion de tipo de vehiculo para registrar
                
                Vehiculo v = null;
                
                switch (tipo) {
                    case 1:
                        v = new Bus(capacidad, tarifa, placa, ruta, disponible);
                        break;
                        
                    case 2:
                        v = new Buseta(capacidad, tarifa, placa, ruta, disponible);
                        break;
                        
                    case 3:
                        v = new MicroBus(capacidad, tarifa, placa, ruta, disponible);
                        break;
                    default:
                        System.out.println("INGRESE UNA OPCION DEL MENU");
                }
                
                //guardado en archivo
                
                VehiculoDAO vd = null;
                
                vd.guardarVehiculo(v);
                
                break;
                
            case 2:
                
                break;
                
            case 3:
                
                break;
                
            case 4:
                
                break;
                
            case 5:
                
                break;
                
            default:
                System.out.println("INGRESE UNA OPCION DEL MENÚ");
        }

            
        }
        
    }
}
