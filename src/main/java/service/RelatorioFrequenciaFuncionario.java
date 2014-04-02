/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class RelatorioFrequenciaFuncionario {
    
    public static String getFrequenciaString(Boolean valor){
        if(valor == null){
            return "FERIADO";
        }else if(valor){
            return "PRESENTE";
        }else{
            return "FALTOU";
        }
    }
    
    private static String localizacaojrxml = "C:/SisPD/FrequenciaMesFuncionario.jrxml";
    public static String localizacaopdf = "C:/Users/" + System.getProperty("user.name") + "/Documents/";
    public static void GeraRelatorio(Integer mes, Integer ano, Integer idFuncionario) throws JRException, SQLException {
        JasperReport report = JasperCompileManager.compileReport("C:/Users/Gilmar/Documents/NetBeansProjects/ProjetoUnaPonto/src/main/java//service/relatorios/FrequenciaMesFuncionario.jrxml");
        
        negocio.UsuarioLogado usuarioLogado = negocio.UsuarioLogado.getInstancia();
        Funcionario usuario = usuarioLogado.getUsuarioLogado();
        
            Locale.setDefault(Locale.US); 
            NumberFormat format = NumberFormat.getInstance();    
            format.setMaximumFractionDigits(2);    
            format.setMinimumFractionDigits(2);    
//            format.setMaximumIntegerDigits(2); 
            format.setMinimumIntegerDigits(2);
            format.setRoundingMode(RoundingMode.HALF_UP); 
        
        
        
        
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        List<Frequencia> frequencias = frequenciaDao.getFrequenciaFuncionario(mes, ano, idFuncionario);
        
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        Integer qtdeDiasMes = utilFrequencia.getMaximoDias(mes, ano);
        utilFrequencia.getTotalDiasUteis(mes, ano);
        
        Map<Integer, Integer> diasPresenca = new HashMap<Integer, Integer>();
        
        for(Frequencia f : frequencias){
            
            diasPresenca.put(f.getAux(), f.getAux());
        }
        
        System.out.println(diasPresenca);
        System.out.println("**");
        
        String dataImprimir = "";
        String nomeImprimir = "";
        String portatiaImprimir = "";
        String presencaImprimir = "";
        
        List<UtilFieldsRelatorioFrequencia> funcionarios = new ArrayList<UtilFieldsRelatorioFrequencia>();

        for(Integer i = 1; i <= qtdeDiasMes; i++){
            Integer dia = i;
            dataImprimir = dia + "/" + mes + "/" + ano;
            if((i<10)&&(mes<10)){
                 dataImprimir = "0" + dia + "/0" + mes + "/" + ano;
            }else if(i<10){
                 dataImprimir = "0" + dia + "/" + mes + "/" + ano;
            }else if(mes<10){
                dataImprimir = dia + "/0" + mes + "/" + ano;
            }
            
            nomeImprimir = frequencias.get(0).getFuncionario().getNome();
            portatiaImprimir = frequencias.get(0).getFuncionario().getPortaria();
            
            presencaImprimir = "FALTOU";
            if(dia == diasPresenca.get(dia)){
                presencaImprimir = "PRESENTE";
            }
            System.out.println(dataImprimir + " | " + nomeImprimir + " | " + portatiaImprimir + " | " + presencaImprimir);
            UtilFieldsRelatorioFrequencia funcionario = new UtilFieldsRelatorioFrequencia(dataImprimir, nomeImprimir, portatiaImprimir, presencaImprimir);

            funcionarios.add(funcionario);
            
            
        }
            
            Integer dias_uteis = utilFrequencia.getTotalDiasUteis(mes, ano);
            Integer dias_trabalhados = frequencias.size();
            Integer total_faltas = (dias_uteis)-(frequencias.size());
            Double salario_bruto = frequencias.get(0).getFuncionario().getSalario();
            Double salarioBrutoAnual = (salario_bruto * 12);
            Double desconto_por_faltas = (((salario_bruto)/dias_uteis)*total_faltas);
            Double deducao = 0D;
            Double aliquota = 0.0;
            if(salarioBrutoAnual < 21453.24){
                aliquota = 0.0;
            }else if((salarioBrutoAnual > 21453.24) && (salarioBrutoAnual <  32151.48)){
                aliquota = 7.5;
                deducao = 134.08;
            }else if((salarioBrutoAnual > 32151.48) && (salarioBrutoAnual < 42869.16)){
                aliquota = 15.0;
                deducao = 335.03;
            }else if((salarioBrutoAnual > 42869.16) && (salarioBrutoAnual <  53565.72)){
                aliquota = 22.5;
                deducao = 602.96;
            }else{
                aliquota = 27.5;
                deducao = 826.15;
            }
            
            String percentual_desconto_ir = aliquota.toString() + "%";
            
            String percentual_desconto_inss = "0%";
        	Double desconto_inss = 0D;
            if((salario_bruto < 1317.07)){
            	percentual_desconto_inss = "8%";
            	desconto_inss = ((salario_bruto)* (8/100));
    		}else if((salario_bruto > 1317.07) && (salario_bruto < 2195.13)){
	            percentual_desconto_inss = "9%";
	            desconto_inss = ((salario_bruto)* (9/100));
            }else if((salario_bruto > 2195.13) && (salario_bruto < 4390.24)){
	            percentual_desconto_inss = "11%";
	            desconto_inss = ((salario_bruto)* (11/100)); 
            }
            Double desconto_ir = ((salario_bruto)*(aliquota / 100) - deducao);
            Double salario_liquido = ((salario_bruto)-(desconto_por_faltas + (desconto_inss + desconto_ir))); 
            String titulo_pagina = "Relatório de Presença Maio 2014";


            UtilParametrosRelatorioFrequencia utilRelatorioFrequenciaMesFuncionario = new UtilParametrosRelatorioFrequencia(dias_uteis.toString(), dias_trabalhados.toString(), total_faltas.toString(), format.format(salario_bruto), format.format(desconto_por_faltas), percentual_desconto_inss, percentual_desconto_ir, format.format(desconto_inss), format.format(desconto_ir), format.format(salario_liquido), titulo_pagina, usuario.getNome());

            Map<String, Object> parametros = utilRelatorioFrequenciaMesFuncionario.getparametros();

            System.out.println(parametros.toString());

            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(funcionarios));

            JasperExportManager.exportReportToPdfFile(print, "C:/Users/Gilmar/Documents/NetBeansProjects/ProjetoUnaPonto/src/main/java//service/relatorios/relatorio_frequencia_mes_funcionario.pdf");
    
    }
    
    public static void GeraRelatorio(Integer mes, Integer ano, List<Frequencia> frequencias) throws JRException, SQLException {
        JasperReport report = JasperCompileManager.compileReport(localizacaojrxml);
        
        negocio.UsuarioLogado usuarioLogado = negocio.UsuarioLogado.getInstancia();
        Funcionario usuario = usuarioLogado.getUsuarioLogado();
        
            Locale.setDefault(Locale.US); 
            NumberFormat format = NumberFormat.getInstance();    
            format.setMaximumFractionDigits(2);    
            format.setMinimumFractionDigits(2);    
//            format.setMaximumIntegerDigits(2); 
            format.setMinimumIntegerDigits(2);
            format.setRoundingMode(RoundingMode.HALF_UP); 
        
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        Integer qtdeDiasMes = utilFrequencia.getMaximoDias(mes, ano);
        utilFrequencia.getTotalDiasUteis(mes, ano);
       
        String dataImprimir = "";
        String nomeImprimir = "";
        String portatiaImprimir = "";
        String presencaImprimir = "";
        
        List<UtilFieldsRelatorioFrequencia> funcionarios = new ArrayList<UtilFieldsRelatorioFrequencia>();

        
        for(Frequencia frequencia : frequencias){
            dataImprimir = UtilDatas.DateToString(frequencia.getData());
            nomeImprimir = frequencia.getFuncionario().getNome();
            portatiaImprimir = frequencia.getFuncionario().getPortaria();
            presencaImprimir = RelatorioFrequenciaFuncionario.getFrequenciaString(frequencia.getPresenca());
            System.out.println(dataImprimir + " | " + nomeImprimir + " | " + portatiaImprimir + " | " + presencaImprimir);
            UtilFieldsRelatorioFrequencia funcionario = new UtilFieldsRelatorioFrequencia(dataImprimir, nomeImprimir, portatiaImprimir, presencaImprimir);
            funcionarios.add(funcionario);
        }
            
            Integer dias_uteis = utilFrequencia.getTotalDiasUteis(mes, ano);
            Integer dias_trabalhados = 0;
            for(Frequencia frequencia:frequencias){
                if((frequencia.getPresenca() != null) && (frequencia.getPresenca() == true)) dias_trabalhados++;
                
            }

            Integer total_faltas = ((dias_uteis)-(dias_trabalhados));
            Double salario_bruto = frequencias.get(0).getFuncionario().getSalario();
            Double salarioBrutoAnual = (salario_bruto * 12);
            Double desconto_por_faltas = (((salario_bruto)/dias_uteis)*total_faltas);
            String percentual_desconto_inss = "8%";
            Double aliquota = 0.0;
            Double deducao = 0.0;
            if(salario_bruto < 1787.77){
                aliquota = 0.0;
                deducao = 0.0;
            }else if((salario_bruto >= 1787.77) && (salario_bruto <=  2679.29)){
                aliquota = 7.5;
                deducao = 134.08;
            }else if((salario_bruto >= 2679.30) && (salario_bruto <= 3572.43)){
                aliquota = 15.0;
                deducao = 335.03;
            }else if((salario_bruto >= 3572.44) && (salario_bruto <=  4463.81)){
                aliquota = 22.5;
                deducao = 602.96;
            }else{
                aliquota = 27.5;
                deducao = 826.15;
            }
            
            String percentual_desconto_ir = aliquota.toString() + "%";
            Double desconto_inss = ((salario_bruto)* 0.08); 
            Double desconto_ir = ((salario_bruto)*(aliquota / 100) - deducao);
            Double salario_liquido = 0D;
            Double descontos_por_imposto = (desconto_inss + desconto_ir);
            salario_liquido = ((salario_bruto)-(desconto_por_faltas + descontos_por_imposto)); 
            if(dias_trabalhados == 0){
                salario_liquido = 0D;
                desconto_inss = 0D;
                desconto_ir = 0D;
//                percentual_desconto_inss = "0%";
//                percentual_desconto_ir = "0%";
            }
            String titulo_pagina = "Relatório de Presença Maio 2014";


            UtilParametrosRelatorioFrequencia utilRelatorioFrequenciaMesFuncionario = new UtilParametrosRelatorioFrequencia(dias_uteis.toString(), dias_trabalhados.toString(), total_faltas.toString(), format.format(salario_bruto), format.format(desconto_por_faltas), percentual_desconto_inss, percentual_desconto_ir, format.format(desconto_inss), format.format(desconto_ir), format.format(salario_liquido), titulo_pagina, usuario.getNome());

            Map<String, Object> parametros = utilRelatorioFrequenciaMesFuncionario.getparametros();

            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(funcionarios));
            
            String nomePDF = frequencias.get(0).getFuncionario().getNome();

            JasperExportManager.exportReportToPdfFile(print, localizacaopdf + nomePDF + ".pdf");
    
    }
    
    
    public static void GeraTodosRelatorio(Integer mes, Integer ano, List<Frequencia> frequencias, String nomePDF) throws JRException, SQLException {
        JasperReport report = JasperCompileManager.compileReport(localizacaojrxml);
        
        negocio.UsuarioLogado usuarioLogado = negocio.UsuarioLogado.getInstancia();
        Funcionario usuario = usuarioLogado.getUsuarioLogado();
        
            Locale.setDefault(Locale.US); 
            NumberFormat format = NumberFormat.getInstance();    
            format.setMaximumFractionDigits(2);    
            format.setMinimumFractionDigits(2);    
//            format.setMaximumIntegerDigits(2); 
            format.setMinimumIntegerDigits(2);
            format.setRoundingMode(RoundingMode.HALF_UP); 
        
        UtilFrequencia utilFrequencia = new UtilFrequencia();
        Integer qtdeDiasMes = utilFrequencia.getMaximoDias(mes, ano);
        utilFrequencia.getTotalDiasUteis(mes, ano);
       
        String dataImprimir = "";
        String nomeImprimir = "";
        String portatiaImprimir = "";
        String presencaImprimir = "";
        
        List<UtilFieldsRelatorioFrequencia> funcionarios = new ArrayList<UtilFieldsRelatorioFrequencia>();

        
        for(Frequencia frequencia : frequencias){
            dataImprimir = UtilDatas.DateToString(frequencia.getData());
            nomeImprimir = frequencia.getFuncionario().getNome();
            portatiaImprimir = frequencia.getFuncionario().getPortaria();
            presencaImprimir = RelatorioFrequenciaFuncionario.getFrequenciaString(frequencia.getPresenca());
            UtilFieldsRelatorioFrequencia funcionario = new UtilFieldsRelatorioFrequencia(dataImprimir, nomeImprimir, portatiaImprimir, presencaImprimir);
            funcionarios.add(funcionario);
        }
            
            Integer dias_uteis = utilFrequencia.getTotalDiasUteis(mes, ano);
            Integer dias_trabalhados = 0;
            for(Frequencia frequencia:frequencias){
                if((frequencia.getPresenca() != null) && (frequencia.getPresenca() == true)) dias_trabalhados++;
                
            }

            Integer total_faltas = ((dias_uteis)-(dias_trabalhados));
            Double salario_bruto = frequencias.get(0).getFuncionario().getSalario();
            Double salarioBrutoAnual = (salario_bruto * 12);
            Double desconto_por_faltas = (((salario_bruto)/dias_uteis)*total_faltas);
            String percentual_desconto_inss = "8%";
            Double aliquota = 0.0;
            if(salarioBrutoAnual < 21453.24){
                aliquota = 0.0;
            }else if((salarioBrutoAnual > 21453.24) && (salarioBrutoAnual <  32151.48)){
                aliquota = 7.5;
            }else if((salarioBrutoAnual > 32151.48) && (salarioBrutoAnual < 42869.16)){
                aliquota = 15.0;
            }else if((salarioBrutoAnual > 42869.16) && (salarioBrutoAnual <  53565.72)){
                aliquota = 22.5;
            }else{
                aliquota = 27.5;
            }
            
            String percentual_desconto_ir = aliquota.toString() + "%";
            Double desconto_inss = ((salario_bruto)* 0.08); 
            Double desconto_ir = ((salario_bruto)*(aliquota / 100));
            Double salario_liquido = 0D;
            Double descontos_por_imposto = (desconto_inss + desconto_ir);
            salario_liquido = ((salario_bruto)-(desconto_por_faltas + descontos_por_imposto)); 
            if(salario_liquido < descontos_por_imposto){
                salario_liquido = 0D;
            }
            String titulo_pagina = "Relatório de Presença Maio 2014";


            UtilParametrosRelatorioFrequencia utilRelatorioFrequenciaMesFuncionario = new UtilParametrosRelatorioFrequencia(dias_uteis.toString(), dias_trabalhados.toString(), total_faltas.toString(), format.format(salario_bruto), format.format(desconto_por_faltas), percentual_desconto_inss, percentual_desconto_ir, format.format(desconto_inss), format.format(desconto_ir), format.format(salario_liquido), titulo_pagina, usuario.getNome());

            Map<String, Object> parametros = utilRelatorioFrequenciaMesFuncionario.getparametros();

            JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(funcionarios));

            JasperExportManager.exportReportToPdfFile(print, "C:/Users/" + System.getProperty("user.name") + "/Documents/" + nomePDF + ".pdf");
    
    }
}
