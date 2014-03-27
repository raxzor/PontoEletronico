/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Funcionario;
import beans.Log;
import conectionbd.ConnectionPostgres;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilmar
 */
public class LogDao {
    
    ConnectionPostgres cp = new ConnectionPostgres();
    Connection con;
    
    public void inserirLog(Log log) throws SQLException{
        con = cp.getconection();
        String sql = "INSERT INTO log(idFuncionario, operacao, data) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, log.getFuncionario().getId());
        ps.setInt(1, 1);
        ps.setString(2, log.getOperacao());
        ps.setDate(3, log.getData());
        
        ps.execute();
        ps.close();
        con.close();
    }
    
    public void AtualizarLog(Log log) throws SQLException{
        con = cp.getconection();
        String sql = "UPDATE log(idFuncionario, operacao, data) SET (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
//      ps.setInt(1, log.getFuncionario().getId());
        ps.setInt(1, 1);
        ps.setString(2, log.getOperacao());
        ps.setDate(3, log.getData());
        
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
     public List<Log> ListarLog(Integer idFuncionario) throws SQLException{
        con = cp.getconection();
        String sql="SELECT * FROM funcionario f, log l WHERE f.id = l.idFuncionario AND l.idFuncionario = ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        ps.setInt(1, idFuncionario);
        ResultSet rs = ps.executeQuery();
        Log l = null;
        Funcionario funcionario;
        List<Log> log = new ArrayList<Log>();
        while (rs.next()){
            l = new Log();
            funcionario = new Funcionario();
            funcionario.setDataAdmissao(rs.getDate("dataadmissao"));
            funcionario.setId(rs.getInt("id"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setPortaria(rs.getString("portaria"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setNivelAcesso(rs.getBoolean("nivelAcesso"));
            funcionario.setSalario(rs.getDouble("salario"));
            l.setFuncionario(funcionario);
            l.setData(rs.getDate("data"));
            l.setOperacao(rs.getString("operacao"));
            log.add(l);
        }
        return log;
    }
     
      public void deletarLog(Date data) throws SQLException{
        con = cp.getconection();
        String sql = "DELETE FROM log WHERE data = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, data);
        
        ps.executeUpdate();
        ps.close();
        con.close();
        
    }
}
