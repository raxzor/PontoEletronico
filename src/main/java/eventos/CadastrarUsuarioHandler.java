/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Funcionario;
import dao.FuncionarioDao;
import dao.OperacaoLogDao;
import gui.Administrador;
import gui.NovoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.OperacaoLog;
import negocio.UsuarioLogado;
import service.relatorios.UtilDatas;

/**
 *
 * @author Gilmar
 */
public class CadastrarUsuarioHandler implements ActionListener {

    NovoUsuario novoUsuario;

    public CadastrarUsuarioHandler(NovoUsuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public void actionPerformed(ActionEvent e) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(novoUsuario.getNome());
        funcionario.setDataAdmissao(UtilDatas.FormatoData(novoUsuario.getDataAdmissao()));
        funcionario.setLogin(novoUsuario.getlogin());
        funcionario.setPortaria(novoUsuario.getPortaria());
        funcionario.setSalario(novoUsuario.getSalario());
        funcionario.setNivelAcesso(novoUsuario.getNivelAcesso());
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        String senha = new String();
        for (char c : novoUsuario.getsenha()) {
            senha = senha + c;
        }
        funcionario.setSenha(senha);
        try {
            funcionarioDao.inserirFuncionario(funcionario);

            OperacaoLog log = new OperacaoLog();
            log.setData(new Timestamp(System.currentTimeMillis()));
            log.setDescricao("Cadastro do Funcionário: " + funcionario.getNome() + ", Portaria Nº: " + funcionario.getPortaria());
            log.setFuncionario(UsuarioLogado.getInstancia().getUsuarioLogado());
            log.setOperacao("Cadastro de Funcionário");

            OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
            try {
                operacaoLogDao.persiste(log);
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarUsuarioHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            String respostaInsercao = "Usuário inserido com sucesso";
            JOptionPane.showMessageDialog(null, respostaInsercao);
            novoUsuario.dispose();
            Administrador.main(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Atenção, já existe um usuário associado ao login " + funcionario.getLogin().toUpperCase() + " !");
        }



    }
}
