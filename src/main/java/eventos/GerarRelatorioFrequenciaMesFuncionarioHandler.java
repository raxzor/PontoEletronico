/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import service.RelatorioFrequenciaFuncionario;

/**
 *
 * @author Gilmar
 */
public class GerarRelatorioFrequenciaMesFuncionarioHandler implements ActionListener{
    private Integer mes;
    private Integer ano;
    private List<Frequencia> frequencias;

    public GerarRelatorioFrequenciaMesFuncionarioHandler(Integer mes, Integer ano, List<Frequencia> frequencias) {
        this.mes = mes;
        this.ano = ano;
        this.frequencias = frequencias;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        try {
            RelatorioFrequenciaFuncionario.GeraRelatorio(mes, ano, frequencias);
            JOptionPane.showMessageDialog(null, "Relatório Gerado com Sucesso!!! \n \n Localização: " + RelatorioFrequenciaFuncionario.localizacaopdf + frequencias.get(0).getFuncionario().getNome() + ".pdf");
            File pdf = new File(RelatorioFrequenciaFuncionario.localizacaopdf + frequencias.get(0).getFuncionario().getNome() + ".pdf");  
        	try {  
        	  Desktop.getDesktop().open(pdf);  
        	} catch(Exception ex) {  
        	  ex.printStackTrace();  
//        	  JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
        	}  
        } catch (JRException ex) {
            Logger.getLogger(GerarRelatorioFrequenciaMesFuncionarioHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorioFrequenciaMesFuncionarioHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
