/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.ProcurarUsuarioFrequencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class ButtonHandlerVoltarProcurarUsuarioFrequencia implements ActionListener{

    ProcurarUsuarioFrequencia procurarUsuarioFrequencia;
    public ButtonHandlerVoltarProcurarUsuarioFrequencia(ProcurarUsuarioFrequencia procurarUsuarioFrequencia){
        this.procurarUsuarioFrequencia = procurarUsuarioFrequencia;
    }
    
    public void actionPerformed(ActionEvent e) {
     procurarUsuarioFrequencia.dispose();
     Administrador.main(null);
    }
    
        
}
