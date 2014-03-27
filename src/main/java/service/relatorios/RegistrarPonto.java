/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.relatorios;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import dao.FuncionarioDao;
import gui.App;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilmar
 */
public class RegistrarPonto {
    
    public void RegistroPonto(Funcionario funcionario) throws SQLException{
        Boolean presenca = true;
        Calendar c = Calendar.getInstance();
        Date data = new Date(c.getTimeInMillis());
        Frequencia f = new Frequencia();
        f.setData(data);
        f.setFuncionario(funcionario);
        f.setPresenca(presenca);
        FrequenciaDao frequenciadao = new FrequenciaDao();
        frequenciadao.inserirFrequencia(f);
    }
    
    public void autenticacao(String login, String senha){
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        Funcionario f = new Funcionario();
        try {
            f = funcionarioDao.getfuncionario(login, senha);
            System.out.println(f);
            this.RegistroPonto(f);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
