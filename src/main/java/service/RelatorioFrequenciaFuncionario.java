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
	
	private static String localizacaojrxml = "C:/SisPD/FrequenciaMesFuncionario.jrxml";
	public static String localizacaopdf = "C:/Users/" + System.getProperty("user.name") + "/Documents/";

	public static String getFrequenciaString(Boolean valor) {
		if (valor == null) {
			return "FERIADO";
		} else if (valor) {
			return "PRESENTE";
		} else {
			return "FALTOU";
		}
	}

	public static String moedaFormat(Double entrada) {
		Locale.setDefault(Locale.US);
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		// format.setMaximumIntegerDigits(2);
		format.setMinimumIntegerDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);

		return format.format(entrada);

	}
	
	public static List<UtilFieldsRelatorioFrequencia> getFields(List<Frequencia> frequencias){
		List<UtilFieldsRelatorioFrequencia> funcionarios = new ArrayList<UtilFieldsRelatorioFrequencia>();

		for (Frequencia frequencia : frequencias) {			
			UtilFieldsRelatorioFrequencia funcionario = new UtilFieldsRelatorioFrequencia(
					UtilDatas.DateToString(frequencia.getData()), frequencia.getFuncionario().getNome(), 
					frequencia.getFuncionario().getPortaria(),
					RelatorioFrequenciaFuncionario.getFrequenciaString(frequencia.getPresenca()));
			funcionarios.add(funcionario);
		}
		
		return funcionarios;
	}

	public static Integer getDiasTrabalhados(List<Frequencia> frequencias){
		Integer dias_trabalhados = 0;
		for (Frequencia frequencia : frequencias) {
			if ((frequencia.getPresenca() != null)
					&& (frequencia.getPresenca() == true))
				dias_trabalhados++;
		}
		return dias_trabalhados;
	}

	public static Double[] getAliquotaDeducaoIR(Double salario_bruto){
		Double aliquota = 0D;
		Double deducao = 0D;
		Double descontoIR = 0D;
		if (salario_bruto < 1787.77) {
			aliquota = 0.0;
			deducao = 0.0;
		} else if ((salario_bruto >= 1787.77) && (salario_bruto <= 2679.29)) {
			aliquota = 7.5;
			deducao = 134.08;
		} else if ((salario_bruto >= 2679.30) && (salario_bruto <= 3572.43)) {
			aliquota = 15.0;
			deducao = 335.03;
		} else if ((salario_bruto >= 3572.44) && (salario_bruto <= 4463.81)) {
			aliquota = 22.5;
			deducao = 602.96;
		} else {
			aliquota = 27.5;
			deducao = 826.15;
		}
		
		descontoIR = ((salario_bruto) * (aliquota / 100) - deducao);
		Double[] d = {aliquota, descontoIR};
		return d;
	}
	
	public static Double[] getPercentualValorINSS(Double salario_bruto){
		Double percentual_desconto_inss = 0D;
		Double desconto_inss = 0D;
		if ((salario_bruto <= 1317.07)) {
			percentual_desconto_inss = 8D;
			desconto_inss = ((salario_bruto) * (percentual_desconto_inss / 100));
		} else if ((salario_bruto >= 1317.08) && (salario_bruto <= 2195.12)) {
			percentual_desconto_inss = 9D;
			desconto_inss = ((salario_bruto) * (percentual_desconto_inss / 100));
		} else if ((salario_bruto >= 2195.13) && (salario_bruto <= 4390.24)) {
			percentual_desconto_inss = 11D;
			desconto_inss = ((salario_bruto) * (percentual_desconto_inss / 100));
		}else{
			percentual_desconto_inss = null;
			desconto_inss = 4390.24 * (11 / 100);
		}
		Double[] d = {percentual_desconto_inss, desconto_inss};
		return d;
	}
	
	
	
	public static Map<String, Object> getParametros(Integer mes, Integer ano, List<Frequencia> frequencias){
		String titulo_pagina = "Relatório de Presença - " + mes + "/" + ano;
		negocio.UsuarioLogado usuarioLogado = negocio.UsuarioLogado.getInstancia();
		Funcionario usuario = usuarioLogado.getUsuarioLogado();
		
		Integer dias_uteis = new UtilFrequencia().getTotalDiasUteis(mes, ano);
		Integer dias_trabalhados = RelatorioFrequenciaFuncionario.getDiasTrabalhados(frequencias);
		Double salario_bruto = frequencias.get(0).getFuncionario().getSalario();
		Double[] ad = RelatorioFrequenciaFuncionario.getAliquotaDeducaoIR(salario_bruto);
		Double aliquota = ad[0];
		Double desconto_ir = ad[1];
		String percentual_desconto_ir = aliquota.toString() + "%";
		ad = RelatorioFrequenciaFuncionario.getPercentualValorINSS(salario_bruto);
		String percentual_desconto_inss = ad[0] + "%";
		Double desconto_inss = ad[1];
		Double salario_liquido = 0D;
		
		Integer total_faltas = ((dias_uteis) - (dias_trabalhados));
		Double desconto_por_faltas = (((salario_bruto) / dias_uteis) * total_faltas);
		
		salario_liquido = ((salario_bruto) - (desconto_por_faltas + (desconto_inss + desconto_ir)));
		if (dias_trabalhados == 0) {
			salario_liquido = 0D;
			desconto_inss = 0D;
			desconto_ir = 0D;
			 percentual_desconto_inss = "0%";
			 percentual_desconto_ir = "0%";
		}
		
		UtilParametrosRelatorioFrequencia utilRelatorioFrequenciaMesFuncionario = new UtilParametrosRelatorioFrequencia(
				dias_uteis.toString(), dias_trabalhados.toString(),	total_faltas.toString(), 
				RelatorioFrequenciaFuncionario.moedaFormat(salario_bruto),
				RelatorioFrequenciaFuncionario.moedaFormat(desconto_por_faltas), percentual_desconto_inss,
				percentual_desconto_ir, RelatorioFrequenciaFuncionario.moedaFormat(desconto_inss),
				RelatorioFrequenciaFuncionario.moedaFormat(desconto_ir), RelatorioFrequenciaFuncionario.moedaFormat(salario_liquido),
				titulo_pagina, usuario.getNome());
		
		return utilRelatorioFrequenciaMesFuncionario.getparametros();
	}
	
	public static void GeraRelatorio(Integer mes, Integer ano, List<Frequencia> frequencias) throws JRException, SQLException {

		List<UtilFieldsRelatorioFrequencia> funcionarios = RelatorioFrequenciaFuncionario.getFields(frequencias);
		Map<String, Object> parametros = RelatorioFrequenciaFuncionario.getParametros(mes, ano, frequencias);

		JasperReport report = JasperCompileManager.compileReport(localizacaojrxml);
		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(funcionarios));
		String nomePDF = frequencias.get(0).getFuncionario().getNome();
		JasperExportManager.exportReportToPdfFile(print, localizacaopdf	+ nomePDF + ".pdf");
	}

}
