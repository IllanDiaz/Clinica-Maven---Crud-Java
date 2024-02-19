/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.services;

import com.mycompany.clinicamaven.dao.DaoHistorial;
import com.mycompany.clinicamaven.dao.impl.DaoHistorialImpl;
import com.mycompany.clinicamaven.entidades.Historial;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author U149
 */
public class HistorialService {

    private final DaoHistorial repositorio = new DaoHistorialImpl();
    private static final HistorialService INSTANCE = new HistorialService();
    private Historial historial = new Historial();
    private Integer id_historial;

    public HistorialService() {
    }

    public static HistorialService getInstance() {
        return INSTANCE;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public Integer getId_historial() {
        return id_historial;
    }

    public void setId_historial(Integer id_historial) {
        this.id_historial = id_historial;
    }

    //CRUD 
    public List<Historial> historialSel() {
        return repositorio.historialSel();
    }

    public Historial historialGet(Integer id) {
        reiniciarFormulario();
        return repositorio.historialGet(id);
    }
    public String historialIns(){
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String url = ext.getRequestContextPath() + "/medico_historial.xhtml";
        
        try{
            ext.redirect(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return repositorio.historialIns(this.historial);
    }
    public String historialUpd(){
        return repositorio.historialUpd(this.historial);
    }
    public String historialDel(Integer id){
        return repositorio.historialDel(id);
    }
    public void reiniciarFormulario(){
        this.historial.setSintomas(null);
        this.historial.setDiagnostico(null);
        this.historial.setPaciente_id_paciente(null);
        this.historial.setMedico_id_medico(null);
    }
    public String detallesHistorial (Integer id_historial){
        this.id_historial = id_historial;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String url = externalContext.getRequestContextPath() + "paciente_historial.xhtml?id_historial" + id_historial;
        try{
            externalContext.redirect(url);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String editarHistorial (Integer id_historial){
        this.id_historial = id_historial;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String url = externalContext.getApplicationContextPath() + "/medico_historial_upd.xhtml?id_historial=" + id_historial;
        try{
            externalContext.redirect(url);
        }catch (IOException e){
             e.printStackTrace();
        }
        return null;
    }
}
