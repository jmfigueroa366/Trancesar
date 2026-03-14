/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

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
