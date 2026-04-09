/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;

import DAO.RutaDAO;
import MODEL.Ruta;
import java.util.List;

/**
 *
 * @author alvar
 */
public class rutaServices {
    
    RutaDAO dao = new RutaDAO();
    
    public void registrar(Ruta r)throws Exception{
        
        if (r.getCodigo()==null) {
            throw new Exception ("El campo del codigo está vacio");
        }
        
        if (r.getC_destino()==null) {
            throw new Exception ("El campo del destino está vacio");
        }
        
        if (r.getC_origen()==null) {
            throw new Exception ("El campo del origen está vacio");
        }
        
        if (r.getDistancia()<=0) {
            throw new Exception ("El campo de la distancia está vacio");
        }
        
        if (r.getTiempo()<=0) {
            throw new Exception("El campo del tiempo está vacio");
        }
        
        if (dao.existeCodigo(r.getCodigo())) {
            throw new Exception ("El codigo ingresado ya existe");
        }
        
        dao.guardar(r);
        
    }
    
    public List<Ruta> listar() throws Exception{
        
        List<Ruta> lista= dao.listarTodas();
        
        if (lista.isEmpty()) {
            throw new Exception ("La lista está vacia");
        }
        
        return lista;
        
    }
    
    public Ruta buscar(String codigo) throws Exception {
        
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("El código no puede estar vacío");
        }
        
        Ruta r = dao.buscarPorCodigo(codigo);
        
        if (r == null) {
            throw new Exception("No existe una ruta con el código: " + codigo);
        }
        
        return r;
    }
    
}
