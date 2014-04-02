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
    private String NOME;
    private String PORTARIA;
    private String PRESENCA;
   
    public UtilFieldsRelatorioFrequencia(String DATA, String NOME, String PORTARIA, String PRESENCA) {
        this.DATA = DATA;
        this.NOME = NOME;
        this.PORTARIA = PORTARIA;
        this.PRESENCA = PRESENCA;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getPORTARIA() {
        return PORTARIA;
    }

    public void setPORTARIA(String PORTARIA) {
        this.PORTARIA = PORTARIA;
    }

    public String getPRESENCA() {
        return PRESENCA;
    }

    public void setPRESENCA(String PRESENCA) {
        this.PRESENCA = PRESENCA;
    }
    
    
}
