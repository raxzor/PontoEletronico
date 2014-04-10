/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.FrequenciaDao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import service.UtilDatas;
import beans.Frequencia;

/**
 *
 * @author Gilmar
 */
public class FachadaFrequencia {
    
    public Boolean verificarFrequenciaDia(Integer idFuncionario) throws SQLException{
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        Date ultimaFrequencia = frequenciaDao.ultimaFrequenciaRegistrada(idFuncionario);
//        System.out.println(ultimaFrequencia);
        Date dataCorrente = new Date(Calendar.getInstance().getTimeInMillis());
        Boolean retorno = true;
        if((ultimaFrequencia == null) || (!ultimaFrequencia.toString().equals(dataCorrente.toString()))){
            retorno = false;
        }
//        System.out.println(retorno);
        return retorno;
    }
    
    public boolean verificarFrequenciaTurno(Integer idFuncionario, Date dia) throws SQLException{
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        List<String> ultimaFrequencia = frequenciaDao.frequenciasDia(idFuncionario, dia);
        if(ultimaFrequencia.contains(UtilDatas.getTurno(new Timestamp(Calendar.getInstance().getTimeInMillis())))){
        	return true;
        }else{
        	return false;
        }
    }
}
