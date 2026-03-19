/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import SERVICES.rutaServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author alvar
 */
public class menuRutas {

    public menuRutas() throws IOException {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        rutaServices rs = new rutaServices();
        
        int opc=0;
        
        while (opc!=4) {            
            
            System.out.println("==============================");
            System.out.println("|       GESTIÓN DE RUTAS             |");
            System.out.println("==============================");
            System.out.println("|  1. Registrar ruta                 |");
            System.out.println("|  2. Buscar ruta                    |");
            System.out.println("|  3. Listar rutas                   |");
            System.out.println("|  4. Salir                          |");
            System.out.println("==============================");
            System.out.print("   Seleccione una opción: ");
            
            try {
                
                opc=Integer.parseInt(leer.readLine());
                
            } catch (NumberFormatException e) {
                
                System.out.println("Ingrese solo números");
                opc = 0;
                
            }
            
            switch (opc) {
                case 1:
                    
                    break;
                default:
                    System.out.println("INGRESE UNA OPCION DEL MENÚ");
            }
            
        }
        
    }
    
      
    
}
