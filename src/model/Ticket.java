/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Imprimible;
import model.Calculable;
import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public class Ticket implements Imprimible, Calculable {
    private String NumeroTicket;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCompra;
    private String OrigenCiudad;
    private String DestinoCiudad;
    private double PrecioFinal;

    public Ticket(String NumeroTicket, Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCompra, String OrigenCiudad, String DestinoCiudad, double PrecioFinal) {
        this.NumeroTicket = NumeroTicket;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.OrigenCiudad = OrigenCiudad;
        this.DestinoCiudad = DestinoCiudad;
        this.PrecioFinal = 0;
    }

    public String getNumeroTicket() {
        return NumeroTicket;
    }

    public void setNumeroTicket(String NumeroTicket) {
        this.NumeroTicket = NumeroTicket;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getOrigenCiudad() {
        return OrigenCiudad;
    }

    public void setOrigenCiudad(String OrigenCiudad) {
        this.OrigenCiudad = OrigenCiudad;
    }

    public String getDestinoCiudad() {
        return DestinoCiudad;
    }

    public void setDestinoCiudad(String DestinoCiudad) {
        this.DestinoCiudad = DestinoCiudad;
    }

    public double getPrecioFinal() {
        return PrecioFinal;
    }

    public void setPrecioFinal(double PrecioFinal) {
        this.PrecioFinal = PrecioFinal;
    }

    @Override
    public double calcularTotal() {
        double tarifa = vehiculo.getTarifa();
        double Descuento = pasajero.calcularDescuento();
        PrecioFinal = tarifa -(tarifa*Descuento);
        return PrecioFinal;
    }
    
        @Override
    public void imprimirDetalle() {
         System.out.println("TICKET");
         System.out.println("Numero de Ticket: " + getNumeroTicket());
         System.out.println("Fecha de Compra: " + getFechaCompra());
         System.out.println("Nombre del Pasajero: " + pasajero.getNombre());
         System.out.println("Placa del Vehiculo: " + vehiculo.getPlaca());
         System.out.println("Ciudad de Origen: " + getOrigenCiudad());
         System.out.println("Ciudad de Destino: " + getDestinoCiudad());
         System.out.println("Precio Final del Ticket: $" + getPrecioFinal());
    }

}
