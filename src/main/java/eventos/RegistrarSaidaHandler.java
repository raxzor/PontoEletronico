package eventos;

import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import service.UtilDatas;
import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import dao.FuncionarioDao;

public class RegistrarSaidaHandler implements ActionListener {

	Principal principal;
	
	public RegistrarSaidaHandler(Principal principal){
		this.principal = principal;
	}
	
	public void actionPerformed(ActionEvent e) {
		Funcionario funcionario;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		FrequenciaDao frequenciaDao = new FrequenciaDao();
		Calendar c = Calendar.getInstance();
		List<Frequencia> frequencias = new ArrayList<Frequencia>();
		String turno = UtilDatas.getTurno(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		String mensagem = new String();
		try {
			funcionario = funcionarioDao.getfuncionario(principal.getLogin(), principal.getSenha());
			frequencias = frequenciaDao.getFrequenciaFuncionario((c.get(Calendar.MONTH)), c.get(Calendar.YEAR), c.get(Calendar.DAY_OF_MONTH), funcionario.getId());
			if(frequencias.isEmpty()){
				mensagem = "Atenção, não existe nenhuma entrada registrada para este turno!";
			}else {
				int c1 = 0;
//				System.out.println(frequencias);
				for (Frequencia frequencia : frequencias) {
					System.out.println("iteracao");
					if (frequencia.getHoraSaida() == null) {
						if(turno.equals("T") && (frequencia.getTurno().equals("T"))){
							frequencia.setHoraSaida(new Timestamp(c.getTimeInMillis()));
							frequenciaDao.atualizarFrequencia(frequencia);
							mensagem = "Saída registrada às " + frequencia.getHoraSaida().toString().substring(10, 16);
						}else if(turno.equals("M") && (frequencia.getTurno().equals("M"))){
							frequencia.setHoraSaida(new Timestamp(c.getTimeInMillis()));
							frequenciaDao.atualizarFrequencia(frequencia);
							mensagem = "Saída registrada às " + frequencia.getHoraSaida().toString().substring(10, 16);
						}
					}
				}
				
				if(mensagem.isEmpty()){
					mensagem = "Atenção, todos os turnos já foram encerrados!";
				}
			}
	           
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, mensagem);                                       
	}

}
