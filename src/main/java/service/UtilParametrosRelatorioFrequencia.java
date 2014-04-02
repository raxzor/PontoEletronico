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
public class UtilParametrosRelatorioFrequencia {
    private String dias_uteis;
    private String dias_trabalhados;
    private String total_faltas;
    private String salario_bruto;
    private String desconto_por_faltas;
    private String percentual_desconto_inss;
    private String percentual_desconto_ir;
    private String desconto_inss;
    private String desconto_ir;
    private String salario_liquido;
    private String titulo_pagina;
    private String administrador;

    public UtilParametrosRelatorioFrequencia(String dias_uteis, String dias_trabalhados, String total_faltas, String salario_bruto, String desconto_por_faltas, String percentual_desconto_inss, String percentual_desconto_ir, String desconto_inss, String desconto_ir, String salario_liquido, String titulo_pagina, String administrador) {
        this.dias_uteis = dias_uteis;
        this.dias_trabalhados = dias_trabalhados;
        this.total_faltas = total_faltas;
        this.salario_bruto = salario_bruto;
        this.desconto_por_faltas = desconto_por_faltas;
        this.percentual_desconto_inss = percentual_desconto_inss;
        this.percentual_desconto_ir = percentual_desconto_ir;
        this.desconto_inss = desconto_inss;
        this.desconto_ir = desconto_ir;
        this.salario_liquido = salario_liquido;
        this.titulo_pagina = titulo_pagina;
        this.administrador = administrador;
    }
    
    
    
    private static Map<String, Object> p = new HashMap<String, Object>();
    
    public Map<String, Object> getparametros(){
        p.put("desconto_inss", desconto_inss);
        p.put("desconto_ir", desconto_ir);
        p.put("desconto_por_faltas", desconto_por_faltas);
        p.put("dias_trabalhados", dias_trabalhados);
        p.put("dias_uteis", dias_uteis);
        p.put("percentual_desconto_inss", percentual_desconto_inss);
        p.put("percentual_desconto_ir", percentual_desconto_ir);
        p.put("salario_bruto", salario_bruto);
        p.put("salario_liquido", salario_liquido);
        p.put("total_faltas", total_faltas);
        p.put("titulo_pagina", titulo_pagina);
        p.put("administrador", administrador);
        return p;
    }
}
