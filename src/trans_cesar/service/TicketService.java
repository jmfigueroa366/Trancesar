/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.service;
import trans_cesar.dao.TicketDAO;
import trans_cesar.dao.PasajeroDAO;
import trans_cesar.dao.ConductorDAO;
import trans_cesar.model.Ticket;
import trans_cesar.model.Pasajero;
import trans_cesar.model.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class TicketService {
    
    private TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
     private PasajeroDAO pasajeroDAO; //Verificar 
     private VehiculoDAO vehiculoDAO;
    
    public void validarRegistro (String NumeroTicket, LocalDate fechaCompra, String OrigenCiudad, String DestinoCiudad, double PrecioFinal, String idpasajero, String placaVehiculo)
            throws Exception {
        // Inicialmente s verifica que no exista diplicacion de Numero de Ticket
        if (ticketDAO.BuscarNumeroTicket(NumeroTicket) !=null) {
             throw new IllegalArgumentException("Ya se ingreso este numero de ticket");
        }
        // Validacion de los datos basicos
        if (OrigenCiudad == null || OrigenCiudad.trim().isEmpty()) {
             throw new IllegalArgumentException("La ciudad de origen no puede estar vacio");
        }
        if (DestinoCiudad == null || DestinoCiudad.trim().isEmpty()) {
             throw new IllegalArgumentException("La ciudad de destino no puede estar vacio");
        }
        
        //Validar el pasajero
        Pasajero pasajero = pasajeroDAO.BuscarId(idpasajero);
        if (pasajero == null) {
             throw new IllegalArgumentException("No existe pasajero con este id");
        }
        
        //Validar el vehiculo
        Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
        if (vehiculo ==  null) {
            throw new IllegalArgumentException("No existe vehiculo con este placa");
        }
        
        
        
        Ticket ticket = new Ticket ();
        ticketDAO.guardar(ticket);
        System.out.println("Ticket registrado correctamente");
    }
    
     //Busca un pasajero por ID.
    public Ticket BuscarNumeroTicket(String NumeroTicket) {
        Ticket ticket = ticketDAO.BuscarNumeroTicket(NumeroTicket);
        if (ticket == null) {
            throw new IllegalArgumentException("No se encontró un ticket con este número: " + NumeroTicket);
        }
        return ticket;
    }
   
    // Retorna la lista de todos los ticket registrados.
    public List<Ticket> listarTicket() {
        List<Ticket> lista = ticketDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay ticket registrados.");
        }
        return lista;
    }
    
    // Elimina un ticket por su numero.
    public void eliminarTicket(String NumeroTicket) {
        if (!ticketDAO.existeNumeroTicket(NumeroTicket)) {
            throw new IllegalArgumentException("No existe ticket con este número: " + NumeroTicket);
        }
        ticketDAO.eliminarTicket(NumeroTicket);
        System.out.println("Ticket eliminado exitosamente.");
    }
}
