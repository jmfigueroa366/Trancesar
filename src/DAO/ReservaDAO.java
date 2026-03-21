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
        
   }
