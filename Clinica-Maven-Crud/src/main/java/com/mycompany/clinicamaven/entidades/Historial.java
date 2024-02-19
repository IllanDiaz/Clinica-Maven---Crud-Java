/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.entidades;

/**
 *
 * @author PC
 */
public class Historial {
    Integer id_historial;
    String sintomas;        
    String diagnostico;       
    String tratamiento;
    Integer paciente_id_paciente;        
    Integer medico_id_medico;       
                    
public Historial(){  
}
        
    public Historial(Integer id_historial, String sintomas, String diagnostico, String tratamiento, Integer paciente_id_paciente, Integer medico_id_medico) {
        this.id_historial = id_historial;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.paciente_id_paciente = paciente_id_paciente;
        this.medico_id_medico = medico_id_medico;
    }

    public Integer getId_historial() {
        return id_historial;
    }

    public void setId_historial(Integer id_historial) {
        this.id_historial = id_historial;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Integer getPaciente_id_paciente() {
        return paciente_id_paciente;
    }

    public void setPaciente_id_paciente(Integer paciente_id_paciente) {
        this.paciente_id_paciente = paciente_id_paciente;
    }

    public Integer getMedico_id_medico() {
        return medico_id_medico;
    }

    public void setMedico_id_medico(Integer medico_id_medico) {
        this.medico_id_medico = medico_id_medico;
    }
            
            
}
