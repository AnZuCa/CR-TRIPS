/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.util.Date;

/**
 *
 * @author hp
 */
public class ReservasPorFecha {
    private Date fecha;
    private long cantidad_reservas;

    public ReservasPorFecha() {
    }

    public ReservasPorFecha(Date fecha, long cantidad_reservas) {
        this.fecha = fecha;
        this.cantidad_reservas = cantidad_reservas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getCantidad_reservas() {
        return cantidad_reservas;
    }

    public void setCantidad_reservas(long cantidad_reservas) {
        this.cantidad_reservas = cantidad_reservas;
    }
    
    
}
