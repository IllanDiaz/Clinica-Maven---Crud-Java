package com.mycompany.clinicamaven.services;

import com.mycompany.clinicamaven.dao.DaoMedico;
import com.mycompany.clinicamaven.dao.impl.DaoMedicoImpl;
import com.mycompany.clinicamaven.entidades.Medico;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

public class MedicoService {

    private final DaoMedico repositorio = new DaoMedicoImpl();
    private static final MedicoService INSTANCE = new MedicoService();
    private Medico medico = new Medico();
    private Integer id_medico;

    public MedicoService() {

    }

    public static MedicoService getInstance() {
        return INSTANCE;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Integer getId_medico() {
        return id_medico;
    }

    public void setId_medico(Integer id_medico) {
        this.id_medico = id_medico;
    }

    public List<Medico> medicoSel() {
        return repositorio.medicoSel();
    }

    public Medico medicoGet(Integer id) {
        reiniciarFormulario();
        return repositorio.medicoGet(id);
    }

    public String medicoIns() {
        return repositorio.medicoIns(this.medico);
    }

    public String medicoUpd() {
        return repositorio.medicoUpd(this.medico);
    }

    public String medicoDel(Integer id) {
        return repositorio.medicoDel(id);
    }

    public boolean medicoLogin() {
        return repositorio.medicoLogin(medico.getNombremedico(), medico.getCodigo());
    }

    public void reiniciarFormulario() {
        this.medico.setNombremedico(null);
        this.medico.setEspecialidad(null);
        this.medico.setCodigo(null);
    }

        public String editarMedico(Integer id_medico){
        this.id_medico = id_medico;
        // Obtener el contexto externo
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        // Construir la URL de redirección con el ID del colaborador
        String url = externalContext.getRequestContextPath() + "/medico_upd.xhtml?id_medico="+id_medico;
        // Redirigir a la página de edición
        try {
            externalContext.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Retornar null para indicar que no hay navegación adicional
        return null;
    }

    public String iniciarSesion() {
        if(medicoLogin()){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String url = externalContext.getRequestContextPath() + "/medico_index.xhtml";
        try {
                externalContext.redirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Retornar null para indicar que no hay navegación adicional
            return null;
        }else{
            return null;
        }
            
    }
}
