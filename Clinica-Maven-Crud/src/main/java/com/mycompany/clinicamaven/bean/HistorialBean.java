/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.clinicamaven.bean;

import com.mycompany.clinicamaven.services.HistorialService;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author U149
 */
@Named(value = "historialBean")
@RequestScoped
public class HistorialBean {

    private HistorialService service = HistorialService.getInstance();
    
    public HistorialService getService(){
        return service;
    }
    
    public void SetService(HistorialService service){
        this.service = service;
    }
    
    public HistorialBean() {
    }
    
}
