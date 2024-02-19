package com.mycompany.clinicamaven.bean;

import com.mycompany.clinicamaven.services.MedicoService;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

@Named(value = "medicoBean")
@RequestScoped
public class MedicoBean {

    private MedicoService service = MedicoService.getInstance();

    public MedicoService getService() {
        return service;
    }

    public void setService(MedicoService service) {
        this.service = service;
    }
    
    public MedicoBean() {
    }
    
}
