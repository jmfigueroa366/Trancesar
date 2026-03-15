/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.dao;
import trans_cesar.model.Ticket;
import trans_cesar.util.RutaArchivos;
import trans_cesar.model.Pasajero;
import trans_cesar.model.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public class TicketDAO {
    ArrayList <Ticket> t = new ArrayList<>(); 
    
    public void guardar(Ticket ticket) throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter ( 
                new FileWriter (RutaArchivos.Conductores, true))) {
                    
            bw.write(ticket.getNumeroTicket() + ";" +
                     ticket.getFechaCompra() + ";" +
                     ticket.getPasajero().getNombre() + ";" +
                     ticket.getVehiculo().getPlaca + ";" +
                     ticket.getOrigenCiudad() + ";" +
                     ticket.getDestinoCiudad() + ";" +
                     ticket.getPrecioFinal());
                    bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }
    
    public Ticket BuscarNumeroTicket (String NumeroTicket) {
        try (BufferedReader br = new BufferedReader (
                new FileReader (RutaArchivos.Ticket))) {
                String linea; 
                while ((linea = br.readLine()) !=null) {
                    if (linea.trim().isEmpty()) continue;
                    String [] datos = linea.split (";");
                    if (datos[0].equals(NumeroTicket)) {
                        int NumTicket = Integer.parseInt(datos[0]);
                        LocalDate fechaCompra = LocalDate.parse(datos[1]);
                        String NombrePasajero = datos[2];
                        float PlacaVehiculo = Float.parseFloat(datos[3]);
                        String OrigenCiudad = datos[4];
                        String DestinoCiudad = datos[5];
                        double PrecioFinal = Double.parseDouble(datos[6]);
                        
                        return new Ticket (NumTicket, fechaCompra, OrigenCiudad, DestinoCiudad, PrecioFinal, NombrePasajero, PlacaVehiculo);
                    }
                }
            } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
        return null;
    } 
}
