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
        this.tipo = tipo;
    }

    public Pasajero(int tipo, String descuento) {
        this.tipo = tipo;
        this.descuento = descuento;
    }

    public Pasajero(int tipo, String descuento, int id, String nombre, int edad) {
        super(id, nombre, edad);
        this.tipo = tipo;
        this.descuento = descuento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    
    
    public abstract double calcularDescuento(); 

    @Override
    public void imprimirDetalle() {
        
    }
}
