/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Expediente;
import beans.Funcionario;
import dao.FuncionarioDao;
import gui.NovoUsuario;
import gui.ProcurarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Gilmar
 */
public class SelecionarUsuarioHandler implements ActionListener {

    ProcurarUsuario procurarUsuario;

    public SelecionarUsuarioHandler(ProcurarUsuario procurarUsuario) {

        this.procurarUsuario = procurarUsuario;
    }

    public void actionPerformed(ActionEvent e) {

        TableModel model = procurarUsuario.getTable().getModel();
        if(procurarUsuario.getTable().getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null, "Pesquise e selecione um USUÁRIO para editá-lo!");
        }else{
        String id = (String) model.getValueAt(procurarUsuario.getTable().getSelectedRow(), 4);

        Integer idUsuario = new Integer(id);
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        Funcionario f = null;
        try {
            f = funcionarioDao.getfuncionario(idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(SelecionarUsuarioHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] valores = new String[9];

        valores[0] = f.getNome();
        valores[1] = f.getLogin();
        valores[2] = f.getPortaria();
        valores[3] = f.getSenha();
        valores[4] = f.getId().toString();
        valores[5] = f.getDataAdmissao().toString();
        valores[6] = f.getSalario().toString();
        valores[7] = f.getNivelAcesso().toString();
        String expedientes = new String();
        for(Expediente expediente : f.getExpedientes()){
            expedientes = expedientes + "-" + expediente.getDiaSemana().toString();
        }
        valores[8] = expedientes;
        procurarUsuario.dispose();
        NovoUsuario.main(valores);

        }

        
    }
}
