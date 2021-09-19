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
public class ListaDeseo {
    private Usuario usuario;
    private Tour tour;

    public ListaDeseo(Usuario usuario, Tour tour) {
        this.usuario = usuario;
        this.tour = tour;
    }

    public ListaDeseo() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    
}
