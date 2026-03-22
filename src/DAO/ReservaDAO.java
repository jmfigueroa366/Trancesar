/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import MODEL.Pasajero;
import MODEL.PasajeroAdultoMayor;
import MODEL.PasajeroEstudiante;
import MODEL.PasajeroRegular;
import MODEL.Reserva;
import MODEL.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import trancesar.util.RutaArchivos;
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
            r.getCodigo()          + ";" +
            r.getFecha_creacion()  + ";" +
            r.getFecha_reserva()   + ";" +
            p.getId()              + ";" +   
            p.getNombre()          + ";" + 
            v.getPlaca()           + ";" +   
            v.getTarifa()          + ";" +   
            v.getCapacidad()       + ";" +   
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
               
               return new PasajeroRegular(0, id, nombre, fechaNac);
               
           case "PasajeroAdultoMayor":
               
               return new PasajeroAdultoMayor(0, id, nombre, fechaNac);
               
           case "PasajeroEstudiante":
               
               return new PasajeroEstudiante(0, id, nombre, fechaNac);
               
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
        return reservas;
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

    public boolean ActualizarRegistros(Reserva codigoReserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
   }
