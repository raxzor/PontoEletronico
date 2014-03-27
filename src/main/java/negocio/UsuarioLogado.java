/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import beans.Funcionario;

/**
 *
 * @author Gilmar
 */
public class UsuarioLogado {
    private static UsuarioLogado usuarioLogado;
    private static Funcionario funcionario;
    
    private UsuarioLogado(){
    }
    
    public static UsuarioLogado getInstancia(){
        if(usuarioLogado == null){
            usuarioLogado = new UsuarioLogado();
        }
        return usuarioLogado;
    }
    
    public Funcionario getUsuarioLogado(){
        return funcionario;
    }
    
    public void setUsuarioLogado(Funcionario funcionario1){
        funcionario = funcionario1;
    }
}
