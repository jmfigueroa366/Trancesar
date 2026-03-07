/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author alvar
 */
public abstract class Vehiculo {
    
    private int capacidad;
    private float tarifa;
    private String placa;
    private String ruta;
    private boolean disponible;

    public Vehiculo() {
    }

    public Vehiculo(int capacidad, float tarifa, String placa, String ruta, boolean disponible) {
        this.capacidad = capacidad;
        this.tarifa = tarifa;
        this.placa = placa;
        this.ruta = ruta;
        this.disponible = disponible;
    }
    
    public boolean disponible(){
        return disponible;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }    
}
