/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conectionbd.ConnectionPostgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.OperacaoLog;

/**
 *
 * @author Gilmar
 */
public class OperacaoLogDao {
    
    ConnectionPostgres cp = new ConnectionPostgres();
    Connection con;
    
    public void persiste(OperacaoLog operacaoLog) throws SQLException{
        con = cp.getconection();
        
        String sql = "INSERT INTO operacao_log (operacao, descricao, data_operacao, id_funcionario) VALUES (?, ?, ?, ?)";
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, operacaoLog.getOperacao());
        ps.setString(2, operacaoLog.getDescricao());
        ps.setTimestamp(3, operacaoLog.getData());
        ps.setInt(4, operacaoLog.getFuncionario().getId());
        
        
        ps.execute();
        ps.close();
        con.close();
        
    }
    
    public OperacaoLog getOperacaoLog(Integer idOperacao) throws SQLException{
        OperacaoLog operacaoLog = null;
        
        con = cp.getconection();
        
        String sql = "SELECT * FROM  operacao_log WHERE id = ?";
        
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, idOperacao);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.first()){
            operacaoLog = new OperacaoLog();
            operacaoLog.setOperacao(rs.getString("operacao"));
            operacaoLog.setDescricao(rs.getString("descricao"));
            operacaoLog.setData(rs.getTimestamp("data_operacao"));
            operacaoLog.setId(rs.getInt("id"));
            operacaoLog.setFuncionario(new FuncionarioDao().getfuncionario(rs.getInt("id_funcionario")));
        }
        
        return operacaoLog;
    }
    
    public List<OperacaoLog> getOpercacoesFuncioanrio(Integer idFuncionario) throws SQLException{
        List<OperacaoLog> operacaoLogs = new ArrayList<OperacaoLog>();
        
        con = cp.getconection();
        
        String sql = "SELECT * FROM operacao_log WHERE id_funcionario = ?";
        
        PreparedStatement ps = con.prepareCall(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        ps.setInt(1, idFuncionario);
        
        ResultSet rs = ps.executeQuery();
        
        OperacaoLog operacaoLog = null;
        while(rs.next()){
            operacaoLog = new OperacaoLog();
            operacaoLog.setData(rs.getTimestamp("data_operacao"));
            operacaoLog.setDescricao(rs.getString("descricao"));
            operacaoLog.setFuncionario(new FuncionarioDao().getfuncionario(rs.getInt("id_funcionario")));
            operacaoLog.setId(rs.getInt("id"));
            operacaoLog.setOperacao(rs.getString("operacao"));
            
            operacaoLogs.add(operacaoLog);
        }
        
        return operacaoLogs;
    }
    
}
