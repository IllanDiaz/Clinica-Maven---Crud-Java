/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.entidades;

/**
 *
 * @author PC
 */
public class Cita {
    Integer id_cita;
    Integer paciente_id_paciente;
    String fecha;
    
    public Cita(){      
    }

    public Cita(Integer id_cita, Integer paciente_id_paciente, String fecha) {
        this.id_cita = id_cita;
        this.paciente_id_paciente = paciente_id_paciente;
        this.fecha = fecha;
    }

    public Integer getId_cita() {
        return id_cita;
    }

    public void setId_cita(Integer id_cita) {
        this.id_cita = id_cita;
    }

    public Integer getPaciente_id_paciente() {
        return paciente_id_paciente;
    }

    public void setPaciente_id_paciente(Integer paciente_id_paciente) {
        this.paciente_id_paciente = paciente_id_paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
