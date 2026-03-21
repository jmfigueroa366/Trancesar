/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import MODEL.Ruta;
import SERVICES.rutaServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author alvar
 */
public class menuRutas {

    public menuRutas() throws IOException, Exception {
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
                    
                    System.out.println("==============================");
                    System.out.println("|       REGISTRAR RUTA               |");
                    System.out.println("==============================");
                    
                    System.out.print("|  Código: ");
                    String codigo = leer.readLine();
                    
                    System.out.print("|  Ciudad origen: ");
                    String origen = leer.readLine();
                    
                    System.out.print("|  Ciudad destino: ");
                    String destino = leer.readLine();
                    
                    System.out.print("|  Distancia (km): ");
                    float distancia = Float.parseFloat(leer.readLine());
                    
                    System.out.print("|  Tiempo estimado (min): ");
                    float tiempo = Float.parseFloat(leer.readLine());
                    
                    System.out.println("==============================");
                    
                    try {
                        
                        Ruta r = new Ruta(codigo, origen, destino, distancia, tiempo);
                        
                        rs.registrar(r);
                        
                        System.out.println("Ruta registrada correctamente");
                        
                    } catch (Exception e) {
                        
                        System.out.println("Error: " + e.getMessage());
                        
                    }
                    
                    break;
                 
                case 2:
                    
                    System.out.println("==============================");
                    System.out.println("|         BUSCAR RUTA                |");
                    System.out.println("==============================");
                    System.out.print("|  Ingrese el código: ");
                    String cod = leer.readLine();
                    System.out.println("==============================");
                    
                    try {
                        
                        Ruta r= rs.buscar(cod);
                        
                        System.out.println("==============================");
                        System.out.println("|       RUTA ENCONTRADA              |");
                        System.out.println("==============================");
                        System.out.println("|  Código:   " + r.getCodigo());
                        System.out.println("|  Origen:   " + r.getC_origen());
                        System.out.println("|  Destino:  " + r.getC_destino());
                        System.out.println("|  Distancia:" + r.getDistancia() + " km");
                        System.out.println("|  Tiempo:   " + r.getTiempo() + " min");
                        System.out.println("==============================");
                        
                    } catch (Exception e) {
                        
                        System.out.println("Error de tipo: " + e);
                        
                    }
                    
                    break;
                    
                case 3:
                    
                    try{
                    
                    List<Ruta> lista = rs.listar();
                    
                    System.out.println("==============================");
                    System.out.println("|         LISTA DE RUTAS             |");
                    System.out.println("==============================");
                    
                    for (Ruta r : lista) {
                        
                        System.out.println("|  Código:   " + r.getCodigo());
                        System.out.println("|  Origen:   " + r.getC_origen());
                        System.out.println("|  Destino:  " + r.getC_destino());
                        System.out.println("|  Distancia:" + r.getDistancia() + " km");
                        System.out.println("|  Tiempo:   " + r.getTiempo() + " min");
                        System.out.println("===========================");
                        
                    }
                    
                    System.out.println("==============================");
                    
                    break;
                    
                    }catch (Exception e) {
                        System.out.println("Error de tipo: " + e.getMessage());
                    }
                 
                default:
                    System.out.println("INGRESE UNA OPCION DEL MENÚ");
                    
            }
            
        }
        
    }
}
