/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.ListarFrequencia;
import gui.ProcurarUsuarioFrequencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class ButtonHandlerVoltarFrequencia implements ActionListener {
    
    ListarFrequencia listarFrequencia;
    
    
    public ButtonHandlerVoltarFrequencia(ListarFrequencia listarFrequencia){
        this.listarFrequencia = listarFrequencia;
    }
    
    public void actionPerformed(ActionEvent e) {
     listarFrequencia.dispose();
     Administrador.main(null);
    }
    
}
