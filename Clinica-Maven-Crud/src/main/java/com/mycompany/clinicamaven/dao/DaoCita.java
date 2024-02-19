/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clinicamaven.dao;

import com.mycompany.clinicamaven.entidades.Cita;
import java.util.List;

/**
 *
 * @author PC
 */
public interface DaoCita {
    List<Cita> citaSel();
    
    Cita citaGet (Integer id);
    String citaIns(Cita cita);
    String citaUpd (Cita cita);
    String citaDel (Integer id);
    String getMensaje();
}
