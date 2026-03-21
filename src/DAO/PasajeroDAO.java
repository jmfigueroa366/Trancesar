/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Pasajero;
import MODEL.PasajeroRegular;
import MODEL.PasajeroEstudiante;
import MODEL.PasajeroAdultoMayor;
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
public class PasajeroDAO {
    
    ArrayList <Pasajero> p = new ArrayList<>(); 
    
    
    public void guardar(Pasajero pasajero) throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter ( 
                new FileWriter (RutaArchivos.Pasajeros, true))) {
                    String tipo;
                    if (pasajero instanceof PasajeroRegular) {
                        tipo = "Pasajero Regular";
                    } else if (pasajero instanceof PasajeroEstudiante) {
                        tipo = "Pasajero Estudiante";
                    } else {
                        tipo = "Pasajero Adulto Mayor";
                    }
                    
            bw.write(pasajero.getId() + ";" +
                     pasajero.getNombre() + ";" +
                     pasajero.getFechaNacimiento() + ";" +
                     tipo + ";" +
                     pasajero.calcularDescuento());
                    bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
    }
    
    
    public Pasajero BuscarId (String id) {
        try (BufferedReader br = new BufferedReader (
                new FileReader (RutaArchivos.Pasajeros))) {
                String linea; 
                while ((linea = br.readLine()) !=null) {
                    if (linea.trim().isEmpty()) continue;
                    String [] datos = linea.split (";");
                    if (datos[0].equals(id)) {
                        String ide = datos[0];
                        String nombre = datos[1];
                        LocalDate fechaNacimiento = LocalDate.parse(datos[2]);
                        String tipo = datos[3];
                        double Descuento = Double.parseDouble(datos[4]);
                        
                        Pasajero pasajero;
                    switch (tipo) {
                        case "Pasajero Regular":
                            pasajero = new PasajeroRegular (Descuento, id, nombre, fechaNacimiento);
                            break;
                        case "Pasajero Estudiante":
                            pasajero = new PasajeroEstudiante(Descuento, id, nombre, fechaNacimiento);
                            break;
                        case "Pasajero Adulto Mayor":
                            pasajero = new PasajeroAdultoMayor(Descuento, id, nombre, fechaNacimiento);
                            break;
                        default:
                            pasajero=null;
                    } 
                    return pasajero;
                    }
                }
            } catch (IOException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
        }
        return null;
    }
    
    
    public List<Pasajero> listarTodos() {
        List<Pasajero> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (
            new FileReader (RutaArchivos.Pasajeros))) {
            String linea;
            while ((linea = br.readLine()) !=null) {
                if (linea.trim().isEmpty()) continue; 
                    String[] datos = linea.split(";"); {
                    String id = datos[0] ;
                    String nombre = datos[1];
                    LocalDate fechaNacimiento = LocalDate.parse(datos[2]);
                    String tipo = datos[3];
                    double Descuento = Double.parseDouble(datos[4]) ;
                    
                    Pasajero pasajero;
                    switch (tipo) {
                        case "Pasajero Regular":
                            pasajero = new PasajeroRegular(Descuento, tipo, nombre, fechaNacimiento);
                            break;
                        case "Pasajero Estudiante":
                            pasajero = new PasajeroEstudiante(Descuento, tipo, nombre, fechaNacimiento);
                            break;
                        case "Pasajero Adulto Mayor":
                            pasajero = new PasajeroAdultoMayor(Descuento, tipo, nombre, fechaNacimiento);
                            break;
                        default:
                            pasajero=null;
                    } 
                    
                if (pasajero !=null) {
                    lista.add(pasajero);
                }
            }
        } } catch (IOException e) {
                    System.out.println("Error al guardar estudiante: " + e.getMessage());
                }
        return lista;
    }
    
    public void eliminarPasajero(String id) {
        List<Pasajero> pasajeros = listarTodos();
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (RutaArchivos.Pasajeros, false))) {
            for (Pasajero p : pasajeros) {
                if (!p.getId().equals(id)) {
                    String tipo;
                    if (pasajeros instanceof PasajeroRegular) {
                        tipo = "Pasajero Regular";
                    } else if (pasajeros instanceof PasajeroEstudiante) {
                        tipo = "Pasajero Estudiante";
                    } else {
                        tipo = "Pasajero Adulto Mayor";
                    }
                    bw.write(p.getId()+";"+p.getNombre()+";"+p.getFechaNacimiento()+";"+tipo+";"+p.calcularDescuento());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar pasajero: " + e.getMessage());
        }
    }

    public boolean existeId(String id) {
        return BuscarId(id) != null;
    }
}

