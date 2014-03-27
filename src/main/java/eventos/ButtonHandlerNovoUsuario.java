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
public class ButtonHandlerNovoUsuario implements ActionListener{

    Administrador administrador;
    public ButtonHandlerNovoUsuario(Administrador administrador){
        this.administrador = administrador;
    }
    public void actionPerformed(ActionEvent e) {
        administrador.dispose();
        NovoUsuario.main(null);
       
    }
    
}
