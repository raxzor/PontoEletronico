/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Expediente;
import beans.Funcionario;
import dao.FuncionarioDao;
import dao.OperacaoLogDao;
import gui.Administrador;
import gui.NovoUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import negocio.OperacaoLog;
import service.UtilDatas;

/**
 *
 * @author Gilmar
 */
public class AlterarUsuarioHandler implements ActionListener {

    NovoUsuario novoUsuario;
    String id;

    public AlterarUsuarioHandler(NovoUsuario novoUsuario, String id) {
        this.novoUsuario = novoUsuario;
        this.id = id;
    }

    public void actionPerformed(ActionEvent e) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(novoUsuario.getNome());
        funcionario.setDataAdmissao(UtilDatas.FormatoData(novoUsuario.getDataAdmissao()));
        funcionario.setLogin(novoUsuario.getlogin());
        funcionario.setPortaria(novoUsuario.getPortaria());
        funcionario.setSalario(novoUsuario.getSalario());
        funcionario.setNivelAcesso(novoUsuario.getNivelAcesso());
        List <Expediente> expedientes = new ArrayList<Expediente>();
            JCheckBox[] jcheckboxes = new JCheckBox[5];
            jcheckboxes = novoUsuario.getJcheckboxes();
            for (int i = 0; i < jcheckboxes.length; i ++) {
                Expediente expediente = new Expediente();
                if(jcheckboxes[i].isSelected()){
                expediente.setDiaSemana(i);
                expedientes.add(expediente);
                }
            }
        
        funcionario.setExpedientes(expedientes);
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        if (novoUsuario.getsenha().length == 0) {
            try {
                funcionario.setSenha(new FuncionarioDao().getfuncionario(new Integer(id)).getSenha());
            } catch (SQLException ex) {
                Logger.getLogger(AlterarUsuarioHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String senha = new String();
            for (char c : novoUsuario.getsenha()) {
                senha = senha + c;
            }
            funcionario.setSenha(senha);
        }
        
//        System.out.println("Funcionario = " + funcionario.getLogin() + " " + funcionario.getNome() + " " + funcionario.getPortaria() + " " + funcionario.getSenha() + " " + funcionario.getDataAdmissao() + " " + funcionario.getId() + " " + funcionario.getNivelAcesso() + " " + funcionario.getSalario());
        
        try {
            
            Funcionario antigo = funcionarioDao.getfuncionario(new Integer(id));
            funcionarioDao.atualizarFuncionario(funcionario, new Integer(id));
            
            OperacaoLog log = new OperacaoLog();
            log.setData(new Timestamp(System.currentTimeMillis()));
            log.setDescricao("Alteração de Cadastro do Funcionário: " + antigo.getNome() + ", Portaria Nº: " + antigo.getPortaria() + ", Data de Admissão: " + antigo.getDataAdmissao().toString() + " PARA nome: " + funcionario.getNome() + ", Portaria nº: " + funcionario.getPortaria() + ", Data de Admissão: " + funcionario.getDataAdmissao().toString());
            log.setFuncionario(negocio.UsuarioLogado.getInstancia().getUsuarioLogado());
            log.setOperacao("Alteração de Cadastro de Funcionário");

            OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
            
            operacaoLogDao.persiste(log);
            
            String respostaInsercao = "Usuário inserido com sucesso";
            JOptionPane.showMessageDialog(null, respostaInsercao);
            novoUsuario.dispose();
            Administrador.main(null);
        } catch (SQLException ex) {
            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Atenção, já existe um usuário associado ao login " + funcionario.getLogin().toUpperCase() + " !");
        }


    }
}
