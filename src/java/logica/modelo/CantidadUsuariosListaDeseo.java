/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

/**
 *
 * @author hp
 */
public class CantidadUsuariosListaDeseo {
    private String nombre;
    private long cantidadusuarios;

    public CantidadUsuariosListaDeseo() {
    }

    public CantidadUsuariosListaDeseo(String nombre, long cantidadusuarios) {
        this.nombre = nombre;
        this.cantidadusuarios = cantidadusuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCantidadusuarios() {
        return cantidadusuarios;
    }

    public void setCantidadusuarios(long cantidadusuarios) {
        this.cantidadusuarios = cantidadusuarios;
    }
    
}
