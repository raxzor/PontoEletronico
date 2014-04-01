package gui;

import beans.Frequencia;
import dao.FrequenciaDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import service.relatorios.UtilFrequencia;

/**
 * Hello world!
 *
 */
public class App 
{
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
        
        String[] a = new String[10];
        
        String[] b = a;
        
        for(String c : b){
            System.out.println(c);
        }
    }
}
