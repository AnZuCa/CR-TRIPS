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
public class IncluyeTour {
    private Incluye incluye;
    private Tour tour;

    public IncluyeTour(Incluye incluye, Tour tour) {
        this.incluye = incluye;
        this.tour = tour;
    }

    public IncluyeTour() {
    }

    public Incluye getIncluye() {
        return incluye;
    }

    public Tour getTour() {
        return tour;
    }

    public void setIncluye(Incluye incluye) {
        this.incluye = incluye;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    
}
