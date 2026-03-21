/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;
import DAO.PasajeroDAO;
import DAO.ReservaDAO;
import DAO.TicketDAO;
import DAO.VehiculoDAO;
import MODEL.Pasajero;
import MODEL.Vehiculo;
import MODEL.Reserva;
import MODEL.Ticket;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class ReservaService {
    private ReservaDAO reservaDAO;
    private PasajeroDAO pasajeroDAO;
    private VehiculoDAO vehiculoDAO;
    private TicketDAO ticketDAO;

    public ReservaService(ReservaDAO reservaDAO, PasajeroDAO pasajeroDAO, VehiculoDAO vehiculoDAO, TicketDAO ticketDAO) {
        this.reservaDAO = reservaDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.ticketDAO = ticketDAO;
    }
    
    public void validarRegistro (String codigo, String idPasajero, String placaVehiculo, String fecha_reserva) 
            throws Exception {
        
        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getCodigo().equals(codigo))
                throw new IllegalArgumentException ("Ya existe una reserva con este codigo");
        }
        
        //Validar el pasajero
        Pasajero pasajero = pasajeroDAO.BuscarId(idPasajero);
        if (pasajero == null) {
             throw new IllegalArgumentException("No existe pasajero con este id");
        }
        
        //Validar el vehiculo
        Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
        if (vehiculo ==  null) {
            throw new IllegalArgumentException("No existe vehiculo con este placa");
        }
        
        if (fecha_reserva == null || fecha_reserva.trim().isEmpty()) {
            throw new IllegalArgumentException ("La fecha de reserva no puede estar vacia");
        }
        
        LocalDate fechaReservaDate = LocalDate.parse(fecha_reserva);
        
        //Validar que sea 1 sola reservacion por pasajero
        for (Reserva r: reservaDAO.listarReservas()) {
            if (r.getPasajero().getId().equals(pasajero.getId()) && r.getVehiculo().getPlaca().equals(vehiculo.getPlaca()) && r.getFecha_reserva().equals(fecha_reserva) && r.isActivo()) {
            throw new Exception ("El pasajero ya tiene una reservación activa para vehiculo en esa fecha ");
            }
        }
        
}
