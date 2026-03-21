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
import java.time.Month;
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
                
        //Validar maximo 3 tickets por día 
        int contador = 0;
        LocalDate hoy = LocalDate.now();
        for (Ticket t : ticketDAO.listarTodos()){
            if (t.getPasajero().getId().equals(pasajero.getId()) && t.getFechaCompra().equals(hoy)) {
                contador++;
            }
        }
        if (contador >= 3) {
            throw new Exception ("El pasajero no puede tener más de 3 tickets por día. ID: " + pasajero.getId());
        }
        
        //Validar que el vehiculo tenga cupo para un nuevo pasajero
        int cupo = 0;
        for (Ticket t : ticketDAO.listarTodos()) {
            if (t.getVehiculo().getPlaca().equals (vehiculo.getPlaca()) && t.getFechaCompra().equals(hoy)) {
                cupo++;
            }
        }
        if (cupo >= vehiculo.getCapacidad()) {
            throw new Exception ("El vehiculo no tiene cupo. Placa: " + vehiculo.getPlaca());
        }
        
        //Si es festivo se le adiciona 20% al precio del tickets
        //Primero se realizan los recargos a la tarifa
        double tarifaFestivo = tarifa;
        if (festivo(fechaCompra)) {
            float tarifaFestivo = tarifa + (tarifa * 0.20);
        }
        
        //Luego, se aplica el descuento según el tipo de pasajero
        double Descuento = pasajero.calcularDescuento();
        double precioFinal = tarifaFestivo - (tarifaFestivo * Descuento);
        
        Ticket ticket = new Ticket (NumeroTicket, fechaCompra, OrigenCiudad, DestinoCiudad, PrecioFinal, pasajero, cupo);
        //Sobreescribir en el precio final
        System.out.println("Precio Final del Ticket: $" + PrecioFinal);
        ticketDAO.guardar(ticket);
        System.out.println("Ticket registrado correctamente");
    }
    
    //Metodo auxiliar para dias festivos en el calendario 
    private boolean Festivo (LocalDate fecha) {
        if (fecha.getDayOfWeek().getValue() == 7) {
            return true;
        }
        //Lunes festivos configurados
        if (fecha.getDayOfWeek().getValue() == 1) {
            List<LocalDate> lunesFestivos = List.of(
            LocalDate.of(2026, 3, 23), 
            LocalDate.of(2026, 5, 18),
            LocalDate.of(2026, 6, 8),
            LocalDate.of(2026, 6, 15),
            LocalDate.of(2026, 6, 29),
            LocalDate.of(2026, 7, 20),
            LocalDate.of(2026, 8, 17));
        }
        //Jueves festivos configurados
        if (fecha.getDayOfWeek().getValue() == 4) {
            List<LocalDate> juevesFestivos = List.of(
            LocalDate.of(2026, 4, 2) );
        }
        //Viernes festivos configurados
        if (fecha.getDayOfWeek().getValue() == 5) {
            List<LocalDate> viernesFestivos = List.of(
            LocalDate.of(2026, 4, 3), 
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 8, 7));
        }
        return false;
    }
    
     //Busca un tickte por numero.
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
