package com.alquiler.appalquiler.entidades;

import java.io.Serializable;

/**
 * Created by jesus on 1/11/2017.
 */

public class persona implements Serializable{

    private Integer id;
    private String nombres;
    private String dni;
    private String telefono;
    private String correo;

    public persona() {
    }

    public persona(Integer id, String dni, String nombres, String telefono, String correo) {
        this.id = id;
        this.nombres = nombres;
        this.dni = dni;
        this.telefono = telefono;
        this.correo=correo;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
