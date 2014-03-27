/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import beans.Funcionario;
import dao.FuncionarioDao;
import java.sql.SQLException;
import service.relatorios.UsuarioLogado;

/**
 *
 * @author Gilmar
 */
public class Autenticacao {
 
    public void autenticacao(String login, String senha) throws SQLException{
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        Funcionario f = funcionarioDao.getfuncionario(login, senha);
        if(f == null){
            System.out.println("Credenciais inválidas");
        }else{
            UsuarioLogado usuarioLogado = UsuarioLogado.getUsuarioLogado();
            usuarioLogado.setFuncionario(f);
            System.out.println("Bem vindo" + UsuarioLogado.getUsuarioLogado().getFuncionario().getNome());
        }
                
    }
}
