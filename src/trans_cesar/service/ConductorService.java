/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trans_cesar.service;
import trans_cesar.dao.ConductorDAO;
import trans_cesar.model.Conductor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ANDREA CAROLINA
 */
public class ConductorService {
    
    private ConductorDAO conductorDAO;

    public ConductorService() {
        this.conductorDAO = new ConductorDAO();
    }
    
    private final List<String> categoriasValidas = new ArrayList<> (
        Arrays.asList ("B1", "B2", "C1", "C2"));
    
    public void validarRegistro (int numeroLicencia, String categoriaLicencia, int id, String nombre, LocalDate fechaNacimiento) 
                throws Exception {
        // Inicialmente s verifica que no exista diplicacion de ID
        if (conductorDAO.BuscarId(id) !=null ) {
             throw new IllegalArgumentException("Ya se ingreso un conductor con este ID");
        }
        // Validacion de los datos basicos
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
             throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (fechaNacimiento == null) {
             throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacio");
        }
        if (numeroLicencia <= 0) {
            throw new IllegalArgumentException("El numero de licencia debe ser valido");
        }
        if (categoriaLicencia == null || categoriaLicencia.isEmpty()) {
            throw new IllegalArgumentException("La categoria de licencia no puede estar vacio");
        }
        //Validación de la categoria entre B1, B2, C1, C2
        if (!categoriasValidas.contains(categoriaLicencia.toUpperCase())){
            throw new IllegalArgumentException("Categoria de licencia invalida (B1, B2, C1, C2).");
        }
        
        Conductor conductor = new Conductor (numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento);
        conductorDAO.guardar(conductor);
        System.out.println("Conductor registrado correctamente");
    }
    
    /**
     * Busca un estudiante por ID y carga sus notas.
     */
    public Conductor buscarConductor(String id) {
        Conductor conductor = conductorDAO.BuscarId(id);
        if (conductor == null) {
            throw new IllegalArgumentException("No se encontró estudiante con ID: " + id);
        }
        return conductor;
    }
   
    public List<Conductor> listarConductor() {
        List<Conductor> lista = conductorDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        }
        return lista;
    }
    /**
     * Elimina un estudiante por su ID.
     */
    public void eliminarConductor(String id) {
        if (!conductorDAO.existeId(id)) {
            throw new IllegalArgumentException("No existe estudiante con ID: " + id);
        }
        conductorDAO.eliminarConductor(id);
        System.out.println("Pasajero eliminado exitosamente.");
    }
    
    //Validar que el conductor cumple con todos los requisitos para que se le pueda asignar un vehiculo
    public void conductorParaVehiculo (id) throw Exception {
        Conductor conductor = conductorDAO.BuscarId(id);
        
        if (conductor == null){
            throw new IllegalArgumentException("No existe un conductor con ese ID);
        }
        if (!categoriasValidas.contains(conductor.getCategoriaLicencia())){
            throw new IllegalArgumentException("El codigo tiene una categoria de licencia invalida);
        }
    }
}
