package eventos;

import gui.Administrador;
import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.OperacaoLog;
import service.UtilDatas;
import beans.Frequencia;
import beans.Funcionario;
import beans.Log;
import dao.FrequenciaDao;
import dao.FuncionarioDao;
import dao.LogDao;
import dao.OperacaoLogDao;

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
				Calendar calendar = Calendar.getInstance();
				mensagem = "Sua Saída foi Registrada com Sucesso! \n \n "
							+ "Registro de saída às: " + calendar.getTime().toString().substring(11, 20);
				OperacaoLog log = new OperacaoLog();
				log.setData(new Timestamp(calendar.getTimeInMillis()));
				log.setFuncionario(funcionario);
				log.setDescricao(calendar.getTime().toString().substring(11, 20));
				log.setOperacao("Registro de Saída sem Entrada");
				new OperacaoLogDao().persiste(log);
			}else{
				int c1 = 0;
				for (Frequencia frequencia : frequencias) {
					System.out.println("iteracao");
					if (frequencia.getHoraSaida() == null) {
						if(turno.equals("T") && (frequencia.getTurno().equals("T"))){
							frequencia.setHoraSaida(new Timestamp(c.getTimeInMillis()));
							frequenciaDao.atualizarFrequencia(frequencia);
							mensagem = "Sua Saída foi registrada com sucesso! \n \n "
									+ "Registro de Saída às: " + frequencia.getHoraSaida().toString().substring(10, 16);
						}else if(turno.equals("M") && (frequencia.getTurno().equals("M"))){
							frequencia.setHoraSaida(new Timestamp(c.getTimeInMillis()));
							frequenciaDao.atualizarFrequencia(frequencia);
							mensagem = "Sua Saída foi registrada com sucesso! \n \n "
									+ "Registro de Saída às: " + frequencia.getHoraSaida().toString().substring(10, 16);
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
		principal.dispose();
		Principal.main(null);
	}
	

}
