/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.view;
import trans_cesar.dao.ConductorDAO;
import trans_cesar.model.Conductor;
import trans_cesar.service.ConductorService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class MenuConductor {
    public static void main(String[] args) throws IOException, Exception {
        
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        
        ConductorService cs = new ConductorService();
        
        int opc = 0;
        
        while (opc !=5) {  
            
        String m = null; 
            
        System.out.println("==============================");
        System.out.println("|       GESTIÓN DE CONDUCTOR          |");
        System.out.println("==============================");
        System.out.println("|  1. Registrar conductor             |");
        System.out.println("|  2. Buscar conductor                |");
        System.out.println("|  3. Listar conductor                |");
        System.out.println("|  4. Eliminar conductor              |");
        System.out.println("|  5. Salir                          |");
        System.out.println("==============================");
        System.out.print("   Seleccione una opción: ");
        
            try {
                
                opc=Integer.parseInt(leer.readLine());
                
            } catch (NumberFormatException e) {
                
                System.out.println("Error de tipo " + e.getMessage());
                opc=0;
            }
        
        switch (opc) {
            
            case 1:
                //ingreso de registros
                System.out.println("==============================");
                System.out.println("|       REGISTRAR CONDUCTOR           |");
                System.out.println("==============================");
                
                System.out.println("|  Numero de identidad: ");
                String id = leer.readLine();
                
                System.out.println("|  Nombre: ");
                String nombre = leer.readLine();
                
                System.out.println("|  Fecha de Nacimiento (YYYY/MM/DD):  ");
                LocalDate fechaNacimiento = LocalDate.parse(leer.readLine());
                
                System.out.println("|  Numero de Licencia: ");
                String numeroLicencia = leer.readLine();
                
                System.out.println("|  Categoria de Licencia: ");
                String categoriaLicencia = leer.readLine();
                
                cs.validarRegistro(numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento);
                
                break;
                
            case 2:
                
                System.out.println("==============================");
                System.out.println("|         BUSCAR CONDUCTOR         |");
                System.out.println("==============================");
                System.out.print("|  Ingrese el numero de identidad: ");
                m = leer.readLine();
                System.out.println("==============================");
                
                try {
                    
                    Conductor c = cs.buscarConductor(m);
                    
                        System.out.println("==============================");
                        System.out.println("|       CONDUCTOR ENCONTRADO    |");
                        System.out.println("==============================");
                        System.out.println("|  Num. Identidad:          " + c.getId());
                        System.out.println("|  Nombre:                 " + c.getNombre());
                        System.out.println("|  Fecha de Nacimiento:    " + c.getFechaNacimiento());
                        System.out.println("|  Numero de Licencia:     " + c.getNumeroLicencia());
                        System.out.println("|  Categoria de Licencia:  " + c.getCategoriaLicencia());
                        System.out.println("==============================");

                    
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
                break;
                
            case 3:
                
                try {
                    
                    List <Conductor> lista = cs.listarConductor();
                    
                    System.out.println("==============================");
                    System.out.println("|         LISTA DE CONDUCTORES        |");
                    System.out.println("==============================");
                    
                    for (Conductor cl : lista) {
                        System.out.println("|  Numero de identidad:    " + cl.getId());
                        System.out.println("|  Nombre:                 " + cl.getNombre());
                        System.out.println("|  Fecha de Nacimiento:    " + cl.getFechaNacimiento());
                        System.out.println("|  Numero de Licencia:     " + cl.getNumeroLicencia());
                        System.out.println("|  Categoria de Licencia:  " + cl.getCategoriaLicencia());
                        System.out.println("===========================");
                    }
                                                                        //
                    System.out.println("===============================");
                    
                } catch (Exception e) {
                    System.out.println("Error de tipo " + e.getMessage());
                }
                
                break;
            
            case 4:
                
                        System.out.println("==============================");
                        System.out.println("|        ELIMINAR CONDUCTOR            |");
                        System.out.println("==============================");
                        System.out.print("|  Ingrese el numero de identidad: ");
                        m = leer.readLine();
                        System.out.println("==============================");
                        
                        try {
                            cs.eliminarConductor(m);
                            System.out.println("CONDUCTOR ELIMINADO");
                        } catch (Exception e) {
                            System.out.println("NO EXISTE ESTE REGISTRO ");
                        }
                
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("INGRESE UNA OPCION DEL MENÚ");
        }
        }
    }
}
