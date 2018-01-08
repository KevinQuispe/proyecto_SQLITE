package com.alquiler.appalquiler.entidades;

import java.io.Serializable;

/**
 * Created by jesus on 2/11/2017.
 */

public class Habitacion implements Serializable{
    private String DNIinquilino;
    private Integer NumHabitacion;
    private Double precio;
    private String TipoServicio;

    public Habitacion() {
    }

    public Habitacion(String DNIinquilino, Integer numHabitacion, Double precio, String tipoServicio) {
        this.DNIinquilino = DNIinquilino;
        NumHabitacion = numHabitacion;
        this.precio = precio;
        TipoServicio = tipoServicio;
    }

    public String getDNIinquilino() {
        return DNIinquilino;
    }

    public void setDNIinquilino(String DNIinquilino) {
        this.DNIinquilino = DNIinquilino;
    }

    public Integer getNumHabitacion() {
        return NumHabitacion;
    }

    public void setNumHabitacion(Integer numHabitacion) {
        NumHabitacion = numHabitacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoServicio() {
        return TipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        TipoServicio = tipoServicio;
    }
}
