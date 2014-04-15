/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gilmar
 */
public class UtilFieldsRelatorioFrequencia {
    
    private String DATA;
    private String PRESENCA_M;
    private String HORA_SAIDA_M;
    private String PRESENCA_T;
    private String HORA_SAIDA_T;
   
    

    public UtilFieldsRelatorioFrequencia(String dATA, String pRESENCA_M, String hORA_SAIDA_M, String pRESENCA_T, String hORA_SAIDA_T) {
		super();
		DATA = dATA;
		PRESENCA_M = pRESENCA_M;
		HORA_SAIDA_M = hORA_SAIDA_M;
		PRESENCA_T = pRESENCA_T;
		HORA_SAIDA_T = hORA_SAIDA_T;
	}



	public String getDATA() {
		return DATA;
	}



	public void setDATA(String dATA) {
		DATA = dATA;
	}



	public String getPRESENCA_M() {
		return PRESENCA_M;
	}



	public void setPRESENCA_M(String pRESENCA_M) {
		PRESENCA_M = pRESENCA_M;
	}



	public String getHORA_SAIDA_M() {
		return HORA_SAIDA_M;
	}



	public void setHORA_SAIDA_M(String hORA_SAIDA_M) {
		HORA_SAIDA_M = hORA_SAIDA_M;
	}



	public String getPRESENCA_T() {
		return PRESENCA_T;
	}



	public void setPRESENCA_T(String pRESENCA_T) {
		PRESENCA_T = pRESENCA_T;
	}



	public String getHORA_SAIDA_T() {
		return HORA_SAIDA_T;
	}



	public void setHORA_SAIDA_T(String hORA_SAIDA_T) {
		HORA_SAIDA_T = hORA_SAIDA_T;
	}

	
    
    
}
