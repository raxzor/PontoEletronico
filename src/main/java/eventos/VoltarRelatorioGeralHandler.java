package eventos;

import gui.Administrador;
import gui.ImprimirFrequencias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoltarRelatorioGeralHandler implements ActionListener {

	ImprimirFrequencias imprimirFrequencias;
	
	public VoltarRelatorioGeralHandler(ImprimirFrequencias imprimirFrequencias){
		this.imprimirFrequencias = imprimirFrequencias;
	}
	
	public void actionPerformed(ActionEvent e) {
		imprimirFrequencias.dispose();
		Administrador.main(null);
		
	}

}
