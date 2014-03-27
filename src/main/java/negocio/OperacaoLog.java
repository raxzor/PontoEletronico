/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import beans.Funcionario;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Gilmar
 */
public class OperacaoLog {
    private String descricao;
    private String operacao;
    private Funcionario funcionario;
    private Timestamp data;
    private Integer id;
    
    public OperacaoLog() {
    }

    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
