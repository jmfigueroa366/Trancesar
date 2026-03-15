/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;

import DAO.VehiculoDAO;
import MODEL.Vehiculo;
import MODEL.Bus;
import MODEL.Buseta;
import MODEL.MicroBus;
import java.util.List;

/**
 *
 * @author alvar
 */
public class vehiculoServices {
   
    VehiculoDAO dao= new VehiculoDAO();
    
    public void validarRegistro(Vehiculo v) throws Exception {

        if (v.getPlaca().trim().isEmpty()) {
            throw new Exception("Se necesita una placa para el vehiculo");
        }

        if (v.getRuta().trim().isEmpty()) {
            throw new Exception("Se necesita la ruta para continuar");
        }

        validarCapacidad(v);

        validarTarifa(v);

        if (dao.exisPlaca(v.getPlaca())) {  // ← primero valida
            throw new Exception("Esta placa ya existe");
        }

        dao.guardarVehiculo(v);  // ← al final guarda
    }

    public void validarCapacidad(Vehiculo v)throws Exception {
        
        if (v instanceof Bus && v.getCapacidad()>45) {
            throw new Exception("El bus no puede tener mas de 45 pasajeros");
        }
        
        if (v instanceof Buseta && v.getCapacidad()>19) {
            throw new Exception("La buseta no puede tener mas de 19 pasajeros");
        }
        
        if (v instanceof MicroBus && v.getCapacidad()>25) {
           throw new Exception("El microBus no puede tener una capacidad mayor a 25 pasajeros");
        }
        
    }
    
    public void validarTarifa(Vehiculo v)throws Exception{
        
        if (v instanceof Bus && v.getTarifa() < 15000) {
        throw new Exception("La tarifa del Bus no puede ser menor a $15.000");
        }
    
        if (v instanceof Buseta && v.getTarifa() < 8000) {
            throw new Exception("La tarifa de la Buseta no puede ser menor a $8.000");
        }

        if (v instanceof MicroBus && v.getTarifa() < 10000) {
            throw new Exception("La tarifa del MicroBus no puede ser menor a $10.000");
        }
        
    }
    
    public Vehiculo validarBusqueda(String placa)throws Exception{
        
        if (placa == null || placa.trim().isEmpty()) {
        throw new Exception("La placa no puede estar vacía");
        }

        Vehiculo v = dao.buscarPorPlaca(placa);

        if (v == null) {
            throw new Exception("No existe un vehiculo con la placa: " + placa);
        }

        return v;
        
    }
    
    public void eliminar(String placa) throws Exception {
    
        if (placa == null || placa.trim().isEmpty()) {
            throw new Exception("La placa no puede estar vacía");
        }

        if (!dao.exisPlaca(placa)) {
            throw new Exception("No existe un vehiculo con la placa: " + placa);
        }

        dao.eliminar(placa);
    }
    
    public List<Vehiculo> validarListado() throws Exception{
        
        List <Vehiculo> lista = dao.listarTodos();
        
        if (lista.isEmpty()) {
            throw new Exception("La lista no tiene registros disponibles");
        }
        
        return lista;
        
    }
    
}
