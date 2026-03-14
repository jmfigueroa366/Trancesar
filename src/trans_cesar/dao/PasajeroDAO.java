/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.dao;

import trans_cesar.model.Conductor;
import trans_cesar.model.Pasajero;
import trans_cesar.model.Persona;
import trans_cesar.model.Ticket;
import trans_cesar.util.RutaArchivos;
import java.util.ArrayList;
import java.io.BufferedWriter; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;

/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroDAO {
    
    public void guardar(Pasajero pasajero) throws IOException {
        try (BuffereWrite bw = new BufferedWriter ( 
                new FileWriter (RutaArchivos.Pasajeros, true))) {
            bw.write(pasajero.getId() + ";" +
                     pasajero.getNombre() + ";" +
                     pasajero.getEdad() + ";" +
                     pasajero.getDescuento());
}                    bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }
    
    public void BuscarId (String id) {
        
    }
    
    public void lista () {
        
    }
}

