/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.dao;

import com.mycompany.clinicamaven.entidades.Historial;
import java.util.List;

/**
 *
 * @author PC
 */
public interface DaoHistorial {
    List<Historial> historialSel();
    
    Historial historialGet(Integer id);
    String historialIns (Historial historial);
    String historialUpd (Historial historial);
    String historialDel (Integer id);
    String getMensaje ();
}
