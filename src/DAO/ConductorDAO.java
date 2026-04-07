
package DAO;

import MODEL.Conductor;
import trancesar.util.RutaArchivos;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author ANDREA CAROLINA
 */
public class ConductorDAO {
    ArrayList <Conductor> c = new ArrayList<>(); 
    
    public void guardar(Conductor conductor) throws IOException {
        try (BufferedWriter bw = new BufferedWriter ( 
                new FileWriter (RutaArchivos.Conductores, true))) {        
            bw.write(conductor.getId() + ";" +
                     conductor.getNombre() + ";" +
                     conductor.getFechaNacimiento() + ";" +
                     conductor.getNumeroLicencia() + ";" +
                     conductor.getCategoriaLicencia());
                    bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }
    
    public Conductor BuscarId (String id) {
        try (BufferedReader br = new BufferedReader (
                new FileReader (RutaArchivos.Conductores))) {
                String linea; 
                while ((linea = br.readLine()) !=null) {
                    if (linea.trim().isEmpty()) continue;
                    String [] datos = linea.split (";");
                    if (datos[0].equals(id)) {
                        String ide = datos[0];
                        String nombre = datos[1];
                        LocalDate fechaNacimiento = LocalDate.parse(datos[2]);
                        String numeroLicencia = datos[3];
                        String categoriaLicencia = datos [4];
                        return new Conductor(numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento);
                    }
                }
            } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
        return null;
    }
    
    public List<Conductor> listarTodos() {
        List<Conductor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (
            new FileReader (RutaArchivos.Conductores))) {
            String linea;
            while ((linea = br.readLine()) !=null) {
                if (linea.trim().isEmpty()) continue; 
                    String[] datos = linea.split(";"); {
                    String id = datos[0] ;
                    String nombre = datos[1];
                    LocalDate fechaNacimiento = LocalDate.parse(datos[2]);
                    String numeroLicencia = datos[3];
                    String categoriaLicencia = datos [4];
                    lista.add( new Conductor (numeroLicencia, categoriaLicencia, id, nombre, fechaNacimiento));
                    }
            }
        } catch (IOException e) {
                    System.out.println("Error al guardar estudiante: " + e.getMessage());
                }
        return lista;
    }
    
    public void eliminarConductor(String id) {
        List<Conductor> conductor = listarTodos();
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (RutaArchivos.Pasajeros, false))) {
            for (Conductor c : conductor) {
                if (!c.getId().equals(id)) {
                    bw.write(c.getId()+";"+c.getNombre()+";"+c.getFechaNacimiento()+";"+c.getNumeroLicencia()+";"+c.getCategoriaLicencia());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar pasajero: " + e.getMessage());
        }
    }
    
    public boolean existeId(String id) {
        return BuscarId(id) != null;
    }
}
