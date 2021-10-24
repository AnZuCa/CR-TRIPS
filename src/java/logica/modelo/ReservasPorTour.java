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
public class ReservasPorTour {
    private String nombre;
    private long cantidad_reservas;

    public ReservasPorTour() {
    }

    public ReservasPorTour(String nombre, long cantidad_reservas) {
        this.nombre = nombre;
        this.cantidad_reservas = cantidad_reservas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCantidad_reservas() {
        return cantidad_reservas;
    }

    public void setCantidad_reservas(long cantidad_reservas) {
        this.cantidad_reservas = cantidad_reservas;
    }
    
}
