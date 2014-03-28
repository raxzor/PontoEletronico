/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;
import beans.Funcionario;
import dao.FuncionarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import service.relatorios.RelatorioFrequenciaFuncionario;
import service.relatorios.UtilFrequencia;

/**
 *
 * @author Gilmar
 */
public class GerarRelatorioTodosFuncionariosHandler implements ActionListener{

    Integer mes;
    Integer ano;

    public GerarRelatorioTodosFuncionariosHandler(Integer mes, Integer ano) {
        this.mes = mes;
        this.ano = ano;
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        List<List<Frequencia>> list = new ArrayList<List<Frequencia>>();
        
        List<Funcionario> funcionarios;
        try {
            funcionarios = new FuncionarioDao().Listarfuncionarios();
            
            for (Funcionario funcionario : funcionarios) {
               list.add(utilFrequencia.getFrequenciaMes(mes, ano, funcionario.getId()));
            }
        
        for(List<Frequencia> frequencias : list){
            String nome = frequencias.get(0).getFuncionario().getNome();
                try {
                    RelatorioFrequenciaFuncionario.GeraTodosRelatorio(mes, ano, frequencias, nome);
                } catch (JRException ex) {
                    Logger.getLogger(GerarRelatorioTodosFuncionariosHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorioTodosFuncionariosHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Relatórios Emitidos");
    }
    
}
