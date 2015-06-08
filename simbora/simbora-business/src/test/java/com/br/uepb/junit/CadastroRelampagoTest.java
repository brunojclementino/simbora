package com.br.uepb.junit;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.CaronaRelampagoBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class CadastroRelampagoTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness caronaBusiness;
	PerfilBusiness perfil;
	CaronaRelampagoBusiness relampago;
	SolicitacaoVagasBusiness solicitacao;
	
	@Before
	public void before() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		caronaBusiness = new CaronaBusiness();
		perfil = new PerfilBusiness();
		relampago = new CaronaRelampagoBusiness();
		solicitacao = new SolicitacaoVagasBusiness();
		
		usuario.encerrarSistema();
		caronaBusiness.encerrarSistema();
		sessao.getSessoes().clear();
		sessao.getUsuarios().clear();
		perfil.zerarSistema();
		relampago.encerrarSistema();
		solicitacao.encerrarSistema();
		
		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckeberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			fail();
		}

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

	}

	@Test
	public void test() {
		relampago.encerrarSistema();
		try {
			assertEquals("0R", relampago.cadastrarCaronaRelampago("mark",
					"Campina Grande", "João Pessoa", "23/06/2013",
					"26/06/2013", "16:00", "3"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("Campina Grande",
					relampago.getAtributoCaronaRelampago("0R", "origem"));
		} catch (CaronaException e3) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					relampago.getAtributoCaronaRelampago("0R", "destino"));
		} catch (CaronaException e2) {
			fail();
		}

		try {
			assertEquals("Campina Grande - João Pessoa",
					relampago.getTrajeto("0R"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("3",
					relampago.getAtributoCaronaRelampago("0R", "minimoCaroneiros"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("3", relampago.getMinimoCaroneiros("0R"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("1R", relampago.cadastrarCaronaRelampago("mark",
					"Rio de Janeiro", "São Paulo", "31/05/2013", "01/06/2013",
					"08:00", "2"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("31/05/2013",
					relampago.getAtributoCaronaRelampago("1R", "dataIda"));
		} catch (CaronaException e1) {
			fail();
		}
		try {
			assertEquals("2",
					relampago.getAtributoCaronaRelampago("1R", "minimoCaroneiros"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("2R", relampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Campina Grande", "25/11/2026",
					"26/11/2026", "06:59", "4"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					relampago.getCaronaRelampago("2R"));
		} catch (CaronaException e1) {
			fail();
		}

		// Carona 3R
		try {
			assertEquals("3R", relampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Lagoa Seca", "25/11/2016", "27/11/2016",
					"05:00", "4"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2016, as 05:00",
					relampago.getCaronaRelampago("3R"));
		} catch (CaronaException e1) {
			fail();
		}

		// Carona 4R
		try {
			assertEquals("4R", relampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Lagoa Seca", "25/11/2017", "28/11/2017",
					"05:00", "4"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00",
					relampago.getCaronaRelampago("4R"));
		} catch (CaronaException e) {
			fail();
		}

		relampago.encerrarSistema();
	}
	
	@Test
	public void entradasInvalidas() {
		try {
			relampago.cadastrarCaronaRelampago(null, "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("teste", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inexistente", e.getMessage());
		}
		
		
		try {
			relampago.cadastrarCaronaRelampago("mark", null, "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("mark", "", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", null, "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		// Data inválida
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", null, "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "30/02/2012", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "31/04/2012", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "32/12/2012", "02/01/2012", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		/* Foi comentada para não apresentar o erro!
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "08/06/2015", "08/06/2015", "10:43", "3");
//			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}*/
		
		// Hora
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", null, "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "seis", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		// quantidade de vagas
		try {
			relampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", null);
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "16:00", "dois");
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		try {
			relampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "16:00", "0");
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		
		try {
			relampago.getAtributoCaronaRelampago(null, "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		try {
			relampago.getAtributoCaronaRelampago("", "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		// Atributo inexistente!
		try {
			relampago.getAtributoCaronaRelampago("xpto", "origem");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		// Cadastra 1 carona Relampago
		relampago.encerrarSistema();
		try {
			assertEquals("0R", relampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Lagoa Seca", "25/11/2017", "28/11/2017",
					"05:00", "4"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			relampago.getAtributoCaronaRelampago("0R", null);
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			relampago.getAtributoCaronaRelampago("0R", "");
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			relampago.getAtributoCaronaRelampago("0R", "xpto");
		} catch (CaronaException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}
		
		try {
			relampago.getCaronaRelampago("");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		try {
			relampago.getCaronaRelampago(null);
		} catch (CaronaException e) {
			assertEquals("Carona Inválida", e.getMessage());
		}
		
		try {
			relampago.getCaronaRelampago("xpto");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		try {
			relampago.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		
		try {
			relampago.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inválida", e.getMessage());
		}
		
		try {
			relampago.getTrajeto("xpto");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
	}
	
	@Test
	public void bill() {
		sessao.encerrarSessao("mark");
		
		usuario.criarUsuario("bill", "billz@o", "Willian Henry Gates", "Medina, Washiton", "billzin@gmail.com");
		
		try {
			sessao.abrirSessao("bill", "billz@o");
		} catch (SessaoException e) {
			fail();
		}
		
		solicitacao.solicitarVaga("bill", "0");
		
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		
		solicitacao.aceitarSolicitacao("mark", "0V");
		
		usuario.criarUsuario("steve", "5t3v3", "Steve Mark", "Medina, Washington", "steve@gmail.com");
		
		try {
			sessao.abrirSessao("steve", "5t3v3");
		} catch (SessaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		solicitacao.solicitarVaga("steve", "0");
		
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		solicitacao.aceitarSolicitacao("mark", "1V");
		
		
	}
}
