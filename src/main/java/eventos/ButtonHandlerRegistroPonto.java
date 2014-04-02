/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import dao.OperacaoLogDao;
import gui.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.FachadaFrequencia;
import negocio.OperacaoLog;
import service.relatorios.LoginInterface;

/**
 *
 * @author Gilmar
 */
public class ButtonHandlerRegistroPonto implements ActionListener {

    Principal principal;

    public void actionPerformed(ActionEvent e) {
        String login = principal.getLogin();
        String senha = principal.getSenha();
        LoginInterface loginInterface = new LoginInterface();
        Funcionario funcionario = null;
        Frequencia frequencia = new Frequencia();
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        FachadaFrequencia fachadaFrequencia = new FachadaFrequencia();
        String respostaUsuario = "";
        try {
            funcionario = loginInterface.logarUsuario(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHandlerRegistroPonto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (funcionario != null) {
                Boolean frequenciaDia = fachadaFrequencia.verificarFrequenciaDia(funcionario.getId());
                if (frequenciaDia == false) {
                    respostaUsuario = "Bom dia " + funcionario.getNome().toString() + ", seu Ponto foi registrado com sucesso!";
                    frequencia.setFuncionario(funcionario);
                    frequencia.setPresenca(Boolean.TRUE);
                    Calendar calendar = Calendar.getInstance();
                    frequencia.setData(new Date(calendar.getTimeInMillis()));
                    try {
                        frequenciaDao.inserirFrequencia(frequencia);
                        
                        OperacaoLog log = new OperacaoLog();
                        log.setData(new Timestamp(System.currentTimeMillis()));
                        log.setDescricao("Registro de Ponto: " + funcionario.getNome());
                        log.setFuncionario(funcionario);
                        log.setOperacao("Registro de Ponto");

                        OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
                        operacaoLogDao.persiste(log);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonHandlerLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    respostaUsuario = "Atenção " + funcionario.getNome() + ", seu ponto já foi registrado!";
                }
            } else {
                respostaUsuario = "Erro, Credenciais inválidas!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHandlerRegistroPonto.class.getName()).log(Level.SEVERE, null, ex);
        }

        e.getActionCommand();
        JOptionPane.showMessageDialog(null, respostaUsuario);
    }

    public ButtonHandlerRegistroPonto(Principal principal) {
        this.principal = principal;
    }
}
