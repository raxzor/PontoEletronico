/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.relatorios;

import beans.Funcionario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Gilmar
 */
public class AmbienteTesteRelatorios {
    public static void main(String[] args) throws JRException {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setId(1);
        funcionario1.setNome("Viviano");
        funcionario1.setDataAdmissao(new Date(Calendar.getInstance().getTimeInMillis()));
        funcionario1.setLogin("viviano");
        funcionario1.setNivelAcesso(Boolean.FALSE);
        funcionario1.setPortaria("1123");
        funcionario1.setSalario(1200D);
        funcionario1.setSenha("1123");
        
        Funcionario funcionario2 = new Funcionario();
        funcionario2.setId(2);
        funcionario2.setNome("Gilmar");
        funcionario2.setDataAdmissao(new Date(Calendar.getInstance().getTimeInMillis()));
        funcionario2.setLogin("gilmar");
        funcionario2.setNivelAcesso(Boolean.FALSE);
        funcionario2.setPortaria("2124");
        funcionario2.setSalario(1200D);
        funcionario2.setSenha("2123");
        
        Funcionario funcionario3 = new Funcionario();
        funcionario3.setId(3);
        funcionario3.setNome("Luciano");
        funcionario3.setDataAdmissao(new Date(Calendar.getInstance().getTimeInMillis()));
        funcionario3.setLogin("lulinha");
        funcionario3.setNivelAcesso(Boolean.FALSE);
        funcionario3.setPortaria("31125");
        funcionario3.setSalario(3200D);
        funcionario3.setSenha("3123");
        
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        funcionarios.add(funcionario3);
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("param", "gilmar");
        
        JasperReport report = JasperCompileManager.compileReport("C:/Users/Gilmar/Documents/NetBeansProjects/ProjetoUnaPonto/src/main/java/service/relatorios/newReport.jrxml");
        
        JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(funcionarios));
        
        JasperExportManager.exportReportToPdfFile(print, "relatorio.pdf");
    }
    
}
