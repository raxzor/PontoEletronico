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
public class ButtonHandlerVoltarPesquisarUsuario implements ActionListener {
    
    ProcurarUsuario procurarUsuario;
    public ButtonHandlerVoltarPesquisarUsuario(ProcurarUsuario procurarUsuario){
         this.procurarUsuario = procurarUsuario;
    }
    
    public void actionPerformed(ActionEvent e) {
     procurarUsuario.dispose();
     Administrador.main(null);
    }
     
}
