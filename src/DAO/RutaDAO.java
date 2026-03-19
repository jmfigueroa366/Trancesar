/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Ruta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    
}
