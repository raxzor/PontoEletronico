/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Gilmar
 */
public class TextFieldHandler implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        String string = "";
        string = e.getSource().toString();
        e.getActionCommand();
        JOptionPane.showMessageDialog(null, string);
    }
    
}
