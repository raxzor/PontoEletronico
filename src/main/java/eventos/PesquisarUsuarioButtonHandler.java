/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Funcionario;
import dao.FuncionarioDao;
import gui.ProcurarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import service.relatorios.UtilDatas;

/**
 *
 * @author Gilmar
 */
public class PesquisarUsuarioButtonHandler implements ActionListener{
    
    ProcurarUsuario procurarUsuario;

    public PesquisarUsuarioButtonHandler(ProcurarUsuario procurarUsuario) {
        this.procurarUsuario = procurarUsuario;
    }

    public void actionPerformed(ActionEvent e) {
//        Funcionario[] funcionarios = new Funcionario[2];
//        funcionarios[0] = new Funcionario();
//        funcionarios[1] = new Funcionario();
        String nome = procurarUsuario.TextFiel1();
        DefaultTableModel modelo = new DefaultTableModel();  
        JTable jTable = procurarUsuario.getTable();
        jTable.setModel(modelo);
        modelo.addColumn("Nome");
        modelo.addColumn("Data de Admissão");
        modelo.addColumn("Portaria");
        modelo.addColumn("Salário");
        modelo.addColumn("Id");
        jTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(49);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(49);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(1);
        
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            funcionarios = funcionarioDao.getfuncionario(nome);
         } catch (SQLException ex) {
            Logger.getLogger(PesquisarUsuarioButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(funcionarios.size() < 1){
            JOptionPane.showMessageDialog(null, "Usuário não existe!");
        
        }
        for(Funcionario funcionario:funcionarios){
            modelo.addRow(new Object[]{funcionario.getNome(), UtilDatas.DateToString(funcionario.getDataAdmissao()), 
                funcionario.getPortaria(), funcionario.getSalario(), funcionario.getId().toString()});
        }
        
            
        procurarUsuario.setTable(jTable);
        
        
    }
    
}
