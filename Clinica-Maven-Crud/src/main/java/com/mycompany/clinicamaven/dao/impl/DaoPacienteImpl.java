/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicamaven.dao.impl;

import com.mycompany.clinicamaven.dao.DaoPaciente;
import com.mycompany.clinicamaven.entidades.Paciente;
import com.mycompany.clinicamaven.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DaoPacienteImpl implements DaoPaciente{
      private Conexion bd;
    private String mensaje;

    public DaoPacienteImpl() {
        bd = new Conexion();
    }

    @Override
    public List<Paciente> pacienteSel() {
        List<Paciente> lista = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_paciente, ")
                .append("nombre,")
                .append("apellidopaterno, ")
                .append("apellidomaterno, ")
                .append("sexo, ")
                .append("edad, ")
                .append("dni, ")
                .append("password  ")
                .append("FROM paciente");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellidopaterno(rs.getString(3));
                paciente.setApellidomaterno(rs.getString(4));
                paciente.setSexo(rs.getString(5));
                paciente.setEdad(rs.getString(6));
                paciente.setDni(rs.getString(7));
                paciente.setPassword(rs.getString(8));
                lista.add(paciente);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public Paciente pacienteGet(Integer id) {
        Paciente paciente = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_paciente, ")
                .append("nombre,")
                .append("apellidopaterno, ")
                .append("apellidomaterno," )
                .append("sexo, ")
                .append("edad, ")
                .append("dni, ")
                .append("password  ")
                .append("FROM paciente ")
                .append("WHERE id_paciente = ?");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            paciente = new Paciente();
            if (rs.next()) {
                paciente.setId_paciente(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellidopaterno(rs.getString(3));
                paciente.setApellidomaterno(rs.getString(4));
                paciente.setSexo(rs.getString(5));
                paciente.setEdad(rs.getString(6));
                paciente.setDni(rs.getString(7));
                paciente.setPassword(rs.getString(8));
            } else {
                mensaje = "Sin datos";
            }

        } catch (Exception e) {
            mensaje = e.getMessage();
        }

        return paciente;
    }

    @Override
    public String pacienteIns(Paciente paciente) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO paciente (")
                .append("nombre, ")
                .append("apellidopaterno, ")
                .append("apellidomaterno, ")
                .append("sexo, ")
                .append("edad, ")
                .append("dni," )
                .append("password  ")
                .append(") VALUES (?,?,?,?,?,?,aes_encrypt(?, 'AES'))");

        
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidopaterno());
            ps.setString(3, paciente.getApellidomaterno());
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getEdad());
            ps.setString(6, paciente.getDni());
            ps.setString(7, paciente.getPassword());
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
    public String pacienteUpd(Paciente paciente) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE paciente SET ")
                .append("nombre = ?,")
                .append("apellidopaterno = ?,")
                .append("apellidomaterno = ?,")
                .append("sexo = ?,")
                .append("edad = ?,")
                .append("dni = ?,")
                .append("password = aes_encrypt(?, 'AES')")
                .append("WHERE id_paciente = ?");

        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidopaterno());
            ps.setString(3, paciente.getApellidomaterno());
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getEdad());
            ps.setString(6, paciente.getDni());
            ps.setString(7, paciente.getPassword());
            ps.setInt(8, paciente.getId_paciente());
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
    public String pacienteDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM paciente ")
                .append("WHERE id_paciente = ?");
        try (Connection c = bd.getConexion()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
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


    @Override
    public Paciente pacienteLogin(String dni, String password) {
       boolean validator = false;
       StringBuilder sql = new StringBuilder();
       Paciente paciente = null;
       sql.append("SELECT ")
               .append("id_paciente, ")
               .append("nombre, ")
               .append("apellidopaterno, ")
               .append("apellidomaterno, ")
               .append("dni ")
               .append("FROM paciente WHERE dni=? and password=aes_encrypt(?,'AES')");
       
       try (Connection c = bd.getConexion()){
           PreparedStatement ps = c.prepareStatement(sql.toString());
           ps.setString(1,dni);
           ps.setString(2, password);
           
           ResultSet rs = ps.executeQuery();
           
           if(rs.next()){
               paciente = new Paciente();
               paciente.setId_paciente(rs.getInt(1));
               paciente.setNombre(rs.getString(2));
               paciente.setApellidopaterno(rs.getString(3));
               paciente.setApellidomaterno(rs.getString(4));
               paciente.setDni(rs.getString(5));
           }
           return paciente;
           
       } catch(SQLException e ){
           mensaje = e.getMessage();
       }
       return null;
    }
   
}
