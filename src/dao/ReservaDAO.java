/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Bus;
import model.Buseta;
import model.MicroBus;
import model.Pasajero;
import model.PasajeroAdultoMayor;
import model.PasajeroEstudiante;
import model.PasajeroRegular;
import model.Reserva;
import model.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Trancesar.RutaArchivos;
/**
 *
 * @author alvar
 */
public class ReservaDAO {
    
    ArrayList<Reserva> reservas = new ArrayList<>();
    
   public void guardarReserva(Reserva r) {
    try (BufferedWriter bw = new BufferedWriter(
            new FileWriter(RutaArchivos.Reserva, true))) {

        Pasajero p = r.getPasajero();
        Vehiculo v = r.getVehiculo();

        bw.write(
            r.getCodigo()                + ";" +
            r.getFecha_creacion()        + ";" +
            r.getFecha_reserva()         + ";" +
            p.getId()                    + ";" +   
            p.getNombre()                + ";" + 
            p.getFechaNacimiento()       + ";" + 
            p.getClass().getSimpleName() + ";" + 
            v.getPlaca()                 + ";" +   
            v.getTarifa()                + ";" +   
            v.getCapacidad()             + ";" +   
            v.getClass().getSimpleName() + ";" + 
            r.isActivo()                     
        );
        bw.newLine();

    } catch (IOException e) {
        System.out.println("Error al guardar reserva: " + e.getMessage());
    }
   }
   
   private Pasajero crearPasajero(String tipo, String id, String nombre, LocalDate fechaNac){
       
       switch (tipo) {
           case "PasajeroRegular":
               
               return new PasajeroRegular(id, nombre, fechaNac);
               
           case "PasajeroAdultoMayor":
               
               return new PasajeroAdultoMayor(id, nombre, fechaNac);
               
           case "PasajeroEstudiante":
               
               return new PasajeroEstudiante(id, nombre, fechaNac);
               
           default:
               return null;
       }
       
   }
    
    private Vehiculo crearVehiculo (String tipo, String placa, Float tarifa, int capacidad){
        
        Vehiculo v;
        
        switch (tipo) {
            case "Bus":
                
                v=new Bus();
                
                break;
             
            case "Buseta":
                
                v=new Buseta();
                
                break;
                
            case "Microbus":
            
                v=new MicroBus();
                
                break;
             
            default:
                return null;
        }
        
        v.setPlaca(placa);
        v.setTarifa(tarifa);
        v.setCapacidad(capacidad);
        
        return v;
        
    }
    
    public List<Reserva> listarReservas() throws IOException{
        
        List<Reserva> lista = new ArrayList<>();
        
         try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.Reserva))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 12) continue;
                
                lista.add(new Reserva(
                
                datos[0], 
                        
                crearPasajero(datos[6], datos[3], datos[4], LocalDate.parse(datos[5])),
                        
                crearVehiculo(datos[10], datos[7], Float.parseFloat(datos[8]), Integer.parseInt(datos[9])),
                        
                datos[1],datos[2],
                        
                Boolean.parseBoolean(datos[11])
                        
                ));
                
            }
        }
        return lista;
    }
    
    public boolean cancelarRerserva(String codigo) throws FileNotFoundException, IOException{
        
        List<String> lineas = new ArrayList<>();
        
        boolean encontrado = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.Reserva))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                    if (datos[0].equals(codigo)) {
                        encontrado = true; // esta línea se omite (se "elimina")
                    } else {
                    lineas.add(linea); // las demás se conservan
                        }
            }
        } catch (IOException e) {
          System.out.println("Error al leer reservas: " + e.getMessage());
          return false;
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivos.Reserva, false))){
            
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
            
        } catch (Exception e) {
            
            System.out.println("Error en la eliminacion de reserva" + e);
            return false;
        } 
        
      return encontrado;
    }
      
    public Reserva buscarCodigo(String codigo){
        
        try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.Reserva))){
            
            String linea;
            
            while ((linea=br.readLine()) !=null ) {                
                
                if (linea.trim().isBlank()) continue ;
                
                String[] d = linea.split(";");
                
                if (d.length<12) continue;
                
                if (d[0].equals(codigo)) {
                    
                    return new Reserva(
                            
                            d[0],
                            crearPasajero(d[6], d[3], d[4], LocalDate.parse(d[5])),
                            crearVehiculo(d[10], d[7], Float.parseFloat(d[8]), Integer.parseInt(d[9])),
                            d[1], d[2],
                            Boolean.parseBoolean(d[11])
                            
                    );
                    
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("Error al buscar el codigo " + e);
        }
        return null;
    }
    
    public boolean ActualizarRegistros(Reserva ReservaActualizada) throws FileNotFoundException, IOException{
        List <String> lineas = new ArrayList<>();
        
        boolean encontrado = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.Reserva))){
            
            String linea;
            
            while ((linea = br.readLine()) !=null) {                
                
                if (!linea.trim().isEmpty()) {
                    String d[] = linea.split(";");
                    
                    if (d[0].equals(ReservaActualizada.getCodigo()) ) {
                        
                        Pasajero p = ReservaActualizada.getPasajero();
                        Vehiculo v = ReservaActualizada.getVehiculo();
                        linea =
                        ReservaActualizada.getCodigo()        + ";" +
                        ReservaActualizada.getFecha_creacion()+ ";" +
                        ReservaActualizada.getFecha_reserva() + ";" +
                        p.getId()                             + ";" +
                        p.getNombre()                         + ";" +
                        p.getFechaNacimiento()                + ";" +
                        p.getClass().getSimpleName()          + ";" +
                        v.getPlaca()                          + ";" +
                        v.getTarifa()                         + ";" +
                        v.getCapacidad()                      + ";" +
                        v.getClass().getSimpleName()          + ";" +
                        ReservaActualizada.isActivo();
                        
                    }
                    lineas.add(linea);
                }
                
            }
            
        }catch(IOException e){
            
            System.out.println("Error en actualizacion " + e);
            return false;
            
        }
        return encontrado;
        
     }
    
   }
