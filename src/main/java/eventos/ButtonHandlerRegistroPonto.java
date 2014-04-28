/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import beans.Frequencia;
import beans.Funcionario;
import dao.FrequenciaDao;
import dao.OperacaoLogDao;
import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import negocio.FachadaFrequencia;
import negocio.OperacaoLog;
import service.LoginInterface;
import service.UtilDatas;

/**
 * 
 * @author Gilmar
 */
public class ButtonHandlerRegistroPonto implements ActionListener {

	Principal principal;

	public ButtonHandlerRegistroPonto(Principal principal) {
		this.principal = principal;

	}

	public void actionPerformed(ActionEvent e) {

		Calendar entradaManha = Calendar.getInstance();
		Calendar corteManha = Calendar.getInstance();

		Calendar entradaTarde = Calendar.getInstance();
		Calendar corteTarde = Calendar.getInstance();

		Calendar corrente = Calendar.getInstance();

		entradaManha.set(corteManha.get(Calendar.YEAR), corteManha.get(Calendar.MONTH), corteManha.get(Calendar.DAY_OF_MONTH), 06, 50, 00);
		corteManha.set(corteManha.get(Calendar.YEAR),
				corteManha.get(Calendar.MONTH),
				corteManha.get(Calendar.DAY_OF_MONTH), 07, 50, 00);

		entradaTarde.set(corteTarde.get(Calendar.YEAR),
				corteTarde.get(Calendar.MONTH),
				corteTarde.get(Calendar.DAY_OF_MONTH), 13, 00, 00);
		corteTarde.set(corteTarde.get(Calendar.YEAR),
				corteTarde.get(Calendar.MONTH),
				corteTarde.get(Calendar.DAY_OF_MONTH), 13, 50, 00);

		if ((corrente.after(entradaManha) && (corrente.before(corteManha))) || ((corrente.after(entradaTarde)) && (corrente.before(corteTarde)))) {

			String login = principal.getLogin();
			String senha = principal.getSenha();
			LoginInterface loginInterface = new LoginInterface();
			Funcionario funcionario = null;
			Frequencia frequencia = new Frequencia();
			FrequenciaDao frequenciaDao = new FrequenciaDao();
			FachadaFrequencia fachadaFrequencia = new FachadaFrequencia();
			String respostaUsuario = "";
			try {
				funcionario = loginInterface.logarUsuario(login, senha);
			} catch (SQLException ex) {
				Logger.getLogger(ButtonHandlerRegistroPonto.class.getName())
						.log(Level.SEVERE, null, ex);
			}
			try {
				if (funcionario != null) {
					Boolean frequenciaDia = fachadaFrequencia.verificarFrequenciaTurno(funcionario.getId(), new Date(Calendar.getInstance().getTimeInMillis()));
					if (frequenciaDia == false) {
						respostaUsuario = "Bom dia "
								+ funcionario.getNome().toString()
								+ ", seu Ponto foi registrado com sucesso!";
						frequencia.setFuncionario(funcionario);
						frequencia.setPresenca(Boolean.TRUE);
						Calendar calendar = Calendar.getInstance();
						frequencia
								.setData(new Date(calendar.getTimeInMillis()));
						frequencia.setTurno(UtilDatas.getTurno(new Timestamp(Calendar.getInstance().getTimeInMillis())));
						try {
							frequenciaDao.inserirFrequencia(frequencia);

							OperacaoLog log = new OperacaoLog();
							log.setData(new Timestamp(System
									.currentTimeMillis()));
							log.setDescricao("Registro de Ponto: "
									+ funcionario.getNome());
							log.setFuncionario(funcionario);
							log.setOperacao("Registro de Ponto");

							OperacaoLogDao operacaoLogDao = new OperacaoLogDao();
							operacaoLogDao.persiste(log);

						} catch (SQLException ex) {
							Logger.getLogger(ButtonHandlerLogin.class.getName())
									.log(Level.SEVERE, null, ex);
						}
					} else {
						respostaUsuario = "Atenção " + funcionario.getNome()
								+ ", seu ponto já foi registrado!";
					}
				} else {
					respostaUsuario = "Erro, Credenciais inválidas!";
				}
			} catch (SQLException ex) {
				Logger.getLogger(ButtonHandlerRegistroPonto.class.getName())
						.log(Level.SEVERE, null, ex);
			}

			e.getActionCommand();
			JOptionPane.showMessageDialog(null, respostaUsuario);
		}else{

		JOptionPane.showMessageDialog(null, "Atenção, horário indisponível para registro de ponto!");
	}
		}

}
