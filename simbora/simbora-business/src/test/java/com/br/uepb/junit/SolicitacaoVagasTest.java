package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.swing.JOptionPane;

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
	SolicitacaoPontoDeEncontroBusiness solicitarVagas;
	SolicitacaoVagasBusiness solicitacao;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitarVagas = new SolicitacaoPontoDeEncontroBusiness();
		solicitacao = new SolicitacaoVagasBusiness();

		usuario.encerrarSistema();
		carona.getCaronas().clear();
		sessao.getSessoes().clear();
		solicitarVagas.solicitacoes.clear();
	}

	@Test
	public void criarUsuario() {
		usuario.getUsuarios().clear();
		carona.getCaronas().clear();
		sessao.getSessoes().clear();
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
			solicitarVagas.responderSugestaoPontoEncontro("mark", "3", "0PE",
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
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitarVagas.getAtributoSolicitacao("0PE",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("Acude Velho", solicitarVagas.getAtributoSolicitacao(
					"0PE", "Ponto de Encontro"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		sessao.encerrarSessao("bill");

		// Aceitar a requisição
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}

		try {
			solicitarVagas.aceitarSolicitacaoPontoEncontro("mark", "0PE");
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			assertEquals("2",
					solicitarVagas.getAtributoSolicitacao("0PE", "vagas"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		try {
			assertEquals("Acude Velho", solicitarVagas.getAtributoSolicitacao(
					"0PE", "Ponto de Encontro"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		// Abrir sessao de Bill
		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			e.getMessage();
		}

		try {
			solicitarVagas.aceitarSolicitacaoPontoEncontro("bill", "0PE");
		} catch (Exception e) {
			assertEquals("Solicitação inexistente", e.getMessage());
		}
		try {
			assertEquals("2", carona.getAtributoCarona("3", "vagas"));
		} catch (CaronaException e) {
			e.getMessage();
		}

		// Sugerir ponto de encontro
		try {
			solicitarVagas.sugerirPontoEncontro("bill", "4",
					"Acude Velho; Hiper Bompreco");
		} catch (Exception e) {
			e.getMessage();
		}

		// Resposta a requisição
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.getMessage();
		}

		try {
			solicitarVagas.responderSugestaoPontoEncontro("mark", "4", "",
					"Acude Velho;Parque da Crianca");
		} catch (Exception e) {
			e.getMessage();
		}

		// requisitar vaga carona
		try {
			solicitarVagas.desistirRequisicao("bill", "3", "0PE");
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			solicitarVagas.sugerirPontoEncontro("bill", "3", "");
		} catch (Exception e) {
			assertEquals("Ponto Inválido", e.getMessage());
		}

		try {
			solicitarVagas.responderSugestaoPontoEncontro("mark", "3", "0PE",
					"");
		} catch (Exception e) {
			assertEquals("Ponto Inválido", e.getMessage());
		}

		sessao.encerrarSessao("mark");
		sessao.encerrarSessao("bill");

	}

	@Test
	public void todosErros() {
		usuario.getUsuarios().clear();
		carona.getCaronas().clear();
		sessao.getSessoes().clear();
		solicitarVagas.solicitacoes.clear();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e1) {
			e1.getMessage();
		}
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"22/10/2014", "17:00", "3");
		} catch (CaronaException e) {
			e.getMessage();
		}

		usuario.criarUsuario("bill", "billz@o", "Willian Henry Gates III",
				"Medina, Washington", "billzin@gmail.com");

		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			e.getMessage();
		}

		// Test
		try {
			solicitarVagas.sugerirPontoEncontro("bill", "0", " ");
		} catch (Exception e) {
			assertEquals("Ponto Inválido", e.getMessage());
		}
		try {
			solicitarVagas.sugerirPontoEncontro("bill", "0", null);
		} catch (Exception e) {
			assertEquals("Ponto Inválido", e.getMessage());
		}

		try {
			solicitarVagas.responderSugestaoPontoEncontro("bill", "0", "0PE",
					null);
		} catch (Exception e) {
			assertEquals("Ponto Inválido", e.getMessage());
		}

	}

	@Test
	public void zeraSistema() {
		solicitarVagas.encerrarSistema();
	}

	@Test
	public void solicitacoesVagasUS05() {
		usuario.encerrarSistema();
		carona.encerrarSistema();
		sessao.getSessoes().clear();
		solicitarVagas.encerrarSistema();
		solicitacao.encerrarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		// Caddastrar Caronas
		// Carona1ID
		try {
			carona.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013",
					"14:00", "4");
		} catch (CaronaException e) {
			fail();
		}
		// Carona2D
		try {
			carona.cadastrarCarona("mark", "São Francisco", "Palo Alto",
					"12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		// Carona3ID
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"01/06/2013", "12:00", "1");
		} catch (CaronaException e) {
			fail();
		}
		// Carona4ID
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"02/06/2013", "14:00", "3");
		} catch (CaronaException e) {
			fail();
		}
		// Carona5ID
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"03/06/2013", "16:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		// Carona6ID
		try {
			carona.cadastrarCarona("mark", "Leeds", "Londres", "10/02/2013",
					"10:00", "3");
		} catch (CaronaException e) {
			fail();
		}

		// Encerrar a sessao de Mark
		sessao.encerrarSessao("mark");

		usuario.criarUsuario("bill", "billz@o", "William Henry Gates III",
				"Medina, Washington", "billzin@msn.com");

		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			fail();
		}

		solicitacao.solicitarVaga("bill", "3");

		try {
			assertEquals("Campina Grande",
					solicitarVagas.getAtributoSolicitacao("0V", "origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitarVagas.getAtributoSolicitacao("0V", "destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitarVagas.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("William Henry Gates III",
					solicitarVagas.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		solicitacao.aceitarSolicitacao("mark", "0PE");

		try {
			assertEquals("3", carona.getAtributoCarona("3", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		// Requisitar vaga na carona.
		solicitacao.solicitarVaga("bill", "4");

		try {
			assertEquals("Campina Grande",
					solicitarVagas.getAtributoSolicitacao("1V", "origem"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("João Pessoa",
					solicitarVagas.getAtributoSolicitacao("1V", "destino"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("Mark Zuckerberg",
					solicitarVagas.getAtributoSolicitacao("1V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("William Henry Gates III",
					solicitarVagas.getAtributoSolicitacao("1V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		// Rejeitar requisicao
		try {
			solicitacao.rejeitarSolicitacao("mark", "1V");
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("2", carona.getAtributoCarona("4", "vagas"));
		} catch (CaronaException e) {
			fail();
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
			fail();
		}

		usuario.encerrarSistema();
	}

}