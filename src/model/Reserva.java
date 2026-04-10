/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alvar
 */
public class Reserva {
    
    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fecha_creacion;
    private String fecha_reserva;
    private boolean activo;

    public Reserva() {
    }

    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo, String fecha_creacion, String fecha_reserva, boolean activo) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_reserva = fecha_reserva;
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public boolean isActivo() {
        return activo;
    }
}
