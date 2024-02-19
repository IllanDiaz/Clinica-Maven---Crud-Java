/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.dao.impl;

import com.mycompany.clinicamaven.dao.DaoHistorial;
import com.mycompany.clinicamaven.entidades.Historial;
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
public class DaoHistorialImpl implements DaoHistorial {
    private Conexion bd;
    private String mensaje;

    public DaoHistorialImpl() {
        bd = new Conexion();
    }

    @Override
    public List<Historial> historialSel() {
        List<Historial> lista = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_historial,")
                .append("sintomas,")
                .append("diagnostico,")
                .append("tratamiento,")
                .append("paciente_id_paciente,")
                .append("medico_id_medico ")
                .append("FROM historial");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Historial historial = new Historial();
                historial.setId_historial(rs.getInt(1));
                historial.setSintomas(rs.getString(2));
                historial.setDiagnostico(rs.getString(3));
                historial.setTratamiento(rs.getString(4));
                historial.setPaciente_id_paciente(rs.getInt(5));
                historial.setMedico_id_medico(rs.getInt(6));
                lista.add(historial);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    @Override
    public Historial historialGet(Integer id) {
        Historial historial = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_historial,")
                .append("sintomas,")
                .append("diagnostico,")
                .append("tratamiento,")
                .append("paciente_id_paciente,")
                .append("medico_id_medico ")
                .append("FROM historial ")
                .append("WHERE id_historial = ?");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            historial = new Historial();
            if (rs.next()) {
                historial.setId_historial(rs.getInt(1));
                historial.setSintomas(rs.getString(2));
                historial.setDiagnostico(rs.getString(3));
                historial.setTratamiento(rs.getString(4));
                historial.setPaciente_id_paciente(rs.getInt(5));
                historial.setMedico_id_medico(rs.getInt(6));
            } else {
                mensaje = "Sin datos";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return historial;
    }

    @Override
    public String historialIns(Historial historial) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO historial (")
                .append("sintomas,")
                .append("diagnostico,")
                .append("tratamiento,")
                .append("paciente_id_paciente,")
                .append("medico_id_medico ")
                .append(") VALUES (?,?,?,?,?)");
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, historial.getSintomas());
            ps.setString(2, historial.getDiagnostico());
            ps.setString(3, historial.getTratamiento());
            ps.setInt(4, historial.getPaciente_id_paciente());
            ps.setInt(5, historial.getMedico_id_medico());
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String historialUpd(Historial historial) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE historial SET ")
                .append("sintomas = ?,")
                .append("diagnostico = ?,")
                .append("tratamiento = ?,")
                .append("paciente_id_paciente = ?,")
                .append("medico_id_medico = ? ")
                .append("WHERE id_historial = ?");
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, historial.getSintomas());
            ps.setString(2, historial.getDiagnostico());
            ps.setString(3, historial.getTratamiento());
            ps.setInt(4, historial.getPaciente_id_paciente());
            ps.setInt(5, historial.getMedico_id_medico());
            ps.setInt(6, historial.getId_historial());
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
    public String historialDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM historial ")
                .append("WHERE id_historial = ?");
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
