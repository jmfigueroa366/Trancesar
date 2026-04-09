/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import dao.TicketDAO;
import dao.PasajeroDAO;
import model.PasajeroRegular;
import model.PasajeroEstudiante;
import model.PasajeroAdultoMayor;
import dao.VehiculoDAO;
import model.Bus;
import model.Buseta;
import model.MicroBus;
import model.Ticket;
import model.Pasajero;
import model.Vehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ANDREA CAROLINA
 */
public class TicketService {
    
    private TicketDAO ticketDAO;
    private PasajeroDAO pasajeroDAO;
    private VehiculoDAO vehiculoDAO;

    public TicketService() {
        
    }
    
    public TicketService(TicketDAO ticketDAO, PasajeroDAO pasajeroDAO, VehiculoDAO vehiculoDAO) {
        this.ticketDAO = ticketDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.vehiculoDAO = vehiculoDAO;
    }

    
    public void validarRegistro (String NumeroTicket, String OrigenCiudad, String DestinoCiudad, String idpasajero, String placaVehiculo)
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
        
        LocalDate hoy = LocalDate.now();
        
        //Validar maximo 3 tickets por día 
        int contador = 0;
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
        
        float tarifaFestivo = vehiculo.getTarifa();
        if (festivo(hoy)) {
            tarifaFestivo += tarifaFestivo * 0.20;
        }
        
        //Luego, se aplica el descuento según el tipo de pasajero
        double Descuento = pasajero.calcularDescuento();
        double precioFinal = tarifaFestivo - (tarifaFestivo * Descuento);
        
        Ticket ticket = new Ticket (NumeroTicket, hoy, OrigenCiudad, DestinoCiudad, precioFinal, pasajero, vehiculo);
        //Sobreescribir en el precio final
        ticketDAO.guardar(ticket);
        System.out.println("Precio Final del Ticket: $" + precioFinal);
        System.out.println("Ticket registrado correctamente");
    }
    
    //Metodo auxiliar para dias festivos en el calendario 
    private boolean festivo (LocalDate fecha) {
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
            return true;
        }
        //Jueves festivos configurados
        if (fecha.getDayOfWeek().getValue() == 4) {
            List<LocalDate> juevesFestivos = List.of(
            LocalDate.of(2026, 4, 2) );
            return true;
        }
        //Viernes festivos configurados
        if (fecha.getDayOfWeek().getValue() == 5) {
            List<LocalDate> viernesFestivos = List.of(
            LocalDate.of(2026, 4, 3), 
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 8, 7));
            return true;
        }
        return false;
    }
    
     //Busca un ticket por numero.
    public Ticket BuscarNumeroTicket(String NumeroTicket) {
        Ticket t = ticketDAO.BuscarNumeroTicket(NumeroTicket);
        if (t == null) {
            throw new IllegalArgumentException("No se encontró un ticket con este número: " + NumeroTicket);
        }
        return t;
    }
   
    // Retorna la lista de todos los ticket registrados.
    public List<Ticket> listarTicket() {
        List<Ticket> lista = ticketDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay ticket registrados.");
        }
        return lista;
    }
    
    //Lista de tickets vendidas por fecha especifica
    public List<Ticket> consultarPorFecha(LocalDate fecha) {
        List<Ticket> resultados = new ArrayList<>();
        
        for (Ticket t: ticketDAO.listarTodos()) {
            if (t.getFechaCompra().equals(fecha)){
                resultados.add(t);
            }
        }
        return resultados;
    }
    
    //Lista de tickets vendidas por tipo de vehiculo
    public List<Ticket> consultarPorTipoVehiculo(String tipo) {
        List<Ticket> resultado = new ArrayList<>();
        
        for (Ticket t: ticketDAO.listarTodos()) {
            if (t.getVehiculo() instanceof Bus) {
               resultado.add(t);
            }
            
            if (t.getVehiculo() instanceof MicroBus) {
                resultado.add(t);
            }
            
            if (t.getVehiculo() instanceof Buseta) {
                resultado.add(t);
            }
        }
        return resultado;
    }
    
    //Lista de tickets vendidas por tipo de pasajero
    public List<Ticket> consultarPorTipoPasajero(String tipo) {
        List<Ticket> resultados =  new ArrayList<>();
        
        for (Ticket t: ticketDAO.listarTodos()) {
            if (tipo.equalsIgnoreCase("Pasajero Regular") && t.getPasajero() instanceof PasajeroRegular) {
                resultados.add(t);
            }
            if (tipo.equalsIgnoreCase("Pasajero Estudiante") && t.getPasajero() instanceof PasajeroEstudiante) {
                resultados.add(t);
            }
            if (tipo.equalsIgnoreCase("Pasajero Adulto Mayor") && t.getPasajero() instanceof PasajeroAdultoMayor) {
                resultados.add(t);
            }
        }
        return resultados;
    }
    
    //Resumen del dia actual con el total de tickets vendidos y el total recaudado
    public void resumenPorDia() {
        List<Ticket> lista = ticketDAO.listarTodos();
        
        int totalTickets = 0;
        double recaudado = 0;
        LocalDate hoy = LocalDate.now();
        
        for (Ticket t: lista) {
            if (t.getFechaCompra().equals(hoy)) {
                totalTickets++;
                recaudado += t.getPrecioFinal();
            }
            System.out.println("===== RESUMEN DEL DIA =====");
            System.out.println("|   Fecha:   " + hoy);
            System.out.println("|   Total de tickets vendidos: " + totalTickets);
            System.out.println("|   Total recaudado:   $" + recaudado);
        }
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
