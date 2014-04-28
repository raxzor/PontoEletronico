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
import javax.swing.UIManager;
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
    		UIManager.put("OptionPane.noButtonText", "Tarde");  
    		UIManager.put("OptionPane.yesButtonText", "Manhã");
    		
    		Integer opcao = JOptionPane.showConfirmDialog(null, "Informa a Frequência a ser Alterada: \n \n ");
    		
    		UIManager.put("OptionPane.noButtonText", "Não");  
    		UIManager.put("OptionPane.yesButtonText", "Sim");
    		
    		if(opcao == JOptionPane.YES_OPTION){   			
    			 String dataString = (String) model.getValueAt(listarFrequencia.getTable().getSelectedRow(), 0);
    		        List<Frequencia> frequencias = listarFrequencia.getFrequencias();
    		        for(Frequencia frequencia:frequencias){
    		            if(frequencia.getData().toString().equals(UtilDatas.FormatoData(dataString).toString())){
    		            	
    		            	if((frequencia.getPresenca() == false) && (frequencia.getTurno().equals("M"))){
	    		                frequencia.setPresenca(Boolean.TRUE);
	    		                try {
	    		                    frequenciaDao.inserirFrequencia(frequencia);
	    		                } catch (SQLException ex) {
	    		                    Logger.getLogger(AlterarFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
	    		                    JOptionPane.showMessageDialog(null, "Falha ao Alterar Frequência. \n \n Verifique os dados informados e tente novamente.");
	    		                }
    		            	}else if((frequencia.getPresenca() == false) && (frequencia.getTurno().equals("T"))){
    		            		
    		            	}else if((frequencia.getPresenca() == true) && (frequencia.getTurno().equals("M"))){
    		            		try{
        		            		frequenciaDao.deletarFrequencia(frequencia.getId());
        		            		} catch (SQLException ex) {
    	    		                    Logger.getLogger(AlterarFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
    	    		                    JOptionPane.showMessageDialog(null, "Falha ao Alterar Frequência. \n \n Verifique os dados informados e tente novamente.");
    	    		                }
    		            	}else if((frequencia.getPresenca() == true) && (frequencia.getTurno().equals("T"))){
    		            		
    		            	}
    		            }
    		            
    		        }
    		        String[] split = new String[2];
    		        Integer mes;
    		        Integer ano;
    		        split = frequencias.get(0).getData().toString().split("-");
    		        mes = new Integer(split[1]);
    		        ano = new Integer(split[0]);
    		        Funcionario funcionario = frequencias.get(0).getFuncionario();
    		        UtilFrequencia utilFrequencia = new UtilFrequencia();
    		        try {
    		            frequencias = frequenciaDao.getFrequenciaFuncionario((mes+1), ano, funcionario.getId());
    		            frequencias = utilFrequencia.getFrequenciaMes((mes+1), ano, funcionario.getId());
    		        } catch (SQLException ex) {
    		            Logger.getLogger(SelecionarUsuarioFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
    		        }
    		        listarFrequencia.dispose();
    		        ListarFrequencia.main(null, frequencias);
    		}else if(opcao == JOptionPane.NO_OPTION){
    			 String dataString = (String) model.getValueAt(listarFrequencia.getTable().getSelectedRow(), 0);
    		        System.out.println(dataString);
    		        List<Frequencia> frequencias = listarFrequencia.getFrequencias();
    		        System.out.println(frequencias.size());
    		        for(Frequencia frequencia:frequencias){
    		            if(frequencia.getData().toString().equals(UtilDatas.FormatoData(dataString).toString())){
    		            	
    		            	if((frequencia.getPresenca() == false) && (frequencia.getTurno().equals("T"))){
	    		                frequencia.setPresenca(Boolean.TRUE);
	    		                try {
	    		                    frequenciaDao.inserirFrequencia(frequencia);
	    		                } catch (SQLException ex) {
	    		                    Logger.getLogger(AlterarFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
	    		                    JOptionPane.showMessageDialog(null, "Falha ao Alterar Frequência. \n \n Verifique os dados informados e tente novamente.");
	    		                }
    		            	}else if((frequencia.getPresenca() == false) && (frequencia.getTurno().equals("M"))){
    		            		
    		            	}else if((frequencia.getPresenca() == true) && (frequencia.getTurno().equals("T"))){
    		            		try{
        		            		frequenciaDao.deletarFrequencia(frequencia.getId());
        		            		} catch (SQLException ex) {
    	    		                    Logger.getLogger(AlterarFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
    	    		                    JOptionPane.showMessageDialog(null, "Falha ao Alterar Frequência. \n \n Verifique os dados informados e tente novamente.");
    	    		                }
    		            	}else if((frequencia.getPresenca() == true) && (frequencia.getTurno().equals("M"))){
    		            		
    		            	}
    		            }
    		            
    		        }
    		        
    		        String[] split = new String[2];
    		        Integer mes;
    		        Integer ano;
    		        split = frequencias.get(0).getData().toString().split("-");
    		        mes = new Integer(split[1]);
    		        ano = new Integer(split[0]);
    		        
//    		        Integer idUsuario = frequencias.get(0).getFuncionario().getId();
//    		        FuncionarioDao funcionarioDao = new FuncionarioDao();
//    		        FrequenciaDao frequenciaDao = new FrequenciaDao();
    		        Funcionario funcionario = frequencias.get(0).getFuncionario();
//    		        List<Frequencia> frequencias = new ArrayList<Frequencia>();
    		        UtilFrequencia utilFrequencia = new UtilFrequencia();
    		        try {
//    		            funcionario = funcionarioDao.getfuncionario(idUsuario);
    		            frequencias = frequenciaDao.getFrequenciaFuncionario((mes+1), ano, funcionario.getId());
    		            frequencias = utilFrequencia.getFrequenciaMes((mes+1), ano, funcionario.getId());
    		        } catch (SQLException ex) {
    		            Logger.getLogger(SelecionarUsuarioFrequenciaHandler.class.getName()).log(Level.SEVERE, null, ex);
//    		            JOptionPane.showMessageDialog(null, "Ocorreu uma falha inesperada!");
    		        }

    		        listarFrequencia.dispose();
    		       
    		        ListarFrequencia.main(null, frequencias);
    		}
    		
       
    }
    }
}
