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
public class Conductor extends Persona {
    private int numeroLicencia;
    private String categoriaLicencia;

    public Conductor(int numeroLicencia, String categoriaLicencia, int id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre, fechaNacimiento);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }
    
    public int getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(int numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    @Override
    public void imprimirDetalle() {
        
    }
}
