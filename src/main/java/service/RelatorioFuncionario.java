/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Calendar;

/**
 *
 * @author Gilmar
 */
public class RelatorioFuncionario {
    
    public Integer QuantidadeDiasUteis(Integer mes, Integer ano){
        Integer quantidadeDiasUteis = 0;
        Calendar c = Calendar.getInstance();
        
        c.getActualMaximum(Calendar.FEBRUARY);
        return quantidadeDiasUteis;
    }
    
}
