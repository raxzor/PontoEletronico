package service;

import beans.Frequencia;
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

/**
 * Hello world!
 *
 */
public class App 
{
	
//	static String callSystem( String cmd ) throws IOException 
//    {
//        StringBuffer sb = new StringBuffer() ;
//        Process proc = Runtime.getRuntime().exec( cmd ) ;
//        BufferedReader bufferedreader = new BufferedReader( new InputStreamReader( proc.getInputStream() ) );
//
//        String line;
//        while ( ( line = bufferedreader.readLine()) != null ) 
//        {
//            sb.append( line ).append( "\n" ) ;
//        }
//    
//        try { proc.waitFor() ; } catch( InterruptedException ex ) {}
//        return sb.toString() ;
//    }
    public static void main( String[] args ) throws SQLException
    {
//        Date d = new Date(Calendar.getInstance().getTimeInMillis());
//        System.out.println(d);
//        Integer mes = 03;
//        Integer ano = 2014;
//        System.out.println("****************");
//        UtilFrequencia utilFrequencia = new UtilFrequencia();
//        Integer maxDias = utilFrequencia.getMaximoDias(mes, ano);
//        Calendar c = Calendar.getInstance();
//        FrequenciaDao frequenciaDao = new FrequenciaDao();
//        List<Frequencia> frequencias = frequenciaDao.getFrequenciaFuncionario(03, 2014, 39);
//        List<String> datas = new ArrayList<String>();
//        String retorno = "";
//        
//        
//            for(Frequencia frequencia : frequencias){
//                datas.add(frequencia.getData().toString());
//            }
//        
//        
//        for(int i = 1; i <= maxDias; i++){
//            c.set(ano, (mes - 1), i);
//            d = new Date(c.getTimeInMillis());
//            if(!datas.contains(d.toString())){
//                Frequencia f = new Frequencia();
//                f.setData(d);
//                f.setFuncionario(frequencias.get(0).getFuncionario());
//                f.setPresenca(Boolean.FALSE);
//                
//                frequencias.add(f);
//             }
//            
//        }
//        
//        Collections.sort(frequencias, new Comparator<Frequencia>() {      
//    public int compare(Frequencia o1, Frequencia o2) {
//        return o1.getData().compareTo(o2.getData());
//    }      
//       }      );
//        for(Frequencia frequencia : frequencias){
//            System.out.println(frequencia.getData() + " | " + frequencia.getFuncionario().getNome() +
//                    " | " + frequencia.getFuncionario().getPortaria() + " | " + frequencia.getPresenca().toString());
//        }
        
//        UtilFrequencia uf = new UtilFrequencia();
//        List<Frequencia> frequencias = uf.getFrequenciaMes(03, 2014, 13);
//        
//        for(Frequencia frequencia : frequencias){
//            System.out.println(frequencia.getData() + " | " + frequencia.getFuncionario().getNome() +
//                    " | " + frequencia.getFuncionario().getPortaria() + " | " + frequencia.getPresenca().toString());
//        }
        
//        String[] a = new String[10];
//        
//        String[] b = a;
//        
//        for(String c : b){
//            System.out.println(c);
//        }
    	
    	
//			System.out.println(System.getProperty("user.name") );
    	
//    	System.out.println(new String().getClass().getResource("C:/Users/Gilmar/Documents/joao.pdf"));
//    	System.out.println(new String().getClass().getResource("/service/relatorios/FrequenciaMesFuncionario.jrxml").toString().substring(6));
//    	"C:/Users/" + System.getProperty("user.name") + "/Documents/"
//    	File pdf = new File("C:/Users/" + System.getProperty("user.name") + "/Documents/windows.pdf");  
//    	try {  
//    	  Desktop.getDesktop().open(pdf);  
//    	} catch(Exception ex) {  
//    	  ex.printStackTrace();  
//    	  JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
//    	}  
    	
//    	Calendar calendar = Calendar.getInstance();
////    	System.out.println(calendar.getTimeInMillis());
//    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    	System.out.println(timestamp);
    	
//    	 try {
//			String hwtime = callSystem( "/sbin/hwclock" ) ;
//			
//			System.out.println(hwtime);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	Time time = new Time(calendar.getTimeInMillis());
//    	System.out.println(time);

//    	Calendar corteManha = Calendar.getInstance();
//    	Calendar corteTarde = Calendar.getInstance();
//    	Calendar corrente = Calendar.getInstance();
//    	corteManha.set(corteManha.get(Calendar.YEAR), corteManha.get(Calendar.MONTH), corteManha.get(Calendar.DAY_OF_MONTH), 07, 45, 00);
//    	corteTarde.set(corteTarde.get(Calendar.YEAR), corteTarde.get(Calendar.MONTH), corteTarde.get(Calendar.DAY_OF_MONTH), 13, 45, 00);
    	
//    	System.out.println(corrente.getTime());
//    	System.out.println(corteManha.getTime());
//    	System.out.println(corteTarde.getTime());
//    	
//    	System.out.println(corrente.after(corteManha));
//    	System.out.println(corrente.after(corteTarde));
    	
    	
//    	Double[] d = RelatorioFrequenciaFuncionario.getPercentualValorINSS(724.00);
//    	System.out.println(d[0]);
//    	System.out.println(RelatorioFrequenciaFuncionario.moedaFormat(d[1]));
    	
    	
//    	List<Integer> i = new ArrayList<Integer>();
//    	List<Integer> j = new ArrayList<Integer>();
//
//    	List<Integer> k = new ArrayList<Integer>();
//    	
//    	i.add(1);
//    	j.add(2);
//    	j.add(3);
//    	k.add(5);
//    	k.add(6);
//    	
//    	i.addAll(j);
//    	i.addAll(k);
//    	
//    	System.out.println(i);
    	
    	
    	
    	UtilFrequencia frequencia = new UtilFrequencia();
//    	System.out.println(frequencia.getDatasFeriadosOrdinarios20a20(21, null, 04, 2014));
//    	
//    	System.out.println(frequencia.diasDaSemana(04, 2014, 21));
//    	frequencia.getFrequenciaMesAnterior(03, 2014, 39);
    	for(Frequencia f :frequencia.getFrequenciaMesAnterior(03, 2014, 39)){
    		System.out.println(f.getData() + " | " + f.getPresenca());
    	}

}
}
