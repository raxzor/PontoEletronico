/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Expediente;
import conectionbd.ConnectionPostgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilmar
 */
public class ExpedienteDao {
    ConnectionPostgres cp = new ConnectionPostgres();
    Connection con;
    
    public void inserir(Expediente expediente, Integer idFuncionario) throws SQLException{
        con = cp.getconection();
        String sql = "INSERT INTO expedientes (DiaSemana, idFuncionario) values (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, expediente.getDiaSemana());
        ps.setInt(2, idFuncionario);
        
        ps.execute();
        ps.close();
        con.close();
        
    }
    
    public List<Expediente> getExpedientes(Integer idFuncionario) throws SQLException{
        List<Expediente> expedientes = new ArrayList<Expediente>();
        
        con = cp.getconection();
        String sql = "SELECT * FROM expedientes WHERE idFuncionario = ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, idFuncionario);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Expediente expediente = new Expediente();
            expediente.setId(rs.getInt("id"));
            expediente.setDiaSemana(rs.getInt("DiaSemana"));
            
            expedientes.add(expediente);
        }
        
        return expedientes;        
    }
    
    public void deletarExpedientes(Integer idFuncionario) throws SQLException{
        con = cp.getconection();
        String sql = "DELETE FROM expedientes WHERE idFuncionario = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idFuncionario);
        
        ps.executeUpdate();
        ps.close();
        con.close();
        
    }
}
