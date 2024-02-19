/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clinicamaven.dao;

import com.mycompany.clinicamaven.entidades.Medico;
import java.util.List;

/**
 *
 * @author PC
 */
public interface DaoMedico {
    List<Medico> medicoSel(); // select * from
    
    Medico medicoGetByDni(String codigo);
    
    Medico medicoGet(Integer id_medico); //select * rom where
    
    String medicoIns(Medico medico); // insert
    
    String medicoUpd(Medico medico); //update
    
    String medicoDel(Integer id_medico);
    
    boolean medicoLogin(String nombremedico, String codigo);
    
    String getMensaje();
}

