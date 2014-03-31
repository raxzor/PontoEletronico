/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import gui.Administrador;
import gui.ImprimirFrequencias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gilmar
 */
public class TelaGerarRelatoriosHandler implements ActionListener{
    
    Administrador administrador;

    public TelaGerarRelatoriosHandler(Administrador administrador) {
        this.administrador = administrador;
    }
    
    

    public void actionPerformed(ActionEvent e) {
        administrador.dispose();
        ImprimirFrequencias.main(null);
    }
    
}
