/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Pasajero;
import MODEL.Reserva;
import MODEL.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    
        
    }
    

