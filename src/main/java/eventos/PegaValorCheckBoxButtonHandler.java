/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Expediente;
import gui.NovoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

/**
 *
 * @author Gilmar
 */
public class PegaValorCheckBoxButtonHandler implements ActionListener {

    NovoUsuario novoUsuario;

    public PegaValorCheckBoxButtonHandler(NovoUsuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public void actionPerformed(ActionEvent e) {
//        JCheckBox[] jcheckboxes = new JCheckBox[5];
//        jcheckboxes = novoUsuario.getJcheckboxes();
//        for(JCheckBox jcb : jcheckboxes){
//            System.out.println(jcb.getText());
//            System.out.println(jcb.isSelected());
//        }
        
        List <Expediente> expedientes = new ArrayList<Expediente>();
            JCheckBox[] jcheckboxes = new JCheckBox[5];
            jcheckboxes = novoUsuario.getJcheckboxes();
            for (int i = 0; i < jcheckboxes.length; i ++) {
                Expediente expediente = new Expediente();
                if(jcheckboxes[i].isSelected()){
                expediente.setDiaSemana(i+2);
                expedientes.add(expediente);
                }
            }
            
            for(Expediente expediente : expedientes){
                System.out.println(expediente.getDiaSemana());
            }
    }
}
