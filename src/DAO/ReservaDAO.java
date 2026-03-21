/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Reserva;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import trancesar.util.RutaArchivos;
/**
 *
 * @author alvar
 */
public class ReservaDAO {
    
    ArrayList<Reserva> reservas = new ArrayList<>();
    
    public void guardarReserva(Reserva r){
        
        String ruta = RutaArchivos.Reserva;
        
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ruta, true))) {

                bw.write(
                     r.getCodigo() + ";" +
                     r.getFecha_creacion() + ";" +
                     r.getFecha_reserva() + ";" +
                     r.getPasajero() + ";" +
                     r.getVehiculo().getPlaca());
            bw.newLine();
            
        } catch (IOException e) {
            System.out.println("Error al guardar vehiculo: " + e.getMessage());
        }
        
    }
    
}
