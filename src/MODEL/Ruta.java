/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author alvar
 */
public class Ruta {

    private String codigo;
    private String c_origen;
    private String c_destino;
    private float distancia;
    private float tiempo;

    public Ruta() {
    }

    public Ruta(String codigo, String c_origen, String c_destino, float distancia, float tiempo) {
        this.codigo = codigo;
        this.c_origen = c_origen;
        this.c_destino = c_destino;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getC_origen() {
        return c_origen;
    }

    public void setC_origen(String c_origen) {
        this.c_origen = c_origen;
    }

    public String getC_destino() {
        return c_destino;
    }

    public void setC_destino(String c_destino) {
        this.c_destino = c_destino;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }
    
    
    
}
