package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;

public class SolicitacaoVagasTest {

	// Tests US04
	UsuarioBusiness usuario;
	CaronaBusiness carona;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitarVaga;
	SolicitacaoPontoDeEncontroBusiness solicitarVagas;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitarVaga = new SolicitacaoVagasBusiness();
		solicitarVagas = new SolicitacaoPontoDeEncontroBusiness();

		usuario.zerarSistema();
		carona.getCaronas().clear();
		sessao.getSessoes().clear();
		solicitarVaga.solicitacoesVagas.clear();
		solicitarVagas.solicitacoes.clear();
	}

	@Test
	public void criarUsuario() {
		usuario.usuarios.clear();
		carona.getCaronas().clear();
		sessao.getSessoes().clear();
		solicitarVaga.solicitacoesVagas.clear();
		solicitarVagas.solicitacoes.clear();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}

		// Cadastro de caronas
		// Cadastro 0
		try {
			carona.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013",
					"14:00", "4");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Cadastro id = 1
		try {
			carona.cadastrarCarona("mark", "São Francisco", "Palo Alto",
					"12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Casdastro id = 2
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"01/06/2013", "12:00", "1");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Casdastro id = 3
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"02/06/2013", "12:00", "3");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Casdastro id = 4
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"04/06/2013", "16:00", "2");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Casdastro id = 5
		try {
			carona.cadastrarCarona("mark", "Leeds", "londres", "10/02/2013",
					"10:00", "3");
		} catch (CaronaException e) {
			e.getMessage();
		}

		sessao.encerrarSessao("mark");

		usuario.criarUsuario("bill", "billz@o", "Willian Henry Gates III",
				"Medina, Washington", "billzin@gmail.com"); // Gmail para Bill
															// foi ...

		// Resposta de Bill
		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			e.getMessage();
		}

		try {
			solicitarVagas.sugerirPontoEncontro("bill", "3",
					"Acude Velho; Hiper Bompreco");
		} catch (Exception e) {
			e.getMessage();
		}

		sessao.encerrarSessao("bill");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}

		try {
			solicitarVagas.responderSugestaoPontoEncontro("mark", "3", "0",
					"Acude Velho;Parque da Crianca");
		} catch (Exception e) {
			e.getMessage();
		}
		sessao.encerrarSessao("mark");

		// Resposta de Bill
		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			e.getMessage();
		}
		// idSolicitacao = 0PE
		solicitarVagas.solicitarVagaPontoEncontro("bill", "3", "Acude Velho");

		try {
			assertEquals("Campina Grande",
					solicitarVagas.getAtributoSolicitacao("0PE", "origem"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("João Pessoa",
					solicitarVagas.getAtributoSolicitacao("0PE", "destino"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitarVagas.getAtributoSolicitacao("0PE",
							"Dono da carona"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitarVagas.getAtributoSolicitacao("0PE",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			e.getMessage();
		}
	}
}
