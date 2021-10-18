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
public class RecomendacionTour {
    private Recomendacion recomendacion;
    private Tour tour;

    public RecomendacionTour(Recomendacion recomendacion, Tour tour) {
        this.recomendacion = recomendacion;
        this.tour = tour;
    }

    public RecomendacionTour() {
    }

    public Recomendacion getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Recomendacion recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    
}
