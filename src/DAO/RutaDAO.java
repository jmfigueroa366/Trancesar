/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Ruta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import trancesar.util.RutaArchivos;

/**
 *
 * @author alvar
 */
public class RutaDAO {

    public void guardar(Ruta r) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(RutaArchivos.rutas, true))) {
            bw.write(r.getCodigo() + ";" +
                     r.getC_origen() + ";" +
                     r.getC_destino() + ";" +
                     r.getDistancia() + ";" +
                     r.getTiempo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ruta: " + e.getMessage());
        }
    }
    
    public Ruta buscarPorCodigo(String codigo) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(RutaArchivos.rutas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(codigo)) {
                    return new Ruta(datos[0], datos[1], datos[2],
                                    Float.parseFloat(datos[3]),
                                    Float.parseFloat(datos[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error buscando ruta: " + e.getMessage());
        }
        return null;
    }
     
    public boolean existeCodigo(String codigo){
        
        Ruta r=buscarPorCodigo(codigo);
        
        if (r!=null) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public List<Ruta> listarTodas() {
        List<Ruta> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(RutaArchivos.rutas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                lista.add(new Ruta(datos[0], datos[1], datos[2],
                                   Float.parseFloat(datos[3]),
                                   Float.parseFloat(datos[4])));
            }
        } catch (IOException e) {
            System.out.println("Error listando rutas: " + e.getMessage());
        }
        return lista;
    }
     
}
