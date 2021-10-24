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
public class GananciaPorTour {
    private String nombre;
    private Double ganancia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public GananciaPorTour() {
    }

    public GananciaPorTour(String nombre, Double ganancia) {
        this.nombre = nombre;
        this.ganancia = ganancia;
    }
    
}
