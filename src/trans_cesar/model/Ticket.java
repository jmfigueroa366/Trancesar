/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public class Ticket implements Imprimible, Calculable {
    private int NumeroTicket;
    private LocalDate fechaCompra;
    private String OrigenCiudad;
    private String DestinoCiudad;
    private double PrecioFinal;
    private Pasajero pasajero;
    private Vehiculo vehiculo;

    public Ticket(int NumeroTicket, LocalDate fechaCompra, String OrigenCiudad, String DestinoCiudad, double PrecioFinal, Pasajero pasajero, Vehiculo vehiculo) {
        this.NumeroTicket = NumeroTicket;
        this.fechaCompra = fechaCompra;
        this.OrigenCiudad = OrigenCiudad;
        this.DestinoCiudad = DestinoCiudad;
        this.PrecioFinal = PrecioFinal;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
    }

    public int getNumeroTicket() {
        return NumeroTicket;
    }

    public void setNumeroTicket(int NumeroTicket) {
        this.NumeroTicket = NumeroTicket;
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
        @Override
    public void imprimirDetalle() {
         
    }

    @Override
    public double calcularTotal() {
        return PrecioFinal;
    }
}
