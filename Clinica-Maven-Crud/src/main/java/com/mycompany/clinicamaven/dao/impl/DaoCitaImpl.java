package com.mycompany.clinicamaven.dao.impl;
import com.mycompany.clinicamaven.dao.DaoCita;
import com.mycompany.clinicamaven.entidades.Cita;
import com.mycompany.clinicamaven.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DaoCitaImpl implements DaoCita {
    private Conexion bd;
    private String mensaje;
    
    public DaoCitaImpl(){
    bd = new Conexion();
}

    @Override
    public List<Cita> citaSel() {
        List<Cita> lista = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_cita,")
                .append("paciente_id_paciente,")
                .append("fecha ")
                .append("FROM cita");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId_cita(rs.getInt(1));
                cita.setFecha(rs.getString(2));
                cita.setPaciente_id_paciente(rs.getInt(3));
                lista.add(cita);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    @Override
    public Cita citaGet(Integer id) {
        Cita cita = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_cita,")
                .append("fecha,")
                .append("paciente_id_paciente,")
                .append("FROM cita ")
                .append("WHERE id_cita = ?");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            cita = new Cita();
            if (rs.next()) {
                cita.setId_cita(rs.getInt(1));
                cita.setFecha(rs.getString(2));
                cita.setPaciente_id_paciente(rs.getInt(3));
            } else {
                mensaje = "Sin datos";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return cita;
    }

    @Override
    public String citaIns(Cita cita) {
StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO cita (")
                .append("fecha,")
                .append("paciente_id_paciente,")
                .append(") VALUES (?,?,?,?,?)");
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, cita.getFecha());
            ps.setInt(2, cita.getPaciente_id_paciente());
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;    }

    @Override
    public String citaUpd(Cita cita) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE cita SET ")
                .append("fecha = ?,")
                .append("paciente_id_paciente = ?,")
                .append("WHERE id_cita = ?");
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, cita.getFecha());
            ps.setInt(2, cita.getPaciente_id_paciente());
            ps.setInt(3, cita.getId_cita());
            int resultado = ps.executeUpdate();

            if (resultado == 0) {
                mensaje = "Cero registros actualizados";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String citaDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM cita ")
                .append("WHERE id_cita = ?");
        try (Connection c = bd.getConexion()){
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            if (resultado == 0){
                mensaje = "Cero registros eliminados";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje; 
    }

    @Override
    public String getMensaje() {
       return mensaje;
    }
    
}
