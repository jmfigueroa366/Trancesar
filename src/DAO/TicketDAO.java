/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.Ticket;
import MODEL.Pasajero;
import MODEL.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.time.LocalDate;
import trancesar.util.RutaArchivos;

/**
 *
 * @author ANDREA CAROLINA
 */
public class TicketDAO {
    ArrayList <Ticket> t = new ArrayList<>(); 
    
    
    
    public void guardar(Ticket ticket) throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter ( 
                new FileWriter (RutaArchivos.Ticket, true))) {
                    
            bw.write(ticket.getNumeroTicket() + ";" +
                     ticket.getFechaCompra() + ";" +
                     ticket.getOrigenCiudad() + ";" +
                     ticket.getDestinoCiudad() + ";" +
                     ticket.getPrecioFinal() + ";" + 
                     ticket.getPasajero().getId() + ";" +
                     ticket.getVehiculo().getPlaca());
                    bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }
    PasajeroDAO p = new PasajeroDAO();
    VehiculoDAO vd= new VehiculoDAO();
    
    public Ticket BuscarNumeroTicket (String NumeroTicket) {
        try (BufferedReader br = new BufferedReader (
                new FileReader (RutaArchivos.Ticket))) {
                String linea; 
                while ((linea = br.readLine()) !=null) {
                    if (linea.trim().isEmpty()) continue;
                    String [] datos = linea.split (";");
                    if (datos[0].equals(NumeroTicket)) {
                        String NumTicket = datos[0];
                        String idPasajero = datos[5];
                        Pasajero IdP = p.BuscarId(idPasajero);
                        String PlacaVehiculo = datos[6];
                        Vehiculo pv = vd.buscarPorPlaca(PlacaVehiculo);
                        LocalDate fechaCompra = LocalDate.parse(datos[1]);
                        String OrigenCiudad = datos[2];
                        String DestinoCiudad = datos[3];
                        double PrecioFinal = Double.parseDouble(datos[4]);
                        
                        return new Ticket (NumTicket, IdP, pv, fechaCompra, OrigenCiudad, DestinoCiudad, PrecioFinal);
                    }
                }
            } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
        return null;
    } 
    
    public List<Ticket> listarTodos() {
        List<Ticket> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (
            new FileReader (RutaArchivos.Ticket))) {
            String linea;
            while ((linea = br.readLine()) !=null) {
                if (linea.trim().isEmpty()) continue; 
                    String[] datos = linea.split(";"); {
                    String NumTicket = datos[0];
                    LocalDate fechaCompra = LocalDate.parse(datos[1]);
                    String OrigenCiudad = datos[2];
                    String DestinoCiudad = datos[3];
                    double PrecioFinal = Double.parseDouble(datos[4]);
                    String idPasajero = datos[5];
                    Pasajero IdP = p.BuscarId(idPasajero);
                    String PlacaVehiculo = datos[6];
                    Vehiculo pv = vd.buscarPorPlaca(PlacaVehiculo);
                  
                    lista.add( new Ticket (NumTicket, IdP, pv, fechaCompra, OrigenCiudad, DestinoCiudad, PrecioFinal));
                    }
            }
        } catch (IOException e) {
                    System.out.println("Error al guardar estudiante: " + e.getMessage());
                }
        return lista;
    }
    
    public void eliminarTicket(String NumeroTicket) {
        List<Ticket> ticket = listarTodos();
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (RutaArchivos.Ticket, false))) {
            for (Ticket t : ticket) {
                if (!t.getNumeroTicket().equals(NumeroTicket)) {
                    bw.write(t.getNumeroTicket()+";"+t.getPasajero()+";"+t.getVehiculo()+";"+t.getFechaCompra()+";"+t.getOrigenCiudad()+";"+t.getDestinoCiudad()+";"+t.getPrecioFinal());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar pasajero: " + e.getMessage());
        }
    }
    
    public boolean existeNumeroTicket(String NumeroTicket) {
        return BuscarNumeroTicket(NumeroTicket) != null;
    }
}
