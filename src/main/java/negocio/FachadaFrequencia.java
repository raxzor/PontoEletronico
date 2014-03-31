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
}
