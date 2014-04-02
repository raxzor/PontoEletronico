/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Funcionario;

/**
 *
 * @author Gilmar
 */
public class UsuarioLogado {

    private static UsuarioLogado u;
    private static Funcionario f;

    private UsuarioLogado() {
        f = new Funcionario();
    }

    public static UsuarioLogado getUsuarioLogado() {
        if (u == null) {
            u = new UsuarioLogado();

        }
        return u;
    }
    
    public Funcionario getFuncionario(){
        return f;
    }

    public static void setFuncionario(Funcionario f) {
        UsuarioLogado.f = f;
    }
    
}
