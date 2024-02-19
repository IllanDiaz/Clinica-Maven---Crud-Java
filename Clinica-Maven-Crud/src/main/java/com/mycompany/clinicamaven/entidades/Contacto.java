package com.mycompany.clinicamaven.entidades;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author PC
 */
@ManagedBean
@RequestScoped
public class Contacto {
    Integer id_contacto;
    String nombre;
    String correo;
    String mensaje;
    public Contacto(){
        
    }

    public Contacto(Integer id_contacto, String nombre, String correo, String mensaje) {
        this.id_contacto = id_contacto;
        this.nombre = nombre;
        this.correo = correo;
        this.mensaje = mensaje;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
