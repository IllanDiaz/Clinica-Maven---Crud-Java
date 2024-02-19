/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clinicamaven.dao;

import com.mycompany.clinicamaven.entidades.Paciente;
import java.util.List;

/**
 *
 * @author PC
 */
public interface DaoPaciente {
    List<Paciente> pacienteSel();
    
    Paciente pacienteGet(Integer id);
      
    String pacienteIns(Paciente paciente);
    
    String pacienteUpd(Paciente paciente);
    
    String pacienteDel(Integer id);
    
    String getMensaje();
    
    Paciente pacienteLogin(String dni, String password);
}