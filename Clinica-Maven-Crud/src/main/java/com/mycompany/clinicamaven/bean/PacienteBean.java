/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.clinicamaven.bean;

import com.mycompany.clinicamaven.services.PacienteService;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author PC
 */
@Named(value = "pacienteBean")
@RequestScoped
public class PacienteBean {

    private PacienteService service = PacienteService.getInstance();

    public PacienteService getService() {
        return service;
    }

    public void setService(PacienteService service) {
        this.service = service;
    }

    public PacienteBean() {
    }

}
