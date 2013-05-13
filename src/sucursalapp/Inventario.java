/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

/**
 *
 * @author Diego Alienware
 */
public class Inventario {
    private String nombre;
    private String descripcion;
    private String costo;
    private String cantidad;
    
    public Inventario(String nombre, String descripcion, String costo, String cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cantidad = cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
}
