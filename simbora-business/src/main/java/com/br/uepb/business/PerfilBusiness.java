package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.PerfilException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.CaronaInteresseDaoImpl;
import com.br.uepb.dao.impl.ReviewCaronasDaoImp;
import com.br.uepb.dao.impl.ReviewVagasCaronaDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaInteresseDomain;
import com.br.uepb.domain.ReviewCaronasDomain;
import com.br.uepb.domain.ReviewVagasCaronaDomain;
import com.br.uepb.domain.ReviewDomain;
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

	private UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
	private CaronaDaoImp caronaDaoImp = new CaronaDaoImp();
	private SolicitacaoVagasDaoImp solicitacaoVagasDaoImp = new SolicitacaoVagasDaoImp();
	private ReviewVagasCaronaDaoImp reviewVagasCaronaDaoImp = new ReviewVagasCaronaDaoImp();
	private ReviewCaronasDaoImp reviewCaronasDaoImp = new ReviewCaronasDaoImp();

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

		try {
			UsuarioDomain usuario = usuarioDaoImp.getUsuario(login);
			return usuario.getLogin();
		} catch (Exception e) {
			throw new PerfilException("Login inválido");
		}

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

		try {
			usuarioDaoImp.getUsuario(login);
			return getAtributo(login, atributo);
		} catch (Exception e) {
			throw new PerfilException("Login inválido");
		}

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
			for (CaronaDomain carona : getCaronas()) {
				if (carona.getIdUsuario().equals(login)) {
					caron += carona.getId();
				}
			}
			return caron + "]";
		}

		if (atributo.equals("historico de vagas em caronas")) {
			boolean flag = true;// indica se a quantidade de ids é 0
			String caron = "[";
			for (SolicitacaoVagasDomain solicitacaoVagas : getSolicitacoesVagas()) {
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

			for (ReviewDomain review : reviewCaronasDaoImp.getCaronasSegurasTranquilas()) {
				if (CaronaBusiness.ehMotorista(login, review.getIdAvaliado())) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("caronas que não funcionaram")) {
			int caron = 0;
			for (ReviewDomain review : reviewCaronasDaoImp
					.getCaronasNaoFuncionaram()) {
				if (CaronaBusiness.ehMotorista(login, review.getIdAvaliado())) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("faltas em vagas de caronas")) {
			int caron = 0;
			for (ReviewDomain review : reviewVagasCaronaDaoImp
					.getFaltaramNasVagas()) {
				if (login.equals(review.getIdAvaliado())) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("presenças em vagas de caronas")) {
			int caron = 0;
			for (ReviewDomain review : reviewVagasCaronaDaoImp
					.getPresentesNasVagas()) {
				if (login.equals(review.getIdAvaliado())) {
					caron++;
				}
			}
			return caron + "";
		}

		return new UsuarioBusiness().getAtributoUsuario(login, atributo);
	}

	private List<SolicitacaoVagasDomain> getSolicitacoesVagas() {
		return solicitacaoVagasDaoImp.list();
	}

	private List<CaronaDomain> getCaronas() {
		return caronaDaoImp.list();
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws PerfilException {
		/* precisa validar os atributos recebidos neste métodos */
		if (!ehCaroneiro(loginCaroneiro, idCarona)) {
			throw new PerfilException("Usuário não possui vaga na carona.");
		}
		if (review.equals("faltou")) {
			ReviewVagasCaronaDomain linha = reviewVagasCaronaDaoImp
					.getIdCampoVazio("faltouNaVaga");

			if (linha != null) {
				ReviewDomain avaliacao = new ReviewDomain();
				avaliacao.setIdAvaliado(loginCaroneiro);
				avaliacao.setLogin(idSessao);
				linha.setFaltouNaVaga(avaliacao);
				reviewVagasCaronaDaoImp.update(linha);
			} else {
				linha = new ReviewVagasCaronaDomain();
				linha.setFaltouNaVaga(new ReviewDomain());
				linha.getFaltouNaVaga().setIdAvaliado(loginCaroneiro);
				linha.getFaltouNaVaga().setLogin(idSessao);
				reviewVagasCaronaDaoImp.save(linha);
			}
			return;
		}

		if (review.equals("não faltou")) {
			ReviewVagasCaronaDomain linha = reviewVagasCaronaDaoImp
					.getIdCampoVazio("presenteNaVaga");
			if (linha != null) {
				ReviewDomain avaliacao = new ReviewDomain();
				avaliacao.setIdAvaliado(loginCaroneiro);
				avaliacao.setLogin(idSessao);
				linha.setPresenteNaVaga(avaliacao);
				reviewVagasCaronaDaoImp.update(linha);
			} else {
				linha = new ReviewVagasCaronaDomain();
				linha.setPresenteNaVaga(new ReviewDomain());
				linha.getPresenteNaVaga().setIdAvaliado(loginCaroneiro);
				linha.getPresenteNaVaga().setLogin(idSessao);
				reviewVagasCaronaDaoImp.save(linha);
			}
			return;
		} else if (review.equals("não funcionou")) {
			ReviewVagasCaronaDomain linha = reviewVagasCaronaDaoImp
					.getIdCampoVazio("caronaNaoFuncionou");
			if (linha != null) {
				ReviewDomain avaliacao = new ReviewDomain();
				avaliacao.setIdAvaliado(loginCaroneiro);
				avaliacao.setLogin(idSessao);
				linha.setCaronaNaoFuncionou(avaliacao);
				reviewVagasCaronaDaoImp.update(linha);
			} else {
				linha = new ReviewVagasCaronaDomain();
				linha.setCaronaNaoFuncionou(new ReviewDomain());
				linha.getCaronaNaoFuncionou().setIdAvaliado(loginCaroneiro);
				linha.getCaronaNaoFuncionou().setLogin(idSessao);
				reviewVagasCaronaDaoImp.save(linha);
			}
			return;
		} else if (review.equals("funcionou")) {
			ReviewVagasCaronaDomain linha = reviewVagasCaronaDaoImp
					.getIdCampoVazio("caronaSeguraTranquila");
			if (linha != null) {
				ReviewDomain avaliacao = new ReviewDomain();
				avaliacao.setIdAvaliado(loginCaroneiro);
				avaliacao.setLogin(idSessao);
				linha.setCaronaSeguraTranquila(avaliacao);
				reviewVagasCaronaDaoImp.update(linha);
			} else {
				linha = new ReviewVagasCaronaDomain();
				linha.setCaronaSeguraTranquila(new ReviewDomain());
				linha.getCaronaSeguraTranquila().setIdAvaliado(loginCaroneiro);
				linha.getCaronaSeguraTranquila().setLogin(idSessao);
				reviewVagasCaronaDaoImp.save(linha);
				return;
			}
		}

		throw new PerfilException("Opção inválida.");

	}

	public void reviewCarona(String idSessao, String idCaroneiro, String review)
			throws PerfilException {

		if (!ehCaroneiro(idSessao, idCaroneiro)) {
			throw new PerfilException("Usuário não possui vaga na carona.");
		} else {
			if (review.equals("segura e tranquila")) {
				ReviewCaronasDomain linha = reviewCaronasDaoImp
						.getIdCampoVazio("caronaSeguraTranquila");
				if (linha != null) {
					ReviewDomain avaliacao = new ReviewDomain();
					avaliacao.setIdAvaliado(idCaroneiro);
					avaliacao.setLogin(idSessao);
					linha.setCaronaSeguraTranquila(avaliacao);
					reviewCaronasDaoImp.update(linha);
				} else {
					linha = new ReviewCaronasDomain();
					linha.setCaronaSeguraTranquila(new ReviewDomain());
					linha.getCaronaSeguraTranquila().setIdAvaliado(idCaroneiro);
					linha.getCaronaSeguraTranquila().setLogin(idSessao);
					reviewCaronasDaoImp.save(linha);
				}
			} else if (review.equals("não funcionou")) {
				ReviewCaronasDomain linha = reviewCaronasDaoImp
						.getIdCampoVazio("caronaNaoFuncionou");
				if (linha != null) {
					ReviewDomain avaliacao = new ReviewDomain();
					avaliacao.setIdAvaliado(idCaroneiro);
					avaliacao.setLogin(idSessao);
					linha.setCaronaNaoFuncionou(avaliacao);
					reviewCaronasDaoImp.update(linha);
				} else {
					linha = new ReviewCaronasDomain();
					linha.setCaronaNaoFuncionou(new ReviewDomain());
					linha.getCaronaNaoFuncionou().setIdAvaliado(idCaroneiro);
					linha.getCaronaNaoFuncionou().setLogin(idSessao);
					reviewCaronasDaoImp.save(linha);
				}
			} else {
				throw new PerfilException("Opção inválida.");
			}
		}
	}

	private boolean ehCaroneiro(String login, String caroneiro) {
		for (SolicitacaoVagasDomain solicitacoes : getSolicitacoesVagas()) {
			if (caroneiro.equals(solicitacoes.getIdCarona())
					&& login.equals(solicitacoes.getIdSessao())) {
				return true;
			}
		}
		return false;
	}

	public String verificarMensagensPerfil(String idSessao) {
		String msgem = "[", idUsuario = "";
		CaronaInteresseDomain caronaInteresse = new CaronaInteresseDomain();
		CaronaDomain carona = new CaronaDomain();
		UsuarioDomain usuario = new UsuarioDomain();

		// Pegar a carona do passageiro
		for (CaronaInteresseDomain caronaInteresseDomain : new CaronaInteresseDaoImpl()
				.list()) {
			if (caronaInteresseDomain.getIdSessao().equals(idSessao)) {
				caronaInteresse = caronaInteresseDomain;
			}
		}

		for (SessaoDomain sessao : SessaoBusiness.getSessoes()) {
			if (sessao.getIdSessao().equals(caronaInteresse.getIdSessao())) {
				idUsuario = sessao.getIdUsuario();
			}
		}

		for (CaronaDomain car : new CaronaDaoImp().list()) {
			if (caronaInteresse.getOrigem().equals(car.getOrigem())
					&& caronaInteresse.getDestino().equals(car.getDestino())
					&& caronaInteresse.getData().equals(car.getData())) {
				idSessao = car.getIdUsuario();
				

				for (UsuarioDomain user : new UsuarioDaoImp().list()) {
					if (user.getLogin().equals(car.getIdUsuario())) {
						usuario = user;
					}
				}

				msgem += "Carona cadastrada no dia "
						+ car.getData()
						+ ", ás "
						+ car.getHora()
						+ " de acordo com os seus interesses registrados. Entrar em contato com "
						+ usuario.getEmail();
			}
		}
		return msgem + "]";
	}

	/**
	 * 
	 * @param login
	 * @return todas as caronas criadas pelo usuário.
	 * @throws PerfilException
	 */
	public List<CaronaDomain> getHistoricoCaronas(String login)
			throws PerfilException {

		if (login == null || login.trim().isEmpty()) {
			throw new PerfilException("Login inválido");
		}
		List<CaronaDomain> caronaDomain = new ArrayList<CaronaDomain>();

		for (CaronaDomain carona : getCaronas()) {
			if (carona.getIdUsuario().equals(login)) {
				caronaDomain.add(carona);
			}
		}
		return caronaDomain;
	}

	/**
	 * Retorna uma List das caronas que foram consideradas seguras e tranquilas.
	 * @param login
	 * @return
	 */
	public List<ReviewDomain> getSegurasTransquilas(String login) {
		List<ReviewDomain> lstreview = new ArrayList<ReviewDomain>();

		for (ReviewDomain review : reviewCaronasDaoImp
				.getCaronasSegurasTranquilas()) {
			if (CaronaBusiness.ehMotorista(login, review.getIdAvaliado())) {
				lstreview.add(review);
			}
		}
		return lstreview;
	}

	/**
	 * Retorna uma List das caronas que não deu certo ou não funcionaram.
	 * @param login
	 * @return
	 */
	public List<ReviewDomain> getNaoFunc(String login) {
		List<ReviewDomain> lstreview = new ArrayList<ReviewDomain>();

		for (ReviewDomain review : reviewCaronasDaoImp
				.getCaronasNaoFuncionaram()) {
			if (CaronaBusiness.ehMotorista(login, review.getIdAvaliado())) {
				lstreview.add(review);
			}
		}
		return lstreview;
	}
}
