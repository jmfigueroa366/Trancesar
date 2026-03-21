/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.view;
import trans_cesar.dao.PasajeroDAO;
import trans_cesar.model.Pasajero;
import trans_cesar.model.PasajeroAdultoMayor;
import trans_cesar.model.PasajeroEstudiante;
import trans_cesar.model.PasajeroRegular;
import trans_cesar.service.PasajeroService;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class MenuPasajero {

    public static void main(String[] args) throws IOException, Exception {
        
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        
        PasajeroService ps = new PasajeroService();
        
        int opc = 0;
        
        while (opc !=5) {  
            
        String m = null; 
            
        System.out.println("==============================");
        System.out.println("|       GESTIÓN DE PASAJERO          |");
        System.out.println("==============================");
        System.out.println("|  1. Registrar pasajero             |");
        System.out.println("|  2. Buscar pasajero                |");
        System.out.println("|  3. Listar pasajero                |");
        System.out.println("|  4. Eliminar pasajero              |");
        System.out.println("|  5. Salir                          |");
        System.out.println("==============================");
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
                System.out.println("==============================");
                System.out.println("|       REGISTRAR PASAJERO           |");
                System.out.println("==============================");
                
                System.out.println("|  Numero de identidad: ");
                String id = leer.readLine();
                
                System.out.println("|  Nombre: ");
                String nombre = leer.readLine();
                
                System.out.println("|  Fecha de Nacimiento (ANO/MES/DIA):  ");
                LocalDate fechaNacimiento = LocalDate.parse(leer.readLine());
                
                System.out.println("|  Tipo (Estudiante/Regular): ");
                String tipo = leer.readLine();
                
                ps.validarRegistro(id, nombre, fechaNacimiento, tipo);
                
                break;
                
            case 2:
                
                System.out.println("==============================");
                System.out.println("|         BUSCAR PASAJERO            |");
                System.out.println("==============================");
                System.out.print("|  Ingrese el numero de identidad: ");
                m = leer.readLine();
                System.out.println("==============================");
                
                try {
                    
                    Pasajero p = ps.buscarPasajero(m);
                    
                        System.out.println("╔=============================");
                        System.out.println("|       PASAJERO ENCONTRADO          |");
                        System.out.println("==============================");
                        System.out.println("|  Num Identidad:      " + p.getId());
                        System.out.println("|  Nombre:       " + p.getNombre());
                        System.out.println("|  Fecha de Nacimiento:  " + p.getFechaNacimiento());
                        System.out.println("|  Tipo:     " + p.getClass().getSimpleName() );
                        System.out.println("==============================");

                    
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
                
                break;
                
            case 3:
                
                try {
                    
                    List <Pasajero> lista = ps.listarPasajero();
                    
                    System.out.println("==============================");
                    System.out.println("|         LISTA DE PASAJEROS         |");
                    System.out.println("==============================");
                    
                    for (Pasajero pa : lista) {
                        System.out.println("|  Numero de identidad:       " + pa.getId());
                        System.out.println("|  Nombre:      " + pa.getNombre());
                        System.out.println("|  Fecha de Nacimiento:       " + pa.getFechaNacimiento());
                        System.out.println("===========================");
                    }
                                                                        //
                    System.out.println("===============================");
                    
                } catch (Exception e) {
                    System.out.println("Error de tipo " + e);
                }
                
                break;
            
            case 4:
                
                        System.out.println("==============================");
                        System.out.println("|        ELIMINAR PASAJERO            |");
                        System.out.println("==============================");
                        System.out.print("|  Ingrese la numero de identidad: ");
                        m = leer.readLine();
                        System.out.println("==============================");
                        
                        try {
                            ps.eliminarPasajero(m);
                            System.out.println("REGISTRO ELIMINADO");
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
