/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.relatorios;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Gilmar
 */
public class UtilDatas {
    
    
    public static Date FormatoData(String data){
        String[] trim = data.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(new Integer(trim[2]), new Integer(trim[1]), new Integer(trim[0]));
        Date date = new Date(calendar.getTimeInMillis());
        return date;
        
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
