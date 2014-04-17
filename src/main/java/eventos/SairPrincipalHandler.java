package eventos;

import gui.Administrador;
import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SairPrincipalHandler implements ActionListener{

	Administrador administrador;
	
	public SairPrincipalHandler(Administrador administrador){
		this.administrador = administrador;
	}
	
	public void actionPerformed(ActionEvent e) {
		administrador.dispose();
		Principal.main(null);
		
	}

}
