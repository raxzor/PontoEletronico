/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Funcionario;
import dao.OperacaoLogDao;
import gui.Administrador;
import gui.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.OperacaoLog;
import negocio.UsuarioLogado;
import service.relatorios.LoginInterface;

/**
 *
 * @author Gilmar
 */
public class ButtonHandlerLogin implements ActionListener {

    Principal principal;

    public ButtonHandlerLogin(Principal principal) {
        this.principal = principal;
    }

    public void actionPerformed(ActionEvent e) {

        String login = principal.getLogin();
        String senha = principal.getSenha();
        LoginInterface loginInterface = new LoginInterface();
        Funcionario funcionario = null;
       
        try {
            funcionario = loginInterface.logarUsuario(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHandlerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        String respostaUsuario = "";

        if (funcionario != null) {
            if (funcionario.getNivelAcesso()) {
//                respostaUsuario = funcionario.getNome() + funcionario.getSalario().toString();
//                frequencia.setFuncionario(funcionario);
//                frequencia.setPresenca(Boolean.TRUE);
//                Calendar calendar = Calendar.getInstance();
//                frequencia.setData(new Date(calendar.getTimeInMillis()));
                try {
                    UsuarioLogado usuarioLogado = UsuarioLogado.getInstancia();
                    usuarioLogado.setUsuarioLogado(funcionario);
                    
                    OperacaoLog log = new OperacaoLog();
                    log.setData(new Timestamp(System.currentTimeMillis()));
                    log.setDescricao("Logon de Administrador: " + funcionario.getNome());
                    log.setFuncionario(UsuarioLogado.getInstancia().getUsuarioLogado());
                    log.setOperacao("Logon de Administrador");

                    OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
                    
                    operacaoLogDao.persiste(log);
//                    frequenciaDao.inserirFrequencia(frequencia);
                    

                } catch (SQLException ex) {
                    Logger.getLogger(ButtonHandlerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                principal.dispose();
                Administrador.main(null);
            } else {
                respostaUsuario = "Erro, acesso não permitido!";
                JOptionPane.showMessageDialog(null, respostaUsuario);

            }
        } else {
            respostaUsuario = "Erro, Credenciais inválidas!";
            JOptionPane.showMessageDialog(null, respostaUsuario);

        }

        e.getActionCommand();



    }
}
