/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;
import DAO.TicketDAO;
import DAO.PasajeroDAO;
import MODEL.PasajeroRegular;
import MODEL.PasajeroEstudiante;
import MODEL.PasajeroAdultoMayor;
import DAO.VehiculoDAO;
import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import MODEL.Ticket;
import MODEL.Pasajero;
import MODEL.Vehiculo;
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
    
    public TicketService(TicketDAO ticketDAO, PasajeroDAO pasajeroDAO, VehiculoDAO vehiculoDAO) {
        this.ticketDAO = ticketDAO;
        this.pasajeroDAO = pasajeroDAO;
        this.vehiculoDAO = vehiculoDAO;
    }

    
    public void validarRegistro (String NumeroTicket, String idpasajero, String placaVehiculo, String OrigenCiudad, String DestinoCiudad)
            throws Exception {
        
        // Validacion de los datos basicos
        if (NumeroTicket == null || NumeroTicket.trim().isEmpty()) {
            throw new IllegalArgumentException ("El numero de ticket no puede estar vacio");
        }
        if (OrigenCiudad == null || OrigenCiudad.trim().isEmpty()) {
             throw new IllegalArgumentException("La ciudad de origen no puede estar vacio");
        }
        if (DestinoCiudad == null || DestinoCiudad.trim().isEmpty()) {
             throw new IllegalArgumentException("La ciudad de destino no puede estar vacio");
        }
        
        // Verifica que no exista diplicacion de Numero de Ticket
        if (ticketDAO.BuscarNumeroTicket(NumeroTicket) !=null) {
             throw new IllegalArgumentException("Ya se ingreso este numero de ticket" + NumeroTicket);
        }
        
        //Validar el pasajero
        Pasajero pasajero = pasajeroDAO.BuscarId(idpasajero);
        if (pasajero == null) {
             throw new IllegalArgumentException("No existe pasajero con este id" + idpasajero);
        }
        
        //Validar el vehiculo
        Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
        if (vehiculo ==  null) {
            throw new IllegalArgumentException("No existe vehiculo con este placa" + placaVehiculo);
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
            throw new Exception ("El pasajero ya tiene" + contador + "ticket(s) comprados hoy. Son maximo 3 tickets por día. ID: " + pasajero.getId());
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
        
        double tarifaFestivo = vehiculo.getTarifa();
        if (festivo(hoy)) {
            tarifaFestivo += tarifaFestivo * 0.20;
            System.out.println("Nota: día festivo la tarifa incrementada 20%."
                + " Tarifa ajustada: $" + String.format("%.2f", tarifaFestivo));
        }
        
        //Luego, se aplica el descuento según el tipo de pasajero
        double Descuento = pasajero.calcularDescuento();
        double precioFinal = tarifaFestivo - (tarifaFestivo * Descuento);
        
        Ticket ticket = new Ticket (NumeroTicket, pasajero, vehiculo, hoy, OrigenCiudad, DestinoCiudad, precioFinal);
        
        //Sobreescribir en el precio final
        ticketDAO.guardar(ticket);
        System.out.println("Precio Final del Ticket: $" + precioFinal);
        System.out.println("Ticket registrado correctamente");
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
            if (tipo.equalsIgnoreCase("Bus") && t.getVehiculo() instanceof Bus) {
               resultado.add(t);
            }
            
            if (tipo.equalsIgnoreCase("MicroBus") && t.getVehiculo() instanceof MicroBus) {
                resultado.add(t);
            }
            
            if (tipo.equalsIgnoreCase("Buseta") && t.getVehiculo() instanceof Buseta) {
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
        
        LocalDate hoy = LocalDate.now();
        int totalTickets = 0;
        double recaudado = 0;
        
        for (Ticket t: lista) {
            if (t.getFechaCompra().equals(hoy)) {
                totalTickets++;
                recaudado += t.getPrecioFinal();
            } 
        }
        System.out.println("===== RESUMEN DEL DIA =====");
        System.out.println("|   Fecha:   " + hoy);
        System.out.println("|   Total de tickets vendidos: " + totalTickets);
        System.out.printf("|   Total recaudado:   $%.2f%n", recaudado);
    }
    
    // Elimina un ticket por su numero.
    public void eliminarTicket(String NumeroTicket) {
        if (!ticketDAO.existeNumeroTicket(NumeroTicket)) {
            throw new IllegalArgumentException("No existe ticket con este número: " + NumeroTicket);
        }
        ticketDAO.eliminarTicket(NumeroTicket);
        System.out.println("Ticket eliminado exitosamente.");
    }
    
    
    //Metodo auxiliar para dias festivos en el calendario 
    private boolean festivo (LocalDate fecha) {
        
        // Festivos de fecha fija (siempre en el mismo día)
        List<LocalDate> festivosFijos = List.of(
            LocalDate.of(fecha.getYear(), 1,  1),   // Año Nuevo
            LocalDate.of(fecha.getYear(), 5,  1),   // Día del Trabajo
            LocalDate.of(fecha.getYear(), 7, 20),   // Independencia de Colombia
            LocalDate.of(fecha.getYear(), 8,  7),   // Batalla de Boyacá
            LocalDate.of(fecha.getYear(), 12, 8),   // Inmaculada Concepción
            LocalDate.of(fecha.getYear(), 12, 25)   // Navidad
        );
        
        if (festivosFijos.contains(fecha)) return true;
       
        // Festivos de ley puente (trasladados al lunes) y fechas móviles para 2026
        List<LocalDate> festivosMoviles2026 = List.of(
            LocalDate.of(2026, 1,  12),  // Reyes Magos (puente)
            LocalDate.of(2026, 3,  23),  // San José (puente)
            LocalDate.of(2026, 4,   2),  // Jueves Santo
            LocalDate.of(2026, 4,   3),  // Viernes Santo
            LocalDate.of(2026, 5,  18),  // Ascensión del Señor (puente)
            LocalDate.of(2026, 6,   8),  // Corpus Christi (puente)
            LocalDate.of(2026, 6,  15),  // Sagrado Corazón (puente)
            LocalDate.of(2026, 6,  29),  // San Pedro y San Pablo (puente)
            LocalDate.of(2026, 8,  17),  // Asunción de la Virgen (puente)
            LocalDate.of(2026, 10, 12),  // Día de la Raza (puente)
            LocalDate.of(2026, 11,  2),  // Todos los Santos (puente)
            LocalDate.of(2026, 11, 16)   // Independencia de Cartagena (puente)
        );
 
        return festivosMoviles2026.contains(fecha);
    }
}
