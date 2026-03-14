/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.model;

/**
 *
 * @author ANDREA CAROLINA
 */
public class Conductor extends Persona implements Imprimible {
    private int numeroLicencia;
    private String categoria;

    public Conductor(int numeroLicencia, String categoria) {
        this.numeroLicencia = numeroLicencia;
        this.categoria = categoria;
    }

    public Conductor(int numeroLicencia, String categoria, int id, String nombre, int edad) {
        super(id, nombre, edad);
        this.numeroLicencia = numeroLicencia;
        this.categoria = categoria;
    }

    public int getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(int numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void imprimirDetalle() {
        
    }
}
