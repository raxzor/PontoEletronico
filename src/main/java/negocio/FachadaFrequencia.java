/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.FrequenciaDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Gilmar
 */
public class FachadaFrequencia {
    
    public Boolean verificarFrequenciaDia(Integer idFuncionario) throws SQLException{
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        System.out.println(idFuncionario);
        Date ultimaFrequencia = frequenciaDao.ultimaFrequenciaRegistrada(idFuncionario);
        Date dataCorrente = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(dataCorrente);
        System.out.println(ultimaFrequencia);
        Boolean retorno = true;
        if((ultimaFrequencia == null) || (!ultimaFrequencia.toString().equals(dataCorrente.toString()))){
            retorno = false;
        }
        return retorno;
    }
}
