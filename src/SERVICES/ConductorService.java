
package SERVICES;
import DAO.ConductorDAO;
import MODEL.Conductor;
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
    
    public void validarRegistro (String numeroLicencia, String categoriaLicencia, String id, String nombre, LocalDate fechaNacimiento) 
                throws Exception {
        
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
        if (numeroLicencia == null) {
            throw new IllegalArgumentException("El numero de licencia no puede estar vacio");
        }
        if (categoriaLicencia == null || categoriaLicencia.isEmpty()) {
            throw new IllegalArgumentException("La categoria de licencia no puede estar vacio");
        }
        
        //Validación de la categoria entre B1, B2, C1, C2
        if (!categoriasValidas.contains(categoriaLicencia.toUpperCase())) {
            throw new IllegalArgumentException(
                "Categoría de licencia inválida: '" + categoriaLicencia
                + "'. Valores aceptados: B1, B2, C1, C2.");
        }
        
        // Verificar que no exista diplicacion de ID
        if (conductorDAO.BuscarId(id) !=null ) {
             throw new IllegalArgumentException("Ya se ingreso un conductor con este ID" + id);
        }
        
        Conductor conductor = new Conductor (numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento);
        conductorDAO.guardar(conductor);
        System.out.println("Conductor registrado correctamente");
    }
    
    //Busca un conductor por ID
    public Conductor buscarConductor(String id) {
        Conductor conductor = conductorDAO.BuscarId(id);
        if (conductor == null) {
            throw new IllegalArgumentException("No se encontró conductor con ID: " + id);
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
    //Elimina un conductor por su ID.
    public void eliminarConductor(String id) {
        if (!conductorDAO.existeId(id)) {
            throw new IllegalArgumentException("No existe estudiante con ID: " + id);
        }
        conductorDAO.eliminarConductor(id);
        System.out.println("Pasajero eliminado exitosamente.");
    }
    
    //Validar que el conductor cumple con todos los requisitos para que se le pueda asignar un vehiculo
    public void conductorParaVehiculo(String id) throws Exception {
        Conductor conductor = conductorDAO.BuscarId(id);
        
        if (conductor == null){
            throw new IllegalArgumentException("No existe un conductor con ese ID" + id);
        }
        
        //Verificar que tenga numero de licencia registrada
        if (conductor.getNumeroLicencia()==null || conductor.getNumeroLicencia().trim().isEmpty()) {
            throw new IllegalArgumentException ("El conductor " + conductor.getNombre() + "no tiene numero de licencia registrada");
        }
        // Verificar que la categoria sea valida
        if (!categoriasValidas.contains(conductor.getCategoriaLicencia())){
            throw new IllegalArgumentException("El codigo tiene una categoria de licencia invalida");
        }
    }
}
