/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.relatorios;

import beans.Funcionario;
import dao.FrequenciaDao;
import dao.FuncionarioDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gilmar
 */
public class UtilFrequencia {
    
    private Integer totalDiasUteis = 0;

    public Integer getTotalDiasUteis(Integer mes, Integer ano) {
      Integer x = 0;
           
      x = (this.getMaximoDias(mes, ano) - this.getFeriadosOrdinarios(mes, ano));
      
      return x;
    }
    
    public Integer getFeriadosOrdinarios(Integer mes, Integer ano){
        
      Integer diaSemanaComecaMes = this.diasDaSemana(mes, ano);
      
      Map<Integer, Integer> dias = new HashMap<Integer, Integer>();
      
      dias.put(1, 9);//Domingo
      dias.put(2, 8);//Segunda
      dias.put(3, 8);//Terça
      dias.put(4, 8);//Quarta
      dias.put(5, 9);//Quinta
      dias.put(6, 10);//Sexta
      dias.put(7, 10);//Sábado
      
      Integer qtdeFeriadosOrdinarios = dias.get(diaSemanaComecaMes);
      
      if((diaSemanaComecaMes == 5)||(diaSemanaComecaMes == 6)) qtdeFeriadosOrdinarios = qtdeFeriadosOrdinarios -1;
      
      if((mes == 2)) {
          qtdeFeriadosOrdinarios = 8;
      }else if((mes == 2)&&(this.RetornarBisexto(ano) == true)){
          if((dias.get(diaSemanaComecaMes) == 1) || (dias.get(diaSemanaComecaMes) == 7)){
              qtdeFeriadosOrdinarios = qtdeFeriadosOrdinarios +1;
          }
      }
      return qtdeFeriadosOrdinarios;
    }
    
    
    
    
  public Boolean RetornarBisexto (Integer ano){
      return new GregorianCalendar().isLeapYear(ano);
  }
  
  public Integer getMaximoDias(Integer mes, Integer ano){
      Integer maximoDias = 0;
      Map<Integer, Integer> meses = new HashMap<Integer, Integer>();
      meses.put(1, 31);
      meses.put(2, 28);
      meses.put(3, 31);
      meses.put(4, 30);
      meses.put(5, 31);
      meses.put(6, 30);
      meses.put(7, 31);
      meses.put(8, 31); 
      meses.put(9, 30);
      meses.put(10,31);
      meses.put(11,30);
      meses.put(12,31);
      maximoDias = meses.get(mes);
      if((RetornarBisexto(ano) == true) && (mes == 2)) {
         maximoDias = maximoDias + 1;
      }
      return maximoDias;
      
  }
    
  
    public static void main(String[] args) {
        UtilFrequencia util = new UtilFrequencia();
//                System.out.println(util.getMaximoDias(2, 2001));
//            System.out.println(util.diasDaSemana(Integer.SIZE, Integer.SIZE));
        
        System.out.println(util.getTotalDiasUteis(01, 2014));
//        Calendar c = Calendar.getInstance();
//        System.out.println(c.getActualMaximum(Calendar.MONTH));
        
    }
    
    public Integer diasDaSemana(Integer mes, Integer ano) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        mes = mes-1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, 01);
        Integer diaDaSemana = 0;
        gregorianCalendar.setTimeInMillis(calendar.getTimeInMillis());
        diaDaSemana = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        return diaDaSemana;
    }
    
    public Double desconto(Integer idFuncionario, Integer mes, Integer ano) throws SQLException{
        Double valorDesconto = 0D;
//        this.getTotalDiasUteis(mes, ano);
        FrequenciaDao frequenciaDao = new FrequenciaDao();
        
        
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        funcionario = funcionarioDao.getfuncionario(idFuncionario);
        
        Double diaria = funcionario.getSalario() / this.getTotalDiasUteis(mes, ano);
        System.out.println("Total de Dias Úteis = " + this.getTotalDiasUteis(mes, ano));
        System.out.println("Valor do Desconto do INSS = " + funcionario.getSalario()*0.08);
        System.out.println("Dias Trabalhados = " + frequenciaDao.getTotalFaltas(mes, ano, idFuncionario, Boolean.TRUE));
        System.out.println("Total Faltas = " + frequenciaDao.getTotalFaltas(mes, ano, idFuncionario, Boolean.FALSE));
        System.out.println("Salario Bruto = " + funcionario.getSalario());
//        System.out.println("diaria = " + diaria);
        
        valorDesconto = diaria * frequenciaDao.getTotalFaltas(mes, ano, idFuncionario, Boolean.FALSE);
        System.out.println("Valor do Desconto por Faltas = " + valorDesconto);
        System.out.println("Salário Líquido = " + (funcionario.getSalario() - (funcionario.getSalario()*0.08 + valorDesconto)));
        return valorDesconto;
    }
    
}
