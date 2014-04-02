/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Frequencia;
import beans.Funcionario;
import conectionbd.ConnectionPostgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import service.UsuarioLogado;
import service.UtilFrequencia;

/**
 *
 * @author Gilmar
 */
public class FrequenciaDao {
    
    ConnectionPostgres cp = new ConnectionPostgres();
    Connection con;
    
    public void inserirFrequencia(Frequencia frequencia) throws SQLException{
        
        con = cp.getconection();
        String sql = "INSERT INTO frequencia(idFuncionario, data, turno, justificativa, presenca) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, frequencia.getFuncionario().getId());
        ps.setDate(2, frequencia.getData());
        ps.setString(3, frequencia.getTurno());
        ps.setString(4, frequencia.getJustificativa());
        ps.setBoolean(5, frequencia.getPresenca());
        
        ps.execute();
        ps.close();
        con.close();
//        UsuarioLogado u = UsuarioLogado.getUsuarioLogado();
//        System.out.println(u);
//        System.out.println(u.getFuncionario());
        
        
    }
    
    public void atualizarFrequencia(Frequencia frequencia) throws SQLException{
        con = cp.getconection();
        String sql = "UPDATE frequencia (idFuncionario, data, turno, justificativa, presenca) SET (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setDate(2, frequencia.getData());
        ps.setString(3, frequencia.getTurno());
        ps.setString(4, frequencia.getJustificativa());
        ps.setBoolean(5, frequencia.getPresenca());
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    public List<Frequencia> ListarFrequencia() throws SQLException{
        con = cp.getconection();
        String sql="SELECT * from Frequencia";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        ResultSet rs = ps.executeQuery();
        Frequencia f = null;
        List<Frequencia> frequencia = new ArrayList<Frequencia>();
        while (rs.next()){
            f = new Frequencia();
           
            f.setData(rs.getDate("data"));
            f.setTurno(rs.getString("turno"));
            
            frequencia.add(f);
        }
        return frequencia;
    }
    
        public List<Frequencia> ListarFrequenciaPorFuncionario(Integer idFuncionario) throws SQLException{
        con = cp.getconection();
        String sql="SELECT * FROM funcionario f, frequencia fr WHERE f.id = fr.idFuncionario AND fr.idFuncionario = ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        ps.setInt(1, idFuncionario);
        ResultSet rs = ps.executeQuery();
        Frequencia f = null;
        List<Frequencia> frequencia = new ArrayList<Frequencia>();
        Funcionario funcionario;
        while (rs.next()){
            f = new Frequencia();
            funcionario = new Funcionario();
            funcionario.setDataAdmissao(rs.getDate("dataadmissao"));
            funcionario.setId(rs.getInt("id"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setPortaria(rs.getString("portaria"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setNivelAcesso(rs.getBoolean("nivelAcesso"));
            funcionario.setSalario(rs.getDouble("salario"));
            f.setFuncionario(funcionario);
            f.setData(rs.getDate("data"));
            f.setTurno(rs.getString("turno"));
            f.setJustificativa(rs.getString("justificativa"));
            
            frequencia.add(f);
        }
        return frequencia;
    }
    
    public void deletarFrequencia(Integer idFrequencia) throws SQLException{
        con = cp.getconection();
        String sql = "DELETE FROM frequencia WHERE idFrequencia = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idFrequencia);
        
        ps.executeUpdate();
        ps.close();
        con.close();
        
    }
    
    public void setPresenca(Integer idFrequencia) throws SQLException{
        con = cp.getconection();
        String sql = "UPDATE frequencia set presenca = ?  WHERE idFrequencia = ?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setBoolean(1, true);
         ps.setInt(2, idFrequencia);
         ps.executeUpdate();
         ps.close();
         con.close();
    }
    
    public List<Frequencia> getFrequenciaFuncionario(Integer mes, Integer ano, Integer idFuncionario) throws SQLException{
        List<Frequencia> frequencia = new ArrayList<Frequencia>();
        con = cp.getconection();
        String sql = "SELECT z.dia, z.data, z.idFuncionario, f.nome, f.portaria, z.presenca FROM funcionario f, (frequencia fr join (Select data as dt, Extract('Day' From data) as dia From frequencia where idFuncionario = 13) x ON x.dt = fr.data) z WHERE f.id = z.idFuncionario AND z.idFuncionario = ? AND z.data between ? AND ? order by z.data asc";
//        SELECT * FROM frequencia WHERE idfuncionario = ? and data Between ? and ?
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, idFuncionario);
        Calendar c = Calendar.getInstance();
        c.set(ano, (mes - 1), 01);
        Date dataInicial = new Date(c.getTimeInMillis());
        ps.setDate(2, dataInicial);
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        Integer ultimoDia = utilFrequencia.getMaximoDias(mes, ano);
        c.set(ano, mes, ultimoDia);
        Date dataFinal = new Date(c.getTimeInMillis());
        ps.setDate(3, dataFinal);
        Frequencia f;
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            f = new Frequencia();
            f.setData(rs.getDate("data"));
            f.setPresenca(rs.getBoolean("presenca"));
//            f.setJustificativa(rs.getString("justificativa"));
            f.setFuncionario(new FuncionarioDao().getfuncionario(rs.getInt("idFuncionario")));
            f.setAux( (int) rs.getDouble("dia"));
            
            frequencia.add(f);
        }
        return frequencia;
    }
       
     public Date ultimaFrequenciaRegistrada(Integer idFuncionario) throws SQLException{
         con = cp.getconection();
         String sql = "SELECT max(data) FROM frequencia WHERE idFuncionario = ?";
         PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
         ps.setInt(1, idFuncionario);
         ResultSet rs = ps.executeQuery();
         Date ultimaFrequencia = null;
         if(rs.first()){
            ultimaFrequencia = rs.getDate("max"); 
         }
         return ultimaFrequencia;
     } 
     
     
     public Integer getTotalFaltas(Integer mes, Integer ano, Integer idFuncionario, Boolean tipo) throws SQLException{
        Integer totalFaltas = 0;
         con = cp.getconection();
        String sql = "SELECT COUNT (data) FROM frequencia WHERE idfuncionario = ? AND data BETWEEN ? AND ? AND presenca = ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setInt(1, idFuncionario);
        Calendar c = Calendar.getInstance();
        c.set(ano, (mes - 1), 01);
        Date dataInicial = new Date(c.getTimeInMillis());
        ps.setDate(2, dataInicial);
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        Integer ultimoDia = utilFrequencia.getMaximoDias(mes, ano);
        c.set(ano, mes, ultimoDia);
        Date dataFinal = new Date(c.getTimeInMillis());
        ps.setDate(3, dataFinal);
        ps.setBoolean(4, tipo);
        ResultSet rs = ps.executeQuery();
        if (rs.first()){
            totalFaltas = rs.getInt("count");
        }
        return totalFaltas;
    }
    
}
