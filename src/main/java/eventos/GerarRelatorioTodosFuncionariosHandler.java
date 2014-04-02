/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;
import beans.Funcionario;
import dao.FuncionarioDao;
import gui.ImprimirFrequencias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import service.RelatorioFrequenciaFuncionario;
import service.UtilDatas;
import service.UtilFrequencia;

/**
 *
 * @author Gilmar
 */
public class GerarRelatorioTodosFuncionariosHandler implements ActionListener {

    ImprimirFrequencias imprimirFrequencias;
    Integer mes;
    Integer ano;
    String data;

    public GerarRelatorioTodosFuncionariosHandler(ImprimirFrequencias imprimirFrequencias) {
        this.imprimirFrequencias = imprimirFrequencias;
        
    }

    public void actionPerformed(ActionEvent e) {

        Integer[] arrayInteger = new Integer[2];
        data = imprimirFrequencias.getData();
        try {
            System.out.println("data " + data);
            arrayInteger = UtilDatas.FormatoDataMesAnoInteger(data);
            mes = arrayInteger[0];
            ano = arrayInteger[1];
            System.out.println(mes);
            System.out.println(ano);
            UtilFrequencia utilFrequencia = new UtilFrequencia();
            List<List<Frequencia>> list = new ArrayList<List<Frequencia>>();

            List<Funcionario> funcionarios;
            try {
                funcionarios = new FuncionarioDao().Listarfuncionarios();

                for (Funcionario funcionario : funcionarios) {
                    list.add(utilFrequencia.getFrequenciaMes(mes, ano, funcionario.getId()));
                }

                for (List<Frequencia> frequencias : list) {
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
        } catch (NumberFormatException ev) {
            JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida!");
        }

    }
}
