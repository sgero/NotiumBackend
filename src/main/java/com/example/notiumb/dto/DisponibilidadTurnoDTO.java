package com.example.notiumb.dto;
import java.time.LocalDate;
public class DisponibilidadTurnoDTO {




    private int numPersonas;
    private LocalDate fecha;
    private Integer restauranteId;

    // Getters y Setters
    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }

}
