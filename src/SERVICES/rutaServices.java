/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SERVICES;

import DAO.RutaDAO;
import MODEL.Ruta;

/**
 *
 * @author alvar
 */
public class rutaServices {
    
    RutaDAO dao = new RutaDAO();
    
    public void registrar(Ruta r)throws Exception{
        
        if (r.getCodigo().trim().isEmpty()) {
            throw new Exception ("El campo del codigo está vacio");
        }
        
        if (r.getC_destino().trim().isEmpty()) {
            throw new Exception ("El campo del destino está vacio");
        }
        
        if (r.getC_origen().trim().isEmpty()) {
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
    
}
