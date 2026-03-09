/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import MODEL.Vehiculo;
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
public class VehiculoDAO {
    
    ArrayList <MicroBus> mbs=new ArrayList<>();
    ArrayList <Bus> bs=new ArrayList<>();
    ArrayList <Buseta> bts=new ArrayList<>();
    
    public void guardarVehiculo(Vehiculo v){
        
        String ruta="";
        
        if (v instanceof Bus) {
            ruta=RutaArchivos.buses;
        }
        
        if (v instanceof Buseta) {
            ruta=RutaArchivos.busetas;
        }
        
        if (v instanceof MicroBus) {
            ruta=RutaArchivos.microBusetas;
        }
        
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ruta, true))) {
            bw.write(v.getPlaca() + ";" + 
                     v.getRuta() + ";" + 
                     v.getCapacidad() + ";" + 
                     v.getTarifa() + ";" +
                     v.isDisponible());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar vehiculo: " + e.getMessage());
        }
        
    }

    public Vehiculo buscarPorPlaca(String placa){

        Vehiculo v = buscarEnArchivo(RutaArchivos.buses, "Bus", placa);

        if(v == null){
            v = buscarEnArchivo(RutaArchivos.busetas, "Buseta", placa);
        }

        if(v == null){
            v = buscarEnArchivo(RutaArchivos.microBusetas, "MicroBus", placa);
        }

        return v;
    }
    
    private Vehiculo buscarEnArchivo(String ruta,String tipo, String placaBuscada){
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while((linea = br.readLine()) != null){

                if(linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                String placa = datos[0];
                String rutaVehiculo = datos[1];
                int capacidad = Integer.parseInt(datos[2]);
                float tarifa = Float.parseFloat(datos[3]);
                boolean disponible = Boolean.parseBoolean(datos[4]);
                
                if (placa.equals(placaBuscada)) {
                    
                    if (tipo.equals("Bus")) {
                        return new Bus(capacidad, tarifa, placa, ruta, disponible);
                    }
                    
                    if (tipo.equals("MicroBus")) {
                        return new MicroBus(capacidad, tarifa, placa, ruta, disponible);
                    }
                    
                    if (tipo.equals("Buseta")) {
                        return new Buseta(capacidad, tarifa, placa, ruta, disponible);
                    }
                    
                }
            } 
            
            
        }catch(IOException e){
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return null;
        }
    
    public List<Vehiculo> listarTodos(){
        
        List<Vehiculo> lista =new ArrayList<>();
        
        listarDesdeArchivo(RutaArchivos.buses, "Bus", lista);
        listarDesdeArchivo(RutaArchivos.busetas, "Busetas", lista);
        listarDesdeArchivo(RutaArchivos.microBusetas, "MicroBus", lista);
        
        return lista;
    }
    
    private void listarDesdeArchivo(String ruta,String tipo,List<Vehiculo> lista){
        
        try (BufferedReader leer=new BufferedReader(new FileReader(ruta))){
            
            String linea;
            
            while((linea=leer.readLine()) !=null){
                if (linea.trim().isEmpty()) continue; 
                
                String datos[] = linea.split(";");
                
                String placa = datos[0];
                String rutaVehiculo = datos[1];
                int capacidad = Integer.parseInt(datos[2]);
                float tarifa = Float.parseFloat(datos[3]);
                boolean disponible = Boolean.parseBoolean(datos[4]);
                
                Vehiculo v = null;
                
                if (tipo.equals("Bus")) {
                    v=new Bus(capacidad, tarifa, placa, ruta, disponible);
                }
                
                if (tipo.equals("Buseta")) {
                    v=new Buseta(capacidad, tarifa, placa, ruta, disponible);
                }
 
                if (tipo.equals("McroBus")) {
                    v=new MicroBus(capacidad, tarifa, placa, ruta, disponible);
                }
                
                if (v!=null) {
                    lista.add(v);
                }
                
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public boolean exisPlaca(String placa){
        
        Vehiculo v =buscarPorPlaca(placa);
        
        if (v !=null) {
            return true;
        }else{
            return false;
        }
        
        
    }
}
