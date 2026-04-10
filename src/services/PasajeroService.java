/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.PasajeroDAO;
import model.Pasajero;
import model.PasajeroRegular;
import model.PasajeroEstudiante;
import model.PasajeroAdultoMayor;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 * @author ANDREA CAROLINA
 */
public class PasajeroService {
    
    private PasajeroDAO pasajeroDAO;

    public PasajeroService() {
        this.pasajeroDAO = new PasajeroDAO();
    }
    
    
    public void validarRegistro (String id, String nombre, LocalDate fechaNacimiento, String tipoIngresado) throws Exception {
        
        // Validacion de los datos basicos
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        } 
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (fechaNacimiento == null) {
             throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacio");
        }
        
        // Validar que no exista diplicacion de ID
        if (pasajeroDAO.BuscarId(id) !=null ) {
             throw new IllegalArgumentException("Ya se ingreso un pasajero con este ID");
        }
        
        //Determinar la edad del pasajero
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        Pasajero pasajero;

        // Definir si es AdultoMayor, de lo contrario, el usuario define estudiante o pasajero regular
        if (edad >= 60) {
            pasajero = new PasajeroAdultoMayor(id, nombre, fechaNacimiento);
            System.out.println("Pasajero registrado como ADULTO MAYOR");
        } else {
            switch (tipoIngresado) {
                case "Pasajero Estudiante":
                    pasajero = new PasajeroEstudiante(id, nombre, fechaNacimiento);
                    break;
                case "Pasajero Regular":
                    pasajero = new PasajeroRegular(id, nombre, fechaNacimiento);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Tipo de pasajero inválido: '" + tipoIngresado + "'. "
                        + "Use 'Pasajero Estudiante' o 'Pasajero Regular'.");
            }
        }
        
        pasajeroDAO.guardar(pasajero);
        System.out.println("El pasajero se ha registrado exitosamente...");
        }

    //Busca un pasajero por ID.
    public Pasajero buscarPasajero(String id) {
        Pasajero pasajero = pasajeroDAO.BuscarId(id);
        if (pasajero == null) {
            throw new IllegalArgumentException("No se encontró pasajero con ID: " + id);
        }
        return pasajero;
    }
   
    // Retorna la lista de todos los pasajeros registrados.
    public List<Pasajero> listarPasajero() {
        List<Pasajero> lista = pasajeroDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        }
        return lista;
    }
    
    // Elimina un pasajero por su ID.
    public void eliminarPasajero(String id) {
        if (!pasajeroDAO.existeId(id)) {
            throw new IllegalArgumentException("No existe pasajero con ID: " + id);
        }
        pasajeroDAO.eliminarPasajero(id);
        System.out.println("Pasajero eliminado exitosamente.");
    }
}
