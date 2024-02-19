/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.dao.impl;

import com.mycompany.clinicamaven.dao.DaoMedico;
import com.mycompany.clinicamaven.entidades.Medico;
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
public class DaoMedicoImpl implements DaoMedico{
    private Conexion bd;
    private String mensaje;
    
    public DaoMedicoImpl(){
        bd = new Conexion();
    }

    @Override
    public List<Medico> medicoSel() {
    
        List<Medico> lista = null;
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_medico,")
                .append("nombremedico,")
                .append("especialidad,")
                .append("codigo")
                .append(" FROM medico");
        
        try(Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while(rs.next()){
                Medico medico = new Medico();
                medico.setId_medico(rs.getInt(1));
                medico.setNombremedico(rs.getString(2));  
                medico.setEspecialidad(rs.getString(3));
                medico.setCodigo(rs.getString(4));
                lista.add(medico);
            }
            
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        
        return lista;  
    }

    @Override
    public Medico medicoGet(Integer id_medico) {
    Medico medico = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("nombremedico,")
                .append("especialidad,")
                .append("codigo,")
                .append("id_medico")
                .append(" FROM medico")
                .append(" WHERE id_medico = ?");
        
        try(Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1,id_medico);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                medico = new Medico();
                medico.setNombremedico(rs.getString(1));  
                medico.setEspecialidad(rs.getString(2));
                medico.setCodigo(rs.getString(3));
                medico.setId_medico(rs.getInt(4));
            }else {
                mensaje = "Sin datos";
            }
            
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        
        return medico;
    }

    @Override
    public String medicoIns(Medico medico) {
     StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO medico(")
                .append("nombremedico,")
                .append("especialidad,")
                .append("codigo ")
                .append(" )VALUES (?,?,?)");
        
         try ( Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            
            ps.setString(1, medico.getNombremedico());
            ps.setString(2, medico.getEspecialidad());
            ps.setString(3, medico.getCodigo());
            
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros agregados";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String medicoUpd(Medico medico) {
    StringBuilder sql = new StringBuilder();
        sql.append("UPDATE medico SET ")
                .append("nombremedico = ?,")
                .append("especialidad = ?,")
                .append("codigo = ?")
                .append(" WHERE id_medico = ?");
    
        try ( Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, medico.getNombremedico());
            ps.setString(2, medico.getEspecialidad());
            ps.setString(3, medico.getCodigo());
            ps.setInt(4, medico.getId_medico());
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
    public String medicoDel(Integer id_medico) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM medico")
                .append(" WHERE id_medico = ?");
        
        try ( Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id_medico);
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
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
    
@Override
    public Medico medicoGetByDni(String codigo) {
        try (Connection c = bd.getConexion()) {
        String sql = "SELECT * FROM medico WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, codigo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Medico medico = new Medico();
            medico.setId_medico(rs.getInt("id_medico"));
            medico.setNombremedico(rs.getString("nombremedico"));
            medico.setEspecialidad(rs.getString("especialidad"));
            medico.setCodigo(rs.getString("codigo"));
            return medico;
        }
    } catch (Exception e) {
        mensaje = e.getMessage();
    }
    return null;
    }

    @Override
    public boolean medicoLogin(String nombremedico, String codigo) {
       boolean validator = false;
       StringBuilder sql = new StringBuilder();
       Medico medico = null;
       sql.append("SELECT ")
               .append("id_medico, ")
               .append("nombremedico, ")
               .append("especialidad, ")
               .append("codigo ")
               .append("FROM medico WHERE nombremedico=? and codigo=?");
       
       try (Connection c = bd.getConexion()){
           PreparedStatement ps = c.prepareStatement(sql.toString());
           ps.setString(1,nombremedico);
           ps.setString(2, codigo);
           
           ResultSet rs = ps.executeQuery();
           
           if(rs.next()){
               medico = new Medico();
               medico.setId_medico(rs.getInt(1));
               medico.setNombremedico(rs.getString(2));
               medico.setEspecialidad(rs.getString(3));
               medico.setCodigo(rs.getString(4));
           }
           return true;
           
       } catch(SQLException e ){
           mensaje = e.getMessage();
       }
       return false;
    }
    }
    
   

