/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import gui.ListarFrequencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import service.UtilDatas;
import service.UtilFrequencia;

/**
 *
 * @author Gilmar
 */
public class AlterarFrequenciaHandler implements ActionListener {

    private ListarFrequencia listarFrequencia;

    public AlterarFrequenciaHandler(ListarFrequencia listarFrequencia) {
        this.listarFrequencia = listarFrequencia;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        TableModel model = listarFrequencia.getTable().getModel();
        if(listarFrequencia.getTable().getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null, "Selecione uma FREQUÊNCIA da Lista para editá-la!");
        }else{
        String dataString = (String) model.getValueAt(listarFrequencia.getTable().getSelectedRow(), 0);
        System.out.println(dataString);
        List<Frequencia> frequencias = listarFrequencia.getFrequencias();
        System.out.println(frequencias.size());
        for(Frequencia frequencia:frequencias){
            System.out.println(frequencia.getData().toString());
            System.out.println(UtilDatas.FormatoData(dataString).toString());
            if(frequencia.getData().toString().equals(UtilDatas.FormatoData(dataString).toString())){
                System.out.println("Alterando Presenca");
                frequencia.setPresenca(Boolean.TRUE);
                try {
                    frequenciaDao.inserirFrequencia(frequencia);
                } catch (SQLException ex) {
                    Logger.getLogger(AlterarFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Falha ao Alterar Frequência. \n \n Verifique os dados informados e tente novamente.");
                }
            }
        }
        
        String[] split = new String[2];
        Integer mes;
        Integer ano;
        split = frequencias.get(0).getData().toString().split("-");
        mes = new Integer(split[1]);
        ano = new Integer(split[0]);
        
//        Integer idUsuario = frequencias.get(0).getFuncionario().getId();
//        FuncionarioDao funcionarioDao = new FuncionarioDao();
//        FrequenciaDao frequenciaDao = new FrequenciaDao();
        Funcionario funcionario = frequencias.get(0).getFuncionario();
//        List<Frequencia> frequencias = new ArrayList<Frequencia>();
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        try {
//            funcionario = funcionarioDao.getfuncionario(idUsuario);
            frequencias = frequenciaDao.getFrequenciaFuncionario(mes, ano, funcionario.getId());
            frequencias = utilFrequencia.getFrequenciaMes(mes, ano, funcionario.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SelecionarUsuarioFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Ocorreu uma falha inesperada!");
        }

        listarFrequencia.dispose();
       
        ListarFrequencia.main(null, frequencias);
    }
    }
}
