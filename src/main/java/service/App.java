package service;

import beans.Expediente;
import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Hello world!
 * 
 */
public class App {

	// static String callSystem( String cmd ) throws IOException
	// {
	// StringBuffer sb = new StringBuffer() ;
	// Process proc = Runtime.getRuntime().exec( cmd ) ;
	// BufferedReader bufferedreader = new BufferedReader( new
	// InputStreamReader( proc.getInputStream() ) );
	//
	// String line;
	// while ( ( line = bufferedreader.readLine()) != null )
	// {
	// sb.append( line ).append( "\n" ) ;
	// }
	//
	// try { proc.waitFor() ; } catch( InterruptedException ex ) {}
	// return sb.toString() ;
	// }
	public static void main(String[] args) throws SQLException {
		// Date d = new Date(Calendar.getInstance().getTimeInMillis());
		// System.out.println(d);
		// Integer mes = 03;
		// Integer ano = 2014;
		// System.out.println("****************");
		// UtilFrequencia utilFrequencia = new UtilFrequencia();
		// Integer maxDias = utilFrequencia.getMaximoDias(mes, ano);
		// Calendar c = Calendar.getInstance();
		// FrequenciaDao frequenciaDao = new FrequenciaDao();
		// List<Frequencia> frequencias =
		// frequenciaDao.getFrequenciaFuncionario(03, 2014, 39);
		// List<String> datas = new ArrayList<String>();
		// String retorno = "";
		//
		//
		// for(Frequencia frequencia : frequencias){
		// datas.add(frequencia.getData().toString());
		// }
		//
		//
		// for(int i = 1; i <= maxDias; i++){
		// c.set(ano, (mes - 1), i);
		// d = new Date(c.getTimeInMillis());
		// if(!datas.contains(d.toString())){
		// Frequencia f = new Frequencia();
		// f.setData(d);
		// f.setFuncionario(frequencias.get(0).getFuncionario());
		// f.setPresenca(Boolean.FALSE);
		//
		// frequencias.add(f);
		// }
		//
		// }
		//
		// Collections.sort(frequencias, new Comparator<Frequencia>() {
		// public int compare(Frequencia o1, Frequencia o2) {
		// return o1.getData().compareTo(o2.getData());
		// }
		// } );
		// for(Frequencia frequencia : frequencias){
		// System.out.println(frequencia.getData() + " | " +
		// frequencia.getFuncionario().getNome() +
		// " | " + frequencia.getFuncionario().getPortaria() + " | " +
		// frequencia.getPresenca().toString());
		// }

		// UtilFrequencia uf = new UtilFrequencia();
		// List<Frequencia> frequencias = uf.getFrequenciaMes(03, 2014, 13);
		//
		// for(Frequencia frequencia : frequencias){
		// System.out.println(frequencia.getData() + " | " +
		// frequencia.getFuncionario().getNome() +
		// " | " + frequencia.getFuncionario().getPortaria() + " | " +
		// frequencia.getPresenca().toString());
		// }

		// String[] a = new String[10];
		//
		// String[] b = a;
		//
		// for(String c : b){
		// System.out.println(c);
		// }

		// System.out.println(System.getProperty("user.name") );

		// System.out.println(new
		// String().getClass().getResource("C:/Users/Gilmar/Documents/joao.pdf"));
		// System.out.println(new
		// String().getClass().getResource("/service/relatorios/FrequenciaMesFuncionario.jrxml").toString().substring(6));
		// "C:/Users/" + System.getProperty("user.name") + "/Documents/"
		// File pdf = new File("C:/Users/" + System.getProperty("user.name") +
		// "/Documents/windows.pdf");
		// try {
		// Desktop.getDesktop().open(pdf);
		// } catch(Exception ex) {
		// ex.printStackTrace();
		// JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);
		// }

		// Calendar calendar = Calendar.getInstance();
		// // System.out.println(calendar.getTimeInMillis());
//		Calendar c = Calendar.getInstance();
//    	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 07, 00, 00);
//		System.out.println(Calendar.day);
//		 Timestamp timestamp = new Timestamp(c.getTimeInMillis());
//		 System.out.println(timestamp);

//		FrequenciaDao frequenciaDao = new FrequenciaDao();
//		Calendar c = Calendar.getInstance();
//		c.set(2014, (04 -1), 8);
//		System.out.println(new Date(c.getTimeInMillis()));
//		System.out.println(frequenciaDao.frequenciasDia(39, new Date(c.getTimeInMillis())));
		
		// try {
		// String hwtime = callSystem( "/sbin/hwclock" ) ;
		//
		// System.out.println(hwtime);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// Time time = new Time(calendar.getTimeInMillis());
		// System.out.println(time);

		// Calendar corteManha = Calendar.getInstance();
		// Calendar corteTarde = Calendar.getInstance();
		// Calendar corrente = Calendar.getInstance();
		// corteManha.set(corteManha.get(Calendar.YEAR),
		// corteManha.get(Calendar.MONTH),
		// corteManha.get(Calendar.DAY_OF_MONTH), 07, 45, 00);
		// corteTarde.set(corteTarde.get(Calendar.YEAR),
		// corteTarde.get(Calendar.MONTH),
		// corteTarde.get(Calendar.DAY_OF_MONTH), 13, 45, 00);

		// System.out.println(corrente.getTime());
		// System.out.println(corteManha.getTime());
		// System.out.println(corteTarde.getTime());
		//
		// System.out.println(corrente.after(corteManha));
		// System.out.println(corrente.after(corteTarde));

		// Double[] d =
		// RelatorioFrequenciaFuncionario.getPercentualValorINSS(724.00);
		// System.out.println(d[0]);
		// System.out.println(RelatorioFrequenciaFuncionario.moedaFormat(d[1]));

		// List<Integer> i = new ArrayList<Integer>();
		// List<Integer> j = new ArrayList<Integer>();
		//
		// List<Integer> k = new ArrayList<Integer>();
		//
		// i.add(1);
		// j.add(2);
		// j.add(3);
		// k.add(5);
		// k.add(6);
		//
		// i.addAll(j);
		// i.addAll(k);
		//
		// System.out.println(i);

		//
		// UtilFrequencia frequencia = new UtilFrequencia();
		// // System.out.println(frequencia.getDatasFeriadosOrdinarios20a20(21,
		// null, 04, 2014));
		// //
		// // System.out.println(frequencia.diasDaSemana(04, 2014, 21));
		// // frequencia.getFrequenciaMesAnterior(03, 2014, 39);
		// for(Frequencia f :frequencia.getFrequenciaMesAnterior(03, 2014, 39)){
		// System.out.println(f.getData() + " | " + f.getPresenca());
		// }

		// System.out.println(Calendar.getInstance().getTime());

//		FrequenciaDao frequenciaDao = new FrequenciaDao();
//		Integer[] locais = new Integer[2];
//		List<Frequencia> frequencias = frequenciaDao.getFrequenciaFuncionario(04, 2014, 8, 39);
//		System.out.println(frequencias.size());
//		if (frequencias.size() == 0) {
//			System.out.println("Não Existe Entrada Compatível!");
//		} else {
//			int c = 0;
//			for (int t = 0; t < frequencias.size(); t++) {
//				if (frequencias.get(t).getHoraSaida() == null) {
//					System.out.println("Você pode Registrar Saída no Turno: "
//							+ (t + 1));
//					c++;
//				}
//			}
//			if (c == 0) {
//				System.out
//						.println("Usuário Já registrou saída pela Manhã e Tarde");
//			}
//		}
		
//		UtilFrequencia utilFrequencia = new UtilFrequencia();
//		List<Frequencia> frequencias = utilFrequencia.getFrequenciaMes(04, 2014, 39);
//		for(Frequencia frequencia : frequencias){
//			System.out.println(frequencia.getData() + " | " + frequencia.getTurno() +" | " + frequencia.getPresenca());
//		}
//		
		
//		List<String> s = new ArrayList<String>();
//		String x = "a";
//		s.add(x);
//		x = "b";
//		s.add(x);
//		x = "c";
//		s.add(x);
//		x = "c";
//		s.add(x);
//		x = "a";
//		s.add(x);
//		System.out.println(s);
//		
//		for(String i : s){
//			int aux = 0;
//			for(String i2 : s){
//				if(i.equals(i2)){
//					aux ++;
//				}
//			}
//			if(aux > 1){
////				s.add(i);
//			}
//		}
//		System.out.println(s);
//	Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());	
//	System.out.println(timestamp);
//	UtilFrequencia frequencia = new UtilFrequencia();
//	timestamp = null;
//	System.out.println(frequencia.getHorasTimeStamp(timestamp));
		
		
//		FrequenciaDao frequenciaDao = new FrequenciaDao();
//		Date d = frequenciaDao.ListarFrequencia().get(0).getData();
//		UtilFrequencia utilFrequencia = new UtilFrequencia();
//		Calendar c1 = Calendar.getInstance();
//		c1.setTimeInMillis(d.getTime());
//
//		List<Frequencia> frequencias = utilFrequencia.getFrequenciaMesAnterior(04, 2014, 39);
//		List<Expediente> expedientes = frequencias.get(0).getFuncionario().getExpedientes();
//		List<Integer> integers = new ArrayList<Integer>();
//		
//		for(Expediente expediente : expedientes){
//			integers.add(expediente.getDiaSemana());
//		}
//		
//		for(Frequencia frequencia : frequencias){
//			System.out.println(frequencia.getData().toString() + " - " + (utilFrequencia.diasDaSemana(frequencia.getData()) - 2));
//
//			if(!integers.contains(utilFrequencia.diasDaSemana(frequencia.getData()) - 2)){
//				System.out.println((utilFrequencia.diasDaSemana(frequencia.getData()) - 2));
//			}
//		}
		

//		System.out.println(new Date(c1.getTimeInMillis()));
//		System.out.println(utilFrequencia.diasDaSemana(c1.get(Calendar.MONTH), c1.get(Calendar.YEAR), c1.get(Calendar.DAY_OF_MONTH)));
		
//		System.out.println("****************************************");
//		Calendar c = Calendar.getInstance();
//		c.set(2014, 06, 01);
//		System.out.println(new Date(c1.getTimeInMillis()));
//		
//		System.out.println(utilFrequencia.diasDaSemana(new Date(c1.getTimeInMillis())));
		
		
//		for(Expediente expediente : expedientes){
////			System.out.println(expediente.getDiaSemana());
//		}
//		System.out.println("********************");
//		for(Frequencia frequencia : frequencias){
//			List<Expediente> expedientes = frequencia.getFuncionario().getExpedientes();
//			for(Expediente expediente : expedientes){
//				System.out.println(expediente.getDiaSemana() + " " + (utilFrequencia.diasDaSemana(frequencia.getData()) -2));
//				System.out.println(expediente.getDiaSemana() == (utilFrequencia.diasDaSemana(frequencia.getData()) -2));
//	        	if((expediente.getDiaSemana() != (utilFrequencia.diasDaSemana(frequencia.getData()) -2))){
//	        		 frequencia.setPresenca(true);
////	     			System.out.println(frequencia.getData() + " - " + frequencia.getTurno() + " - " + frequencia.getPresenca());
//
////	        		 System.out.println(frequencia.getData());
//	        	} 
//			}
//			System.out.println("************");
			
//			System.out.println(frequencia.getData());
//			System.out.println(utilFrequencia.diasDaSemana(frequencia.getData()) -2);
//			System.out.println(frequencia.getData() + " - " + frequencia.getTurno() + " - " + frequencia.getPresenca());
			
			
//		}

//		File dir = new File("C:/Users/" + System.getProperty("user.name") + "/Documents/" + "Relatorio" + (RelatorioFrequenciaFuncionario.mesString(02 +1)) + " de " + 2014); 
//		dir.mkdirs();
		
//		JOptionPane.showConfirmDialog(null, "Mensagem");
//		JOptionPane jOptionPane = new JOptionPane();
//		Object[] o = new Object[3];
//		o[0] = "Manhã";
//		o[1] = "Tarde";
//		o[2] = "Cancelar";
//		jOptionPane.setOptions(o);
		
		
//		UIManager.put("OptionPane.cancelButtonText", "Cancelar");  
//		UIManager.put("OptionPane.noButtonText", "Tarde");  
//		UIManager.put("OptionPane.yesButtonText", "Manhã");
//		
//		JOptionPane.showConfirmDialog(null, "Informa a Frequência a ser Alterada: \n \n ");
		
//		String gilmar = "Francisco Gilmar";
//		
//		System.out.println(gilmar.toUpperCase().split(" ")[0]);
		
//		System.out.println(new UtilFrequencia().diasDaSemana(04, 2014, 26));
		
//		System.out.println(new arrayl);
	}
	
}
