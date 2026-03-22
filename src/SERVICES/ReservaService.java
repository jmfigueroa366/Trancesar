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
import MODEL.Ruta;
import MODEL.Ticket;
import SERVICES.TicketService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private TicketService ticketService;

    public ReservaService(ReservaDAO reservaDAO, PasajeroDAO pasajeroDAO, VehiculoDAO vehiculoDAO, TicketDAO ticketDAO, TicketService ticketService) {
        this.reservaDAO = reservaDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.ticketDAO = ticketDAO;
        this.ticketService = ticketService;
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
        
        //Validar que el vehiculo tenga cupo para una nueva reserva contando los tickets vendidos
        int cupo = 0;
        
        for (Ticket t : ticketDAO.listarTodos()) {
            if (t.getVehiculo().getPlaca().equals(vehiculo.getPlaca()) && t.getFechaCompra().equals(fechaReservaDate)) {
                cupo++;
            }
        }
        
        for (Reserva r: reservaDAO.listarReservas()) {
            if (r.isActivo() && r.getVehiculo().getPlaca().equals(vehiculo.getPlaca()) && r.getFecha_reserva().equals(fechaReservaDate)) {
                cupo++;
            } 
        }
        if (cupo >= vehiculo.getCapacidad()) {
            throw new Exception ("El vehiculo no tiene cupo para realizar la reserva. Placa: " + vehiculo.getPlaca());
        }
        
        String fechaCreacion = LocalDate.now().toString();
        
        Reserva reserva = new Reserva (codigo, pasajero, vehiculo, fecha_reserva, fecha_reserva, true);
        
        reservaDAO.guardarReserva(reserva);
        System.out.println("Reserva registrada exitosamente...");
    }
    
    //Cancelar una reserva 
    public void cancelarReserva(String codigo) throws Exception {

    List<Reserva> lista = reservaDAO.listarReservas();
    boolean buscar = false;
    
    for (Reserva r : lista) {
        if (r.getCodigo().equals(codigo) && r.isActivo()) {
            buscar = true;
        }
    }

    if (!buscar) {
        throw new IllegalArgumentException("No existe reserva activa con este codigo");
    }

    reservaDAO.cancelarRerserva(codigo);
    System.out.println("Reserva cancelada correctamente");
}
    //listar de reservas activas
    public List<Reserva> listarActivas() throws Exception {
        
        List<Reserva> lista = reservaDAO.listarReservas();
        List<Reserva> activas = new ArrayList<>();
        
        for (Reserva r : lista) {
            if (r.isActivo()) {
                activas.add(r);
            }
        }
        return activas;
    }
    
    //listar el historial de reservas por pasajero
    public List<Reserva> historialPorPasajero(String id) throws Exception {
        
        List<Reserva> lista = reservaDAO.listarReservas();
        List<Reserva> historial = new ArrayList<>();
        
        for (Reserva r : lista) {
            if (r.getPasajero().getId().equals(id)) {
                historial.add(r);
            }
        }
        return historial;
    }
    
    //Convertir una reserva en ticket 
    public void Reserva_Ticket(String codigo) throws Exception {
        
    Reserva reservaEncontrada = null;

        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getCodigo().equals(codigo) && r.isActivo()) {
                reservaEncontrada = r;
                break;
            }
        }

        if (reservaEncontrada == null) {
            throw new IllegalArgumentException("No existe reserva activa con este codigo");
        }

        Pasajero pasajero = reservaEncontrada.getPasajero();
        Vehiculo vehiculo = reservaEncontrada.getVehiculo();

        // Obtener origen y destino desde la Ruta del vehículo
        Ruta ruta = vehiculo.getRuta();
        if (ruta == null) {
            throw new IllegalArgumentException("El vehículo no tiene una ruta asignada");
        }

        String NumTicket = codigo;
        String origen = ruta.getC_origen();
        String destino = ruta.getC_destino();

        ticketService.validarRegistro(
            NumTicket, origen, destino, pasajero.getId(), vehiculo.getPlaca());

        boolean actualizado = reservaDAO.ActualizarRegistros(reservaEncontrada);

        if (!actualizado) {
            throw new IllegalArgumentException("Error en la actualización de la reserva");
        }

        System.out.println("Reserva convertida en ticket exitosamente");
        
        }
    
    public boolean cancelarReservasVencidas (Reserva r) {

    LocalDateTime fechaCreacion = LocalDateTime.parse(r.getFecha_creacion());
    LocalDateTime ahora = LocalDateTime.now();

    long horas = ChronoUnit.HOURS.between(fechaCreacion, ahora);

    return horas > 24;
}
    
    public void verificarReservasVencidas() throws Exception {

    List<Reserva> lista = reservaDAO.listarReservas();

    int canceladas = 0;

    for (Reserva r : lista) {

        // Solo reservas activas
        if (r.isActivo()) {

            LocalDateTime fechaCreacion = LocalDateTime.parse(r.getFecha_creacion());
            LocalDateTime ahora = LocalDateTime.now();

            long horas = ChronoUnit.HOURS.between(fechaCreacion, ahora);

            if (horas > 24) {

                // Cancelar reserva (eliminar del archivo)
                boolean eliminado = reservaDAO.cancelarRerserva(r.getCodigo());

                if (eliminado) {
                    canceladas++;
                }
            }
        }
    }

    System.out.println("===================================");
    System.out.println(" VERIFICACIÓN DE RESERVAS VENCIDAS ");
    System.out.println("===================================");
    System.out.println("Reservas canceladas: " + canceladas);
}

}
