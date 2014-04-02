/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.ProcurarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class ProcurarUsuarioHandler implements ActionListener {
    
    Administrador administrador;
    
    public ProcurarUsuarioHandler(Administrador administrador){
       this.administrador = administrador; 
    }
    public void actionPerformed(ActionEvent e) {
        administrador.dispose();
        ProcurarUsuario.main(null);
    }
    
}
