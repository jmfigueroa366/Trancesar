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
    
    private static final int MaximoHoras = 24;
    
    private final ReservaDAO reservaDAO;
    private final PasajeroDAO pasajeroDAO;
    private final VehiculoDAO vehiculoDAO;
    private final TicketDAO ticketDAO;
    
    public ReservaService(ReservaDAO reservaDAO, PasajeroDAO pasajeroDAO, VehiculoDAO vehiculoDAO, TicketDAO ticketDAO) {
        this.reservaDAO = reservaDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.ticketDAO = ticketDAO;
    }
    
    public void validarRegistro (String codigo, String idPasajero, String placaVehiculo, String fecha_reserva) 
            throws Exception {
        
        //Validaciones basicas
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de reserva no puede estar vacío.");
        }
        if (idPasajero == null || idPasajero.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del pasajero no puede estar vacío.");
        }
        if (placaVehiculo == null || placaVehiculo.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del vehículo no puede estar vacía.");
        }
        if (fecha_reserva == null || fecha_reserva.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de viaje no puede estar vacía.");
        }
        
        //Validar formato de fecha
        try {
            LocalDate.parse(fecha_reserva);
        } catch (Exception e) {
            throw new IllegalArgumentException ("Fecha invalida (año/mes/dia)");
        }
        
        //Validar que no exista diplicacion de codigo
        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getCodigo().equals(codigo))
                throw new IllegalArgumentException ("Ya existe una reserva con este codigo" + codigo);
        }
        
        //Validar el pasajero
        Pasajero pasajero = pasajeroDAO.BuscarId(idPasajero);
        if (pasajero == null) {
             throw new IllegalArgumentException("No existe pasajero con este id" + idPasajero);
        }
        
        //Validar el vehiculo
        Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
        if (vehiculo ==  null) {
            throw new IllegalArgumentException("No existe vehiculo con este placa" + placaVehiculo);
        }
        
        
        //Validar que sea 1 sola reservacion por pasajero y por vehiculo
        for (Reserva r: reservaDAO.listarReservas()) {
            if (r.getPasajero().getId().equals(idPasajero) 
                    && r.getVehiculo().getPlaca().equals(placaVehiculo) 
                    && r.getFecha_reserva().equals(fecha_reserva) 
                    && r.isActivo()) {
            throw new Exception ("El pasajero ya tiene una reservación activa en el vehiculo "
                                    + placaVehiculo + " para la fecha " + fecha_reserva);
            }
        }
        
        //Validar que el vehiculo tenga cupo para una nueva reserva contando los tickets vendidos
        int cupo = 0;
        
        //Contar todos los tickets vendidos para este vehiculo
        for (Ticket t : ticketDAO.listarTodos()) {
            if (t.getVehiculo().getPlaca().equals(placaVehiculo)) {
                cupo++;
            }
        }
        
        //Contar todas las reservas para este vehiculo
        for (Reserva r: reservaDAO.listarReservas()) {
            if (r.getVehiculo().getPlaca().equals(placaVehiculo) 
                    && r.getFecha_reserva().equals(fecha_reserva)
                    && r.isActivo()) {
                cupo++;
            } 
        }
        if (cupo >= vehiculo.getCapacidad()) {
            throw new Exception ("El vehiculo no tiene cupo para realizar la reserva. Placa: " + vehiculo.getPlaca());
        }
        
        //Crear y guardar la reserva
        String fechaCreacion = LocalDateTime.now().toString();
        
        Reserva reserva = new Reserva (codigo, pasajero, vehiculo, fechaCreacion, fecha_reserva, true);
        reservaDAO.guardarReserva(reserva);
        System.out.println("Reserva registrada exitosamente..."
                            + " | Reserva: " + codigo
                            + " | Pasajero: " + pasajero.getNombre()
                            + " | Vehículo: " + placaVehiculo
                            + " | Fecha viaje: " + fecha_reserva);
    }
    
    //Cancelar una reserva 
    public void cancelarReserva(String codigo) throws Exception {
        
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de reserva no puede estar vacío.");
        }
        
        // Verificar existencia y estado antes de cancelar
        Reserva reserva = reservaDAO.buscarCodigo(codigo);
        if (reserva == null) {
            throw new IllegalArgumentException("No existe reserva con código: " + codigo);
        }
        if (!reserva.isActivo()) {
            throw new Exception("La reserva " + codigo + " ya no está activa.");
        }

        reservaDAO.cancelarRerserva(codigo);
        System.out.println("Reserva" + codigo + "cancelada correctamente");
    }
    // Convertir reserva a ticket
    public void reservaATicket(String codigoReserva, String numeroTicket,
                                String origenCiudad, String destinoCiudad,
                                TicketService ticketService) throws Exception {
 
        if (codigoReserva == null || codigoReserva.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de reserva no puede estar vacío.");
        }
    
        // Buscar la reserva
        Reserva reserva = reservaDAO.buscarCodigo(codigoReserva);
        if (reserva == null) {
            throw new IllegalArgumentException(
                "No existe reserva con código: " + codigoReserva);
        }
        if (!reserva.isActivo()) {
            throw new Exception("La reserva " + codigoReserva + " ya no está activa.");
        }
        
        ticketService.validarRegistro(
            numeroTicket,
            reserva.getPasajero().getId(),
            reserva.getVehiculo().getPlaca(),
            origenCiudad,
            destinoCiudad
        );
        
        // Marcar la reserva como inactiva
        Reserva reservaConvertida = new Reserva(
            reserva.getCodigo(),
            reserva.getPasajero(),
            reserva.getVehiculo(),
            reserva.getFecha_creacion(),
            reserva.getFecha_reserva(),
            false  // isActivo = false es convertida
        );
        reservaDAO.ActualizarRegistros(reservaConvertida);
        System.out.println("Reserva " + codigoReserva
            + " convertida al ticket " + numeroTicket + ".");
    }
 
    // Verificar reservas vencidas 
    public int cancelarReservasVencidas() throws Exception {
        List<Reserva> lista = reservaDAO.listarReservas();
        int canceladas = 0;
 
        for (Reserva r : lista) {
 
            if (!r.isActivo()) continue;
 
            // LocalDateTime.parse() acepta el formato ISO que genera toString()
            LocalDateTime fechaCreacion;
            try {
                fechaCreacion = LocalDateTime.parse(r.getFecha_creacion());
            } catch (Exception e) {
                System.out.println("Aviso: no se pudo parsear la fecha de creación "
                    + "de la reserva " + r.getCodigo() + ". Se omite.");
                continue;
            }
 
            long horasTranscurridas = ChronoUnit.HOURS.between(fechaCreacion, LocalDateTime.now());
 
            if (horasTranscurridas > MaximoHoras) {
                Reserva vencida = new Reserva(
                    r.getCodigo(), r.getPasajero(), r.getVehiculo(),
                    r.getFecha_creacion(), r.getFecha_reserva(),
                    false  // isActivo = false es vencida
                );
                reservaDAO.ActualizarRegistros(vencida);
                canceladas++;
                System.out.println("Reserva " + r.getCodigo() + " vencida y cancelada."
                    + " (" + horasTranscurridas + " horas desde su creación).");
            }
        }
 
        if (canceladas == 0) {
            System.out.println("Verificación completada: no hay reservas vencidas.");
        } else {
            System.out.println("Verificación completada: "
                + canceladas + " reserva(s) cancelada(s) por vencimiento.");
        }
        return canceladas;
    }
        
    //listar de reservas activas
    public List<Reserva> listarActivas() throws Exception {
        
        List<Reserva> activas = new ArrayList<>();
        
        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.isActivo()) {
                activas.add(r);
            }
        }
        return activas;
    }
    
    
    //listar el historial de reservas por pasajero
    public List<Reserva> historialPorPasajero(String idPasajero) throws Exception {
        
        if (idPasajero == null || idPasajero.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del pasajero no puede estar vacío.");
        }
        
        List<Reserva> historial = new ArrayList<>();
        
        for (Reserva r : reservaDAO.listarReservas()) {
            if (r.getPasajero().getId().equals(idPasajero)) {
                historial.add(r);
            }
        }
        if (historial.isEmpty()) {
            System.out.println("No hay reservas registradas para el pasajero: " + idPasajero);
        }
        return historial;
    }
    
    //Metodo que cuentas las reservas activas por vehiculo
    public static int contarReservasActivas(ReservaDAO reservaDAO, String placaVehiculo) {
        int contador = 0;
        try {
            for (Reserva r : reservaDAO.listarReservas()) {
                if (r.getVehiculo().getPlaca().equals(placaVehiculo) && r.isActivo()) {
                    contador++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al contar reservas activas: " + e.getMessage());
        }
        return contador;
    }
}
