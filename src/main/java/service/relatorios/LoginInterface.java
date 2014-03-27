/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.relatorios;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import dao.FuncionarioDao;
import eventos.ButtonHandlerLogin;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gilmar
 */
public class LoginInterface {
    
    public Funcionario logarUsuario(String login, String senha) throws SQLException{
        Funcionario funcionario = null;
        
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        
        Frequencia frequencia = new Frequencia();
                
        funcionario = funcionarioDao.getfuncionario(login, senha);
       
        return funcionario;
    }
}
