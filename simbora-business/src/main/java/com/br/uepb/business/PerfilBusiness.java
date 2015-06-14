package com.br.uepb.business;

import java.util.ArrayList; 
import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.ReviewCaronaDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaInteresseDomain;
import com.br.uepb.domain.ReviewCaronasDomain;
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

	List<CaronaInteresseDomain> interessesCaronas = CaronaInteresesBusiness
			.getInteresseCaronas();
	
	UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
	CaronaDaoImp caronaDaoImp = new CaronaDaoImp();
	SolicitacaoVagasDaoImp solicitacaoVagasDaoImp = new SolicitacaoVagasDaoImp();
	ReviewCaronaDaoImp reviewCaronaDaoImp = new ReviewCaronaDaoImp();
	
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
			for (String idCarona : reviewCaronaDaoImp.getCaronasSegurasTranquilas()) {
				if (CaronaBusiness.ehMotorista(login, idCarona)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("caronas que não funcionaram")) {
			int caron = 0;
			String caronas = "";
			for (String idCarona : reviewCaronaDaoImp.getCaronasNaoFuncionaram()) {
				if (CaronaBusiness.ehMotorista(login, idCarona)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("faltas em vagas de caronas")) {
			int caron = 0;
			for (String idUsuario : reviewCaronaDaoImp.getFaltaramNasVagas()) {
				if (idUsuario.equals(login)) {
					caron++;
				}
			}
			return caron + "";
		}

		if (atributo.equals("presenças em vagas de caronas")) {
			int caron = 0;
			for (String idUsuario : reviewCaronaDaoImp.getPresentesNasVagas()) {
				if (idUsuario.equals(login)) {
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
			ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("faltouNaVaga");
			if(linha!=null){
				linha.setFaltouNaVaga(loginCaroneiro);
				reviewCaronaDaoImp.update(linha);
			}else{
				linha = new ReviewCaronasDomain();
				linha.setFaltouNaVaga(loginCaroneiro);
				reviewCaronaDaoImp.save(linha);
			}
			return;
		}

		if (review.equals("não faltou")) {
			ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("presenteNaVaga");
			if(linha!=null){
				linha.setPresenteNaVaga(loginCaroneiro);
				reviewCaronaDaoImp.update(linha);
			}else{
				linha = new ReviewCaronasDomain();
				linha.setPresenteNaVaga(loginCaroneiro);
				reviewCaronaDaoImp.save(linha);
			}
			return;
		} else if (review.equals("não funcionou")) {
			ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("caronaNaoFuncionou");
			if(linha!=null){
				linha.setCaronaNaoFuncionou(loginCaroneiro);
				reviewCaronaDaoImp.update(linha);
			}else{
				linha = new ReviewCaronasDomain();
				linha.setCaronaNaoFuncionou(loginCaroneiro);
				reviewCaronaDaoImp.save(linha);
			}
			return;
		} else if (review.equals("funcionou")) {
			ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("caronaSeguraTranquila");
			if(linha!=null){
				linha.setCaronaSeguraTranquila(loginCaroneiro);
				reviewCaronaDaoImp.update(linha);
			}else{
				linha = new ReviewCaronasDomain();
				linha.setCaronaSeguraTranquila(loginCaroneiro);
				reviewCaronaDaoImp.save(linha);
			}
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
				ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("caronaSeguraTranquila");
				if(linha!=null){
					linha.setCaronaSeguraTranquila(idCaroneiro);
					reviewCaronaDaoImp.update(linha);
				}else{
					linha = new ReviewCaronasDomain();
					linha.setCaronaSeguraTranquila(idCaroneiro);
					reviewCaronaDaoImp.save(linha);
				}
			} else if (review.equals("não funcionou")) {
				ReviewCaronasDomain linha= reviewCaronaDaoImp.getIdCampoVazio("caronaNaoFuncionou");
				if(linha!=null){
					linha.setCaronaNaoFuncionou(idCaroneiro);
					reviewCaronaDaoImp.update(linha);
				}else{
					linha = new ReviewCaronasDomain();
					linha.setCaronaNaoFuncionou(idCaroneiro);
					reviewCaronaDaoImp.save(linha);
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
		String msgem = "[", idUsuario ="";
		CaronaInteresseDomain caronaInteresse = new CaronaInteresseDomain();
		CaronaDomain carona = new CaronaDomain(); 
		UsuarioDomain usuario = new UsuarioDomain();

		// Pegar a carona do passageiro 
		for (CaronaInteresseDomain caronaInteresseDomain : interessesCaronas) {
			if (caronaInteresseDomain.getIdSessao().equals(idSessao)) {
				caronaInteresse = caronaInteresseDomain;
			}
		}
		
		for (SessaoDomain sessao : SessaoBusiness.getSessoes()) {
			if (sessao.getIdSessao().equals(caronaInteresse.getIdSessao())) {
				idUsuario = sessao.getIdUsuario();
			}
		}
		
		for (CaronaDomain car : CaronaBusiness.caronas) {
			if (caronaInteresse.getOrigem().equals(car.getLocalDeOrigem())
					&& caronaInteresse.getDestino().equals(
							car.getLocalDeDestino())
					&& caronaInteresse.getData().equals(car.getData())) {
				idSessao = car.getIdSessao();
				System.out.println("Carona: "+ caronaInteresse.getData() 
						+", "+ caronaInteresse.getOrigem());
		
				for (UsuarioDomain user : UsuarioBusiness.getUsuarios()) {
					if (user.getLogin().equals(car.getIdSessao())) {
						usuario = user;
					}
				}
				
				msgem += "Carona cadastrada no dia "
						+ car.getData() + ", ás "
						+ car.getHorarioDeSaida()
						+ " de acordo com os seus interesses registrados. Entrar em contato com "
						+ usuario.getEmail();
			}
		}
		return msgem + "]";
	}
}
