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
public class TourReservaSalida {
    private Salida salida;
    private TourReserva tourreserva;

    public TourReservaSalida() {
    }

    public TourReservaSalida(Salida salida, TourReserva tourreserva) {
        this.salida = salida;
        this.tourreserva = tourreserva;
    }

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
    }

    public TourReserva getTourreserva() {
        return tourreserva;
    }

    public void setTourreserva(TourReserva tourreserva) {
        this.tourreserva = tourreserva;
    }
    
}
