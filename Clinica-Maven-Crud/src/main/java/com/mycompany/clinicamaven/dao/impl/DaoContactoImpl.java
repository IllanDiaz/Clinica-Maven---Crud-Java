package com.mycompany.clinicamaven.dao.impl;

import com.mycompany.clinicamaven.dao.DaoContacto;
import com.mycompany.clinicamaven.entidades.Contacto;
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
public class DaoContactoImpl implements DaoContacto {
    private Conexion bd;
    private String mensaje;
    
    public DaoContactoImpl(){
        bd = new Conexion();
    }

    @Override
    public List<Contacto> contactoSel() {
        List<Contacto> lista = null;
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_contacto,")
                .append("nombre,")
                .append("correo,")
                .append("mensaje")
                .append(" FROM contacto");
        
        try(Connection c = bd.getConexion()){
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while(rs.next()){
                Contacto contacto = new Contacto();
                contacto.setId_contacto(rs.getInt(1));
                contacto.setNombre(rs.getString(2));
                contacto.setCorreo(rs.getString(3));
                contacto.setMensaje(rs.getString(4));
                lista.add(contacto);
            }
        } catch(Exception e ){
        mensaje = e.getMessage();
    }
        return lista;
    }

    @Override
    public Contacto contactoGet(Integer id) {
        Contacto contacto = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_contacto,")
                .append("nombre,")
                .append("correo")
                .append("mensaje")
                .append(" FROM contacto")
                .append("WHERE id_contacto = ? ");
        
        try(Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                contacto = new Contacto();
                contacto.setNombre(rs.getString(1));
                contacto.setCorreo(rs.getString(2));
                contacto.setMensaje(rs.getString(3));
                contacto.setId_contacto(rs.getInt(4));
            }else{
                mensaje = "Sin datos";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return contacto;
    }

    @Override
    public String contactoIns(Contacto contacto) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO contacto(")
                .append("nombre,")
                .append("correo,")
                .append("mensaje ")
                .append(" )VALUES (?,?,?)");
        
         try ( Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getCorreo());
            ps.setString(3, contacto.getCorreo());
            
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
    public String contactoUpd(Contacto contacto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE contacto SET ")
                .append("nombre = ?,")
                .append("correo = ?,")
                .append("mensaje = ?")
                .append(" WHERE id_contacto = ?");
    
        try ( Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getCorreo());
            ps.setString(3, contacto.getMensaje());
            ps.setInt(4, contacto.getId_contacto());
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
    public String contactoDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM contacto")
                .append(" WHERE id_contacto = ?");
        
        try (Connection cn = bd.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
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
}

    
