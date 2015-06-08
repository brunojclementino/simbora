package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;

public class ReviewCaronaTest {

	UsuarioBusiness usuario;
	CaronaBusiness carona;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitacaoVagas;
	SolicitacaoPontoDeEncontroBusiness solicitacaoPontoEncontro;
	PerfilBusiness perfilCaroneiro;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitacaoVagas = new SolicitacaoVagasBusiness();
		solicitacaoPontoEncontro = new SolicitacaoPontoDeEncontroBusiness();
		perfilCaroneiro = new PerfilBusiness();

		usuario.encerrarSistema();
		carona.encerrarSistema();
		sessao.getSessoes().clear();
		solicitacaoVagas.encerrarSistema();
		solicitacaoPontoEncontro.encerrarSistema();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		usuario.criarUsuario("bill", "bilz@o", "Willian Henry Gates III",
				"Medina, Washington", "billzin@gmail.com");

		usuario.criarUsuario("vader", "d4rth", "Anakin Skywalker",
				"Death Star I", "dartvander@empire.com");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		// Carona 4
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"02/06/2013", "12:00", "3");
		} catch (CaronaException e) {
			fail();
		}
		// Carona 5
		try {
			assertEquals("1",carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa",
					"04/06/2013", "14:00", "2"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("bill", sessao.abrirSessao("bill", "bilz@o"));
		} catch (SessaoException e) {
			fail();
		}
	}

	@Test
	public void reviewTest() {

		try {
			assertEquals("0V", solicitacaoVagas.solicitarVaga("bill", "0"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("Campina Grande",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			solicitacaoVagas.aceitarSolicitacao("mark", "0V");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("2", carona.getAtributoCarona("0", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			solicitacaoVagas.solicitarVaga("bill", "1");
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("Campina Grande",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		solicitacaoVagas.aceitarSolicitacao("mark", "1V");
		try {
			assertEquals("1", carona.getAtributoCarona("1", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			perfilCaroneiro.reviewVagaEmCarona("mark", "0", "bill", "faltou");
		} catch (PerfilException e2) {
			fail();
		}

		try {
			assertEquals("bill",
					perfilCaroneiro.visualizarPerfil("bill", "bill"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					perfilCaroneiro.getAtributoPerfil("bill", "nome"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Medina, Washington",
					perfilCaroneiro.getAtributoPerfil("bill", "endereco"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("billzin@gmail.com",
					perfilCaroneiro.getAtributoPerfil("bill", "email"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("[0,1]", perfilCaroneiro.getAtributoPerfil("bill",
					"historico de vagas em caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("[]", perfilCaroneiro.getAtributoPerfil("bill",
					"historico de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("bill",
					"caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("bill",
					"caronas que não funcionaram"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("bill",
					"faltas em vagas de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("bill",
					"presenças em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		// Segundo Review
		try {
			perfilCaroneiro.reviewVagaEmCarona("mark", "1", "bill",
					"não faltou");
		} catch (PerfilException e2) {
			fail();
		}

		try {
			assertEquals("bill",
					perfilCaroneiro.visualizarPerfil("bill", "bill"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					perfilCaroneiro.getAtributoPerfil("bill", "nome"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Medina, Washington",
					perfilCaroneiro.getAtributoPerfil("bill", "endereco"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("billzin@gmail.com",
					perfilCaroneiro.getAtributoPerfil("bill", "email"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("[0,1]", perfilCaroneiro.getAtributoPerfil("bill",
					"historico de vagas em caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("[]", perfilCaroneiro.getAtributoPerfil("bill",
					"historico de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("bill",
					"caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("bill",
					"caronas que não funcionaram"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("bill",
					"faltas em vagas de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("bill",
					"presenças em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void possiveisErros() {

		try {
			assertEquals("0V", solicitacaoVagas.solicitarVaga("bill", "1"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			solicitacaoVagas.aceitarSolicitacao("mark", "0V");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			perfilCaroneiro.reviewVagaEmCarona("mark", "1", "bill",
					"não dou mais carona");
		} catch (PerfilException e) {
			assertEquals("Opção inválida.", e.getMessage());
		}

		try {
			sessao.abrirSessao("vader", "d4rth");
		} catch (SessaoException e) {
			fail();
		}

		try {
			perfilCaroneiro.reviewVagaEmCarona("mark", "1", "vader",
					"não funcionou");
		} catch (PerfilException e) {
			assertEquals("Usuário não possui vaga na carona.", e.getMessage());
		}

		usuario.encerrarSistema();
		carona.encerrarSistema();
		solicitacaoPontoEncontro.encerrarSistema();
		solicitacaoVagas.encerrarSistema();

	}

	// US09
	@Test
	public void reviewComentarios() {
		// US09 linha 26
		try {
			assertEquals("0V", solicitacaoVagas.solicitarVaga("bill", "0"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Campina Grande",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"origem"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"destino"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitacaoPontoEncontro.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		// Aceitar requisição
		try {
			solicitacaoVagas.aceitarSolicitacao("mark", "0V");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("2", carona.getAtributoCarona("0", "vagas"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		// Requisitar vaga na carona.
		try {
			assertEquals("1V", solicitacaoVagas.solicitarVaga("bill", "1"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Campina Grande",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"origem"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"destino"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"Dono da carona"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("Willian Henry Gates III",
					solicitacaoPontoEncontro.getAtributoSolicitacao("1V",
							"Dono da solicitacao"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		// Aceitar requisição
		try {
			solicitacaoVagas.aceitarSolicitacao("mark", "1V");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("1", carona.getAtributoCarona("1", "vagas"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}
		
		// Review de caronas
		try {
			perfilCaroneiro.reviewCarona("bill", "0", "segura e tranquila");
		} catch (PerfilException e3) {
			e3.printStackTrace();
			fail();
		}
		try {
			assertEquals("mark", perfilCaroneiro.visualizarPerfil("mark", "mark"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("Mark Zuckerberg", perfilCaroneiro.getAtributoPerfil("mark", "nome"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("Palo Alto, California", perfilCaroneiro.getAtributoPerfil("mark", "endereco"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("mark@facebook.com", perfilCaroneiro.getAtributoPerfil("mark", "email"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("[]", perfilCaroneiro.getAtributoPerfil("mark", "historico de vagas em caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("mark", "caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("mark", "caronas que não funcionaram"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("mark", "faltas em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("mark", "presenças em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		
		// Segunda REVIEW
		try {
			perfilCaroneiro.reviewCarona("bill", "1", "não funcionou");
		} catch (PerfilException e2) {
			e2.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("mark", perfilCaroneiro.visualizarPerfil("mark", "mark"));
		} catch (PerfilException e1) {
			e1.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("Mark Zuckerberg", perfilCaroneiro.getAtributoPerfil("mark", "nome"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("Palo Alto, California", perfilCaroneiro.getAtributoPerfil("mark", "endereco"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("mark@facebook.com", perfilCaroneiro.getAtributoPerfil("mark", "email"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("[]", perfilCaroneiro.getAtributoPerfil("mark", "historico de vagas em caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("mark", "caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("1", perfilCaroneiro.getAtributoPerfil("mark", "caronas que não funcionaram"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("mark", "faltas em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals("0", perfilCaroneiro.getAtributoPerfil("mark", "presenças em vagas de caronas"));
		} catch (PerfilException e) {
			e.printStackTrace();
			fail();
		}
		
		// Erros ... 
		
		try{
			perfilCaroneiro.reviewCarona("bill", "1", "bacana");
		} catch(PerfilException e) {
			assertEquals("Opção inválida.", e.getMessage());
		}
		
		try {
			assertEquals("vader", sessao.abrirSessao("vader", "d4rth"));
		} catch (SessaoException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			perfilCaroneiro.reviewCarona("vader", "1", "não funcionou");
			fail();
		} catch (PerfilException e) {
			assertEquals("Usuário não possui vaga na carona.", e.getMessage());
		}
		
		usuario.encerrarSistema();
	}
	
}
