/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.services;

import com.mycompany.clinicamaven.dao.DaoPaciente;
import com.mycompany.clinicamaven.dao.impl.DaoPacienteImpl;
import com.mycompany.clinicamaven.entidades.Paciente;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author PC
 */
public class PacienteService {

    private final DaoPaciente repositorio = new DaoPacienteImpl();
    private static final PacienteService INSTANCE = new PacienteService();
    private Paciente paciente = new Paciente();
    private Integer id_paciente;

    public PacienteService() {
    }

    public static PacienteService getInstance() {
        return INSTANCE;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Integer id_paciente) {
        this.id_paciente = id_paciente;
    }

//CRUD
    public List<Paciente> pacienteSel() {
        return repositorio.pacienteSel();
    }

    public Paciente pacienteGet(Integer id) {
        reiniciarFormulario();
        return repositorio.pacienteGet(id);
    }

    public String pacienteIns() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String url = ext.getRequestContextPath() + "/inisesion.xhtml";
        
        try{
            ext.redirect(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        
        return repositorio.pacienteIns(this.paciente);
        
    }

    public String pacienteUpd() {
        return repositorio.pacienteUpd(this.paciente);
    }

    public String pacienteDel(Integer id) {
        return repositorio.pacienteDel(id);
    }
    public Paciente pacienteLogin(){
        return repositorio.pacienteLogin(paciente.getDni(), paciente.getPassword());
    }
    
    public void reiniciarFormulario(){
        this.paciente.setNombre(null);
        this.paciente.setApellidopaterno(null);
        this.paciente.setApellidomaterno(null);
        this.paciente.setSexo(null);
        this.paciente.setEdad(null);
        this.paciente.setDni(null);
        this.paciente.setPassword(null);
    }

    public String editarPaciente(Integer idPaciente){
        this.id_paciente=idPaciente;
        
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String url = ext.getRequestContextPath() + "/paciente_perfil_upd.xhtml?id_paciente=" +this.id_paciente;
        
        try{
            ext.redirect(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public String iniciarSesion(){
        this.paciente= pacienteLogin();
        if(this.paciente !=null){
            this.id_paciente = this.paciente.getId_paciente();
            
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String url = ext.getRequestContextPath() + "/paciente_index.xhtml?id_paciente=" +this.id_paciente;
            
            try {
                ext.redirect(url);
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
            
        }else{
            return null;
        }
        
    }
    
   
}
