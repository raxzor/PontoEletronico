/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Funcionario;
import dao.FuncionarioDao;
import dao.OperacaoLogDao;
import gui.ProcurarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import negocio.OperacaoLog;
import negocio.UsuarioLogado;

/**
 *
 * @author Gilmar
 */
public class ApagarUsuarioHandler implements ActionListener{

    ProcurarUsuario procurarUsuario;
    
    public ApagarUsuarioHandler(ProcurarUsuario procurarUsuario) {
        
        this.procurarUsuario = procurarUsuario;
    }
    public void actionPerformed(ActionEvent e) {
        TableModel model = procurarUsuario.getTable().getModel();
        String id = (String) model.getValueAt(procurarUsuario.getTable().getSelectedRow(), 4);
       
        String nome = (String) model.getValueAt(procurarUsuario.getTable().getSelectedRow(), 0);
        String dataAdmissao = (String) model.getValueAt(procurarUsuario.getTable().getSelectedRow(), 1);
        String portaria = (String) model.getValueAt(procurarUsuario.getTable().getSelectedRow(), 2);
        
        String mensagem = "Deseja realmente Apagar este Usuário? \n \n " +
                "\t \t \t \t" + " NOME: " + nome +
                "; \n \t \t \t \t PORTARIA Nº: " + portaria + 
                "; \n  \t \t \t \t DATA DE ADMISSÃO: " + dataAdmissao;
        String titulo = "Apagar o Usuario " + nome.toUpperCase() + " ?";
        int confirmacao = JOptionPane.showConfirmDialog(null, mensagem, titulo, 0);
       
        if(confirmacao == 0){
            Integer idUsuario = new Integer(id);
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            try {
                
                Funcionario funcionario = funcionarioDao.getfuncionario(idUsuario);
                funcionarioDao.deletaFuncionario(idUsuario);
                OperacaoLog log = new OperacaoLog();
                log.setData(new Timestamp(System.currentTimeMillis()));
                log.setDescricao("Exclusão de Funcionário: " + funcionario.getNome() + ", Portaria Nº: " + funcionario.getPortaria());
                log.setFuncionario(UsuarioLogado.getInstancia().getUsuarioLogado());
                log.setOperacao("Exclusão de Funcionário");

                OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
                
                operacaoLogDao.persiste(log);
            } catch (SQLException ex) {
                Logger.getLogger(ApagarUsuarioHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Usuario " + nome.toUpperCase() + "apagado com sucesso!");

            PesquisarUsuarioButtonHandler pesquisarUsuarioButtonHandler = new PesquisarUsuarioButtonHandler(procurarUsuario);
            pesquisarUsuarioButtonHandler.actionPerformed(e);
            //            procurarUsuario.updateUi();
            
            
        }
//        procurarUsuario.main(null);
        
//        Integer idUsuario = new Integer(id);
//        FuncionarioDao funcionarioDao = new FuncionarioDao();
//        Funcionario f = null;
//        try {
//            f = funcionarioDao.getfuncionario(idUsuario);
//        } catch (SQLException ex) {
//            Logger.getLogger(SelecionarUsuarioHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
