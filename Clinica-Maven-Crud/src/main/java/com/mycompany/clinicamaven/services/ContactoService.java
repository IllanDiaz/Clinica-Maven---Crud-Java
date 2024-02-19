/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.services;

import com.mycompany.clinicamaven.dao.DaoContacto;
import com.mycompany.clinicamaven.dao.impl.DaoContactoImpl;
import com.mycompany.clinicamaven.entidades.Contacto;
import java.util.List;

/**
 *
 * @author PC
 */
public class ContactoService {
    
    private final DaoContacto repositorio = new DaoContactoImpl();
    private static final ContactoService INSTANCE = new ContactoService();
    private Contacto contacto = new Contacto();
    private Integer id_contacto;
    
    public ContactoService(){      
    }

    public static ContactoService getInstance(){
        return INSTANCE;
    }
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }
    
    public List<Contacto> contactoSel(){
        return repositorio.contactoSel();
    }
    public Contacto contactoGet(Integer id){
        reiniciarFormulario();
        return repositorio.contactoGet(id);
    } 
    public String contactoIns(){
        return repositorio.contactoIns(this.contacto);
    }
    public String contactoDel(Integer id){
        return repositorio.contactoDel(id);
    }
    
    public void reiniciarFormulario(){
        this.contacto.setNombre(null);
        this.contacto.setCorreo(null);
        this.contacto.setMensaje(null);
    }
    
    
}
