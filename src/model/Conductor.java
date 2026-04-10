/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author ANDREA CAROLINA
 */
public class Conductor extends Persona {
    private String numeroLicencia;
    private String categoriaLicencia; // B1, B2, C1, C2

    public Conductor(String numeroLicencia, String categoriaLicencia, String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre, fechaNacimiento);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
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
        int edad = Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
         System.out.println("CONDUCTOR");
         System.out.println("Cedula: " + getId());
         System.out.println("Nombre: " + getNombre());
         System.out.println("Edad: " + edad + "años");
         System.out.println("Número de Licencia: " + getNumeroLicencia());
         System.out.println("Categoría de Licencia: " + getCategoriaLicencia());
    }
}