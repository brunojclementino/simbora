package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class PersistenciaTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness carona;
	SolicitacaoPontoDeEncontroBusiness solicitarPontoVaga;
	SolicitacaoVagasBusiness solicitarVaga;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		carona = new CaronaBusiness();
		solicitarPontoVaga = new SolicitacaoPontoDeEncontroBusiness();
		solicitarVaga = new SolicitacaoVagasBusiness();
		usuario.getUsuarios().clear();
		sessao.getSessoes().clear();
		sessao.getUsuarios().clear();
		carona.getCaronas().clear();
		solicitarPontoVaga.encerrarSistema();
		solicitarVaga.encerrarSistema();
	}

	@Test
	public void criarUsuarios() {
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@acebbok.com");
		usuario.criarUsuario("steve", "5t3v3", "Steve Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");

		try {
			sessao.abrirSessao("steve", "5t3v3");
		} catch (SessaoException e) {
			fail();
		}
		try {
			assertEquals("Steve Paul Jobs",
					usuario.getAtributoUsuario("steve", "nome"));
		} catch (UsuarioException e) {
			assertEquals("Já existe um usuário com este login", e.getMessage());
		}

		try {
			assertEquals("Palo Alto, California",
					usuario.getAtributoUsuario("steve", "endereco"));
		} catch (UsuarioException e) {
			assertEquals("Já existe um usuário com este login", e.getMessage());
		}

		// Mark
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		assertEquals("Mark Zuckerberg",
				usuario.getAtributoUsuario("mark", "nome"));
		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("mark", "endereco"));

		// Cadastrar Caronas
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013",
					"14:00", "4"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			carona.cadastrarCarona("mark", "São Francisco", "Palo Alto",
					"12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			fail();
		}

		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"01/06/2013", "12:00", "1");
		} catch (CaronaException e) {
			fail();
		}

		try {
			carona.cadastrarCarona("steve", "Campina Grande", "João Pessoa",
					"04/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			fail();
		}

		try {
			carona.cadastrarCarona("steve", "Campina Grande", "João Pessoa",
					"20/07/2013", "14:00", "4");
		} catch (CaronaException e) {
			fail();
		}

		try {
			carona.cadastrarCarona("steve", "Leeds", "Londres", "10/02/2013",
					"10:00", "3");
		} catch (CaronaException e) {
			fail();
		}

		sessao.encerrarSessao("mark");

		sessao.encerrarSessao("steve");

		try {
			sessao.abrirSessao("steve", "5t3v3");
		} catch (SessaoException e) {
			fail();
		}

		try {
			solicitarPontoVaga.sugerirPontoEncontro("steve", "1",
					"Acude Velho;Hiper Bompreco");
		} catch (Exception e) {
			fail();
		}

		// Requisitar vaga
		solicitarVaga.solicitarVaga("steve", "1");
		try {
			assertEquals("São Francisco",
					solicitarPontoVaga.getAtributoSolicitacao("0V", "origem"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("Palo Alto",
					solicitarPontoVaga.getAtributoSolicitacao("0V", "destino"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("Mark Zuckerberg",
					solicitarPontoVaga.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("Steve Paul Jobs",
					solicitarPontoVaga.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		// Aceitar Solicitacao
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		solicitarVaga.aceitarSolicitacao("mark", "0V");
		try {
			assertEquals("3", carona.getAtributoCarona("3", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		sessao.encerrarSessao("mark");
		sessao.encerrarSessao("steve");


		// Inicializar o sistemam
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		try {
			sessao.abrirSessao("steve", "5t3v3");
		} catch (SessaoException e) {
			fail();
		}

		assertEquals("Steve Paul Jobs",
				usuario.getAtributoUsuario("steve", "nome"));
		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("steve", "endereco"));
		assertEquals("Mark Zuckerberg",
				usuario.getAtributoUsuario("mark", "nome"));
		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("mark", "endereco"));

		try {
			assertEquals("0", carona.getCaronaUsuario("mark", "1"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Cajazeiras", carona.getAtributoCarona("0", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Patos", carona.getAtributoCarona("0", "destino"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("1", carona.getCaronaUsuario("mark", "2"));
		} catch (Exception e) {
			fail();			
		}
		try {
			assertEquals("São Francisco",
					carona.getAtributoCarona("1", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Palo Alto", carona.getAtributoCarona("1", "destino"));
		} catch (Exception e) {
			fail();
		}
		// Carona 03
		try {
			assertEquals("2", carona.getCaronaUsuario("mark", "3"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Campina Grande",
					carona.getAtributoCarona("2", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("João Pessoa",
					carona.getAtributoCarona("2", "destino"));
		} catch (Exception e) {
			fail();
		}
		// Carona 04
		try {
			assertEquals("3", carona.getCaronaUsuario("steve", "1"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Campina Grande",
					carona.getAtributoCarona("3", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("João Pessoa",
					carona.getAtributoCarona("3", "destino"));
		} catch (Exception e) {
			fail();
		}

		// Carona 05
		try {
			assertEquals("4", carona.getCaronaUsuario("steve", "2"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Campina Grande",
					carona.getAtributoCarona("4", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("João Pessoa",
					carona.getAtributoCarona("4", "destino"));
		} catch (Exception e) {
			fail();
		}

		// Carona 06
		try {
			assertEquals("5", carona.getCaronaUsuario("steve", "3"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Leeds", carona.getAtributoCarona("5", "origem"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals("Londres", carona.getAtributoCarona("5", "destino"));
		} catch (Exception e) {
			fail();
		}

		// Recuperar todas as caronas cadastradas
		assertEquals("{0,1,2}", carona.getTodasCaronasUsuario("mark"));
		assertEquals("{3,4,5}", carona.getTodasCaronasUsuario("steve"));

		// Recuperar solicitacoes confirmadas
		assertEquals("{0V}",
				solicitarVaga.getSolicitacoesConfirmadas("mark", "1"));
		assertEquals("{}", solicitarVaga.getSolicitacoesPendentes("mark", "1"));
		assertEquals("[]", solicitarPontoVaga.getPontosEncontro("mark", "1"));

		assertEquals("[Acude Velho;Hiper Bompreco]",
				solicitarPontoVaga.getPontosSugeridos("mark", "1"));

		usuario.encerrarSistema();
	}
}
