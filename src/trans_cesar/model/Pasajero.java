/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

/**
 *
 * @author ANDREA CAROLINA
 */
public class Pasajero extends Persona {
    private String descuento;

    public Pasajero(String descuento) {
        this.descuento = descuento;
    }

    public Pasajero(String descuento, int id, String nombre, int edad) {
        super(id, nombre, edad);
        this.descuento = descuento;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
}
