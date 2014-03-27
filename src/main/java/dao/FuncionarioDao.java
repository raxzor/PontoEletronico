/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Funcionario;
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
public class FuncionarioDao {
    
    ConnectionPostgres cp = new ConnectionPostgres();
    Connection con;
//    Connection con = new ConnectionPostgres().getconection();
    
    public void inserirFuncionario(Funcionario f) throws SQLException{
            con = cp.getconection();
            String sql = "INSERT INTO funcionario(login, senha, nome, portaria, dataAdmissao, nivelAcesso, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, f.getLogin());
            ps.setString(2, f.getSenha());
            ps.setString(3, f.getNome());
            ps.setString(4, f.getPortaria());
            ps.setDate(5, f.getDataAdmissao());
            ps.setBoolean(6, f.getNivelAcesso());
            ps.setDouble(7, f.getSalario());

            ps.execute();
            ps.close();
            con.close();
    }
    
    public void inserirAdministrador(Funcionario f) throws SQLException{
        
        this.setAdministrador();
        
        con = cp.getconection();
        String sql = "INSERT INTO funcionario(login, senha, nome, portaria, dataAdmissao, nivelAcesso, salario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, f.getLogin());
        ps.setString(2, f.getSenha());
        ps.setString(3, f.getNome());
        ps.setString(4, f.getPortaria());
        ps.setDate(5, f.getDataAdmissao());
        ps.setBoolean(6, f.getNivelAcesso());
        ps.setDouble(7, f.getSalario());
        
        ps.execute();
        ps.close();
        con.close();
    }
    
    public void atualizarFuncionario(Funcionario f, Integer idFuncionario) throws SQLException{
        System.out.println(idFuncionario);
        con = cp.getconection();
        String sql="UPDATE funcionario SET login = ?, senha = ?, nome = ?, portaria = ?, dataAdmissao = ?, nivelAcesso = ?, salario = ? where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, f.getLogin());
        ps.setString(2, f.getSenha());
        ps.setString(3, f.getNome());
        ps.setString(4, f.getPortaria());
        ps.setDate(5, f.getDataAdmissao());
        ps.setBoolean(6, f.getNivelAcesso());
        ps.setInt(8, idFuncionario);
        ps.setDouble(7, f.getSalario());
        ps.executeUpdate();
        ps.close();
        con.close();
    }
    
    public List<Funcionario> Listarfuncionarios() throws SQLException{
        con = cp.getconection();
        String sql="SELECT * from funcionario";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        ResultSet rs = ps.executeQuery();
        Funcionario f = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        while (rs.next()){
            f = new Funcionario();
            f.setDataAdmissao(rs.getDate("dataAdmissao"));
            f.setId(rs.getInt("id"));
            f.setLogin(rs.getString("login"));
            f.setNome(rs.getString("nome"));
            f.setPortaria(rs.getString("portaria"));
            f.setSenha(rs.getString("senha"));
            funcionarios.add(f);
            f.setNivelAcesso(rs.getBoolean("nivelAcesso"));
            f.setSalario(rs.getDouble("salario"));
        }
        return funcionarios;
    }
    
    public Funcionario getfuncionario(String login, String senha) throws SQLException{
        con = cp.getconection();
        String sql="SELECT * from funcionario where login ilike ? and senha ilike ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        
        ps.setString(1, login);
        ps.setString(2, senha);
       
        ResultSet rs = ps.executeQuery();
        Funcionario funcionario = null;
        if (rs.first()){
            funcionario = new Funcionario();
            funcionario.setDataAdmissao(rs.getDate("dataAdmissao"));
            funcionario.setId(rs.getInt("id"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setPortaria(rs.getString("portaria"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setNivelAcesso(rs.getBoolean("nivelAcesso"));
            funcionario.setSalario(rs.getDouble("salario"));
        }
        return funcionario;
    }
    
    public Boolean countLogin(String login) throws SQLException{
        con = cp.getconection();
        String sql="SELECT COUNT(login) from funcionario where login ilike ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        
        ps.setString(1, login);
       
        ResultSet rs = ps.executeQuery();
        Integer count = 0;
        count = rs.getInt("id");
        
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Funcionario getfuncionario(Integer id) throws SQLException{
        con = cp.getconection();
        String sql="SELECT * from funcionario where id = ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        
        ps.setInt(1, id);
       
        ResultSet rs = ps.executeQuery();
        Funcionario funcionario = null;
        if (rs.first()){
            funcionario = new Funcionario();
            funcionario.setDataAdmissao(rs.getDate("dataAdmissao"));
            funcionario.setId(rs.getInt("id"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setPortaria(rs.getString("portaria"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setNivelAcesso(rs.getBoolean("nivelAcesso"));
            funcionario.setSalario(rs.getDouble("salario"));
        }
        return funcionario;
    }
    
    public void setAdministrador() throws SQLException{
        con = cp.getconection();
        String sql = "UPDATE funcionario SET nivelAcesso = ? where nivelAcesso = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setBoolean(1, false);
        ps.setBoolean(2, true);
        ps.executeUpdate();
        ps.close();
        con.close();
        
    }
    public List<Funcionario> getfuncionario(String nome) throws SQLException{
        con = cp.getconection();
        String sql="SELECT * from funcionario where nome ilike ?";
        PreparedStatement ps = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ps.setString(1, "%" + nome + "%");
       
        ResultSet rs = ps.executeQuery();
        Funcionario funcionario = null;
        
        while (rs.next()){
            funcionario = new Funcionario();
            funcionario.setDataAdmissao(rs.getDate("dataAdmissao"));
            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setPortaria(rs.getString("portaria"));
            funcionario.setSalario(rs.getDouble("salario"));
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }
    
    public void deletaFuncionario(Integer idFuncionario) throws SQLException{
        con = cp.getconection();
        String sql = "DELETE FROM funcionario WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idFuncionario);
        
        ps.executeUpdate();
        ps.close();
        con.close();
        
    }
}
