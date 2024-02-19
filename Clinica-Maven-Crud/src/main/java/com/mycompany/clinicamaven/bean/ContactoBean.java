/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.clinicamaven.bean;

import com.mycompany.clinicamaven.services.ContactoService;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;

/**
 *
 * @author PC
 */
@Named(value = "contactoBean")
@Dependent
public class ContactoBean {
    
    private ContactoService service = ContactoService.getInstance();

    public ContactoService getService() {
        return service;
    }

    public void setService(ContactoService service) {
        this.service = service;
    }
    
            
    public ContactoBean() {
    }
    
}
