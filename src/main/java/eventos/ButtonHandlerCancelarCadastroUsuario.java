/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.NovoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class ButtonHandlerCancelarCadastroUsuario implements ActionListener {

    NovoUsuario novoUsuario;
    public ButtonHandlerCancelarCadastroUsuario(NovoUsuario novoUsuario){
        this.novoUsuario = novoUsuario;
    }
    public void actionPerformed(ActionEvent e) {
        novoUsuario.dispose();
        Administrador.main(null);
    }
    
    
}
