/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clinicamaven.dao;

import com.mycompany.clinicamaven.entidades.Contacto;
import java.util.List;

/**
 *
 * @author PC
 */
public interface DaoContacto {
    List<Contacto> contactoSel();
    
    Contacto contactoGet(Integer id);
    String contactoIns (Contacto contacto);
    String contactoUpd (Contacto contacto);
    String contactoDel (Integer id);
    String getMensaje();
}
