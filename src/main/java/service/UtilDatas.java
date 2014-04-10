/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Gilmar
 */
public class UtilDatas {
    
    
    public static Date FormatoData(String data){
        String[] trim = data.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(new Integer(trim[2]), ((new Integer(trim[1])) - 1), new Integer(trim[0]));
        Date date = new Date(calendar.getTimeInMillis());
        return date;
        
    }
    
    public static String getTurno(Integer indicador){
    	if(indicador == 0){
    		return "manhã";
    	}else{
    		return "tarde";
    	}
    }
    
    public static String getTurno(Timestamp hora){
    	Calendar c = Calendar.getInstance();
    	Calendar saidaManha = Calendar.getInstance();
    	Calendar entradatarde = Calendar.getInstance();
    	Calendar saidaTarde = Calendar.getInstance();
    	c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 07, 00, 00);
    	saidaManha.set(saidaManha.get(Calendar.YEAR), saidaManha.get(Calendar.MONTH), saidaManha.get(Calendar.DAY_OF_MONTH), 12, 59, 59);
    	entradatarde.set(entradatarde.get(Calendar.YEAR), entradatarde.get(Calendar.MONTH), entradatarde.get(Calendar.DAY_OF_MONTH), 13, 00, 00);
    	saidaTarde.set(saidaTarde.get(Calendar.YEAR), saidaTarde.get(Calendar.MONTH), saidaTarde.get(Calendar.DAY_OF_MONTH), 17, 59, 59);
    	if((hora.after(new Timestamp(c.getTimeInMillis()))) && (hora.before(new Timestamp(saidaManha.getTimeInMillis())))){
    		return "M";
    	}else if((hora.after(new Timestamp(entradatarde.getTimeInMillis()))) && (hora.before(new Timestamp(saidaTarde.getTimeInMillis())))){
    		return "T";
    	}else{
    		return "";
    	}
    }
    
    public static String FormatoDataMesAno(String data){
        String[] trim = data.split("/");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(new Integer(trim[2]), ((new Integer(trim[1])) - 1), new Integer(trim[0]));
//        Date date = new Date(calendar.getTimeInMillis());
        Integer mes  = new Integer(trim[0]);
        Integer ano = new Integer(trim[1]);
        return mes.toString() + "/" + ano.toString();
        
    }
    
    public static Integer[] FormatoDataMesAnoInteger(String data){
        String[] trim = data.split("/");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(new Integer(trim[2]), ((new Integer(trim[1])) - 1), new Integer(trim[0]));
//        Date date = new Date(calendar.getTimeInMillis());
        Integer mes  = new Integer(trim[0]);
        Integer ano = new Integer(trim[1]);
        
        Integer[] arrayInteger = new Integer[2];
        arrayInteger[0] = new Integer(trim[0]);
        arrayInteger[1] = new Integer(trim[1]);
        return arrayInteger;
        
    }
    
    public static String DateToString(Date data){
        String dataString = new String();
        
        String[] trim = data.toString().split("-");
        
        dataString = trim[2] + "/" + trim[1] + "/" + trim[0];
        
        return dataString;
    }
    public static String DateToString(String data){
        String dataString = new String();
        
        String[] trim = data.split("-");
        
        dataString = trim[2] + "/" + trim[1] + "/" + trim[0];
        
        return dataString;
    }
}
