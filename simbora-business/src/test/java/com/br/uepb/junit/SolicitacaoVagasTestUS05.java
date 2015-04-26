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

public class SolicitacaoVagasTestUS05 {

	UsuarioBusiness usuario;
	CaronaBusiness carona;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitacao;
	SolicitacaoPontoDeEncontroBusiness solicitarPonto;

	@Before
	public void test() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitacao = new SolicitacaoVagasBusiness();
		solicitarPonto = new SolicitacaoPontoDeEncontroBusiness();
	}

	@Test
	public void criarUsuario() {
		usuario.zerarSistema();
		carona.zerarSistema();
		solicitacao.zerarSistema();
		solicitarPonto.zerarSistema();
		sessao.getSessoes().clear();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}

		// Caddastrar Caronas
		// Carona1ID
		try {
			carona.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013",
					"14:00", "4");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Carona2D
		try {
			carona.cadastrarCarona("mark", "São Francisco", "Palo Alto",
					"12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Carona3ID		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"01/06/2013", "12:00", "1");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Carona4ID		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"02/06/2013", "14:00", "3");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Carona5ID
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"03/06/2013", "16:00", "2");
		} catch (CaronaException e) {
			e.getMessage();
		}
		// Carona6ID		
		try {
			carona.cadastrarCarona("mark", "Leeds", "Londres", "10/02/2013",
					"10:00", "3");
		} catch (CaronaException e) {
			e.getMessage();
		}

		// Encerrar a sessao de Mark
		sessao.encerrarSessao("mark");

		usuario.criarUsuario("bill", "billz@o", "William Henry Gates III",
				"Medina, Washington", "billzin@msn.com");

		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			e.getMessage();
		}

		solicitacao.solicitarVaga("bill", "3");

		try {
			assertEquals("Campina Grande",
					solicitarPonto.getAtributoSolicitacao("0V", "origem"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("João Pessoa",
					solicitarPonto.getAtributoSolicitacao("0V", "destino"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitarPonto.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("William Henry Gates III",
					solicitarPonto.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}
		
		solicitacao.aceitarSolicitacao("mark", "0PE");
		
		try {
			assertEquals("3", carona.getAtributoCarona("3", "vagas"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Requisitar vaga na carona.
		solicitacao.solicitarVaga("bill", "4");
		
		try {
			assertEquals("Campina Grande", solicitarPonto.getAtributoSolicitacao("1V", "origem"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("João Pessoa", solicitarPonto.getAtributoSolicitacao("1V", "destino"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("Mark Zuckerberg", solicitarPonto.getAtributoSolicitacao("1V", "Dono da carona"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("William Henry Gates III", solicitarPonto.getAtributoSolicitacao("1V", "Dono da solicitacao"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Rejeitar requisicao
		try {
			solicitacao.rejeitarSolicitacao("mark", "1V");
		} catch (Exception e) {
			e.getMessage();
		}
		
		try {
			assertEquals("2", carona.getAtributoCarona("4", "vagas"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Tentar rejeitar novamente a requisição
		try {
			solicitacao.rejeitarSolicitacao("mark", "1V");
		} catch (Exception e) {
			assertEquals("Solicitação inexistente", e.getMessage());
		}
		
		try {
			assertEquals("2", carona.getAtributoCarona("4", "vagas"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		usuario.encerrarSistema();
	}
}
