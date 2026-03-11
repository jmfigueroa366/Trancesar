/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

/**
 *
 * @author ANDREA CAROLINA
 */
public abstract class Pasajero extends Persona implements Imprimible {
    private int tipo; //Esta variable define a que grupo pertenece, si estudiante, aldulto mayor o regular.
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
    
        @Override
    public void imprimirDetalle() {
       
    }
    
    public abstract double calcularDescuento(); 
    
}
