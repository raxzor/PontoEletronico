/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Gilmar
 */
public class Frequencia implements Comparable<Date> {
	
    private Integer id;
    private Funcionario funcionario;
    private Date data;
    private String turno;
    private Boolean presenca;
    private String justificativa;
    private Timestamp horaSaida;
    private Integer aux;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAux() {
        return aux;
    }

    public void setAux(Integer aux) {
        this.aux = aux;
    }
    
    

    public Timestamp getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Timestamp horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Boolean getPresenca() {
        return presenca;
    }

    public void setPresenca(Boolean presenca) {
        this.presenca = presenca;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public int compareTo(Date o) {
        return this.data.compareTo(o);
    }
    
    
}
