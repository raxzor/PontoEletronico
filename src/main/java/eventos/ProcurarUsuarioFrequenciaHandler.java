/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.NovoUsuario;
import gui.ProcurarUsuario;
import gui.ProcurarUsuarioFrequencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class ProcurarUsuarioFrequenciaHandler implements ActionListener {
    
    Administrador administrador;
    
    public ProcurarUsuarioFrequenciaHandler(Administrador administrador){
       this.administrador = administrador; 
    }
    public void actionPerformed(ActionEvent e) {
        administrador.dispose();
        ProcurarUsuarioFrequencia.main(null);
    }
    
}
