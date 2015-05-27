package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaInteresseDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * Esta classe gerencia o perfil do usuário. Responsável por transmitir as
 * informações relacionadas as atividades dos usuários. Define os métodos de
 * acesso público: visualizarPerfil e getAtributoPerfil
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class PerfilBusiness {

	public final static Logger logger = Logger.getLogger(PerfilBusiness.class);

	public static List<String> caronasSegurasTranquilas = new ArrayList<>();
	public static List<String> caronasNaoFuncionaram = new ArrayList<>();
	public static List<String> faltaramNasVagas = new ArrayList<>();
	public static List<String> presenteNasVagas = new ArrayList<>();
	private List<SolicitacaoVagasDomain> solicitacoesVagas = SolicitacaoVagasBusiness.solicitacoesVagas;
	List<CaronaInteresseDomain> interessesCaronas = CaronaInteresesBusiness
			.getInteresseCaronas();

	/**
	 * Retorna o login do usuario.
	 * 
	 * @param idSessao
	 * @param login
	 * @return
	 * @throws PerfilException
	 */
	public String visualizarPerfil(String idSessao, String login)
			throws PerfilException {

		if (login == null || login.trim().trim().isEmpty()) {
			throw new PerfilException("Login inválido");
		}

		if (idSessao == null || idSessao.trim().isEmpty()) {
			throw new PerfilException("Sessão inválida");
		}

		for (UsuarioDomain usuario : new UsuarioBusiness().usuarios) {
			if (usuario.getLogin().equals(login)) {
				return usuario.getLogin();
			}
		}
		throw new PerfilException("Login inválido");
	}

	/**
	 * Retorna todas as informações solicitadas pelo parametro.
	 * 
	 * @see getAtributo
	 * @param login
	 * @param atributo
	 *            (historico das caronas, historico de vagas em caronas, caronas
	 *            seguras e tranquilas, caronas que não funcionaram, faltas em
	 *            vagas de caronas, presenças em vagas de caronas)
	 * @return informações dependendo do parametro.
	 * @throws PerfilException
	 *
	 */
	public String getAtributoPerfil(String login, String atributo)
			throws PerfilException {
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new PerfilException("Atributo inválido");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new PerfilException("Login inválido");
		}

		for (UsuarioDomain usuario : new UsuarioBusiness().usuarios) {
			if (usuario.getLogin().equals(login)) {
				return getAtributo(login, atributo);
			}
		}
		throw new UsuarioException("Login inválido");

	}

	/**
	 * Este metodo retorna historico das caronas, historico de vagas em caronas,
	 * caronas seguras e tranquilas, caronas que não funcionaram, faltas em
	 * vagas de caronas ou presenças em vagas de caronas.
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws PerfilException
	 */
	private String getAtributo(String login, String atributo)
			throws PerfilException {

		if (atributo.equals("historico de caronas")) {
			String caron = "[";
			for (CaronaDomain carona : CaronaBusiness.getCaronas()) {
				if (carona.getIdSessao().equals(login)) {
					caron += carona.getIdCarona();
				}
			}
			return caron + "]";
		}

		if (atributo.equals("historico de vagas em caronas")) {
			boolean flag = true;// indica se a quantidade de ids é 0
			String caron = "[";
			for (SolicitacaoVagasDomain solicitacaoVagas : SolicitacaoVagasBusiness.solicitacoesVagas) {
				if (solicitacaoVagas.getIdSessao().equals(login)) {
					if (!flag) {
						caron += ",";
					}
					caron += solicitacaoVagas.getIdCarona();
					flag = false;
				}
			}
			return caron + "]";
		}

		if (atributo.equals("caronas seguras e tranquilas")) {
			int caron = 0;
			for (String idCarona : caronasSegurasTranquilas) {
				if (CaronaBusiness.ehMotorista(login, idCarona)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("caronas que não funcionaram")) {
			int caron = 0;
			String caronas = "";
			for (String idCarona : caronasNaoFuncionaram) {
				if (CaronaBusiness.ehMotorista(login, idCarona)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("faltas em vagas de caronas")) {
			int caron = 0;
			for (String idUsuario : faltaramNasVagas) {
				if (idUsuario.equals(login)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("presenças em vagas de caronas")) {
			int caron = 0;
			for (String idUsuario : presenteNasVagas) {
				if (idUsuario.equals(login)) {
					caron++;
				}
			}
			return caron + "";
		}

		return new UsuarioBusiness().getAtributoUsuario(login, atributo);
	}

	public void zerarSistema() {
		caronasSegurasTranquilas.clear();
		caronasNaoFuncionaram.clear();
		faltaramNasVagas.clear();
		presenteNasVagas.clear();
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws PerfilException {
		/* precisa validar os atributos recebidos neste métodos */
		if (!SolicitacaoVagasBusiness.ehCaroneiro(loginCaroneiro, idCarona)) {
			throw new PerfilException("Usuário não possui vaga na carona.");
		}

		if (review.equals("faltou")) {
			faltaramNasVagas.add(loginCaroneiro);
			return;
		}

		if (review.equals("não faltou")) {
			presenteNasVagas.add(loginCaroneiro);
			return;
		} else if (review.equals("não funcionou")) {
			caronasNaoFuncionaram.add(idCarona);
			return;
		} else if (review.equals("funcionou")) {
			caronasNaoFuncionaram.add(idCarona);
			return;
		}

		throw new PerfilException("Opção inválida.");

	}

	public void reviewCarona(String idSessao, String idCaroneiro, String review)
			throws PerfilException {

		if (!ehCaroneiro(idSessao, idCaroneiro)) {
			throw new PerfilException("Usuário não possui vaga na carona.");
		} else {
			if (review.equals("segura e tranquila")) {
				caronasSegurasTranquilas.add(idCaroneiro);
			} else if (review.equals("não funcionou")) {
				caronasNaoFuncionaram.add(idCaroneiro);
			} else {
				throw new PerfilException("Opção inválida.");
			}
		}
	}

	private boolean ehCaroneiro(String login, String caroneiro) {
		for (SolicitacaoVagasDomain solicitacoes : solicitacoesVagas) {
			if (caroneiro.equals(solicitacoes.getIdCarona())
					&& login.equals(solicitacoes.getIdSessao())) {
				return true;
			}
		}
		return false;
	}

	public String verificarMensagensPerfil(String idSessao) {
		String msgem = "[";
		UsuarioDomain usuario = new UsuarioDomain();
		String id = null;

		CaronaInteresseDomain caronaInteresse = new CaronaInteresseDomain();
		try {
			CaronaDomain car = null;
			caronaInteresse = pegarInteresseCarona(idSessao, caronaInteresse);
			id = pegarIdUsuario(id, caronaInteresse);
			usuario = pegarUsuario(usuario, id);

			for (CaronaDomain carona : CaronaBusiness.caronas) {
				if (carona.getLocalDeOrigem().equals(
						caronaInteresse.getOrigem())
						&& carona.getLocalDeDestino().equals(
								caronaInteresse.getDestino())) {
					car = new CaronaDomain();
					car = carona;
				}
			}
			try {
			msgem += "Carona cadastrada no dia "
					+ car.getData()
					+ ", ás "
					+ car.getLocalDeOrigem()
					+ " de acordo com os seus interesses registrados. Entrar em contato com "
					+ usuario.getEmail();
			}catch (Exception e){}
			return msgem + "]";
		} catch (Exception e) {
			return "[]";
		}

	}

	private UsuarioDomain pegarUsuario(UsuarioDomain usuario, String id) {
		for (UsuarioDomain user : UsuarioBusiness.usuarios) {
			if (user.getLogin().equals(id)) {
				usuario = user;
			}
		}
		return usuario;
	}

	private String pegarIdUsuario(String id,
			CaronaInteresseDomain caronaInteresse) {
		for (SessaoDomain sessao : SessaoBusiness.getSessoes()) {
			if (caronaInteresse.getIdSessao().equals(sessao.getIdSessao())) {
				id = sessao.getIdUsuario();
			}
		}
		return id;
	}

	private CaronaInteresseDomain pegarInteresseCarona(String idSessao,
			CaronaInteresseDomain caronaInteresse) {
		for (CaronaInteresseDomain caronaInteresseDomain : interessesCaronas) {
			if (caronaInteresseDomain.getIdSessao().equals(idSessao)) {
				caronaInteresse = caronaInteresseDomain;
			}
		}
		return caronaInteresse;
	}
}
