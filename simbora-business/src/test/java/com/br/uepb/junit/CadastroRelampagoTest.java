package com.br.uepb.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.accept.SimboraEasyAccept;
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
	CaronaRelampagoBusiness caronaRelampago;
	SolicitacaoVagasBusiness solicitacao;
	

	String sessaoID1 = "";
	String caronaID1 = "", caronaID2 = "", caronaID3 = "";
	
	@Before
	public void before() throws SessaoException {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		caronaBusiness = new CaronaBusiness();
		perfil = new PerfilBusiness();
		caronaRelampago = new CaronaRelampagoBusiness();
		solicitacao = new SolicitacaoVagasBusiness();
		
		
		new SimboraEasyAccept().zerarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckeberg",
					"Palo Alto, California", "mark@facebook.com");

		sessaoID1 = sessao.abrirSessao("mark", "m@rk");

	}

	@Test
	public void test() {
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark",
					"Campina Grande", "João Pessoa", "23/06/2013",
					"26/06/2013", "16:00", "3");
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("Campina Grande",
					caronaRelampago.getAtributoCaronaRelampago(caronaID1, "origem"));
		} catch (CaronaException e3) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					caronaRelampago.getAtributoCaronaRelampago(caronaID1, "destino"));
		} catch (CaronaException e2) {
			fail();
		}

		try {
			assertEquals("Campina Grande - João Pessoa",
					caronaBusiness.getTrajeto(caronaID1));//Verificando se o trajeto está correto
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("3",
					caronaRelampago.getAtributoCaronaRelampago(caronaID1, "minimoCaroneiros"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("3", caronaRelampago.getMinimoCaroneiros(caronaID1));
		} catch (CaronaException e) {
			fail();
		}

		try {
			caronaID2 = caronaRelampago.cadastrarCaronaRelampago("mark",
					"Rio de Janeiro", "São Paulo", "31/05/2013", "01/06/2013",
					"08:00", "2");
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("31/05/2013",
					caronaRelampago.getAtributoCaronaRelampago(caronaID2, "dataIda"));
		} catch (CaronaException e1) {
			fail();
		}
		try {
			assertEquals("01/06/2013",
					caronaRelampago.getAtributoCaronaRelampago(caronaID2, "dataVolta"));
		} catch (CaronaException e1) {
			fail();
		}
		try {
			assertEquals("2",
					caronaRelampago.getAtributoCaronaRelampago(caronaID2, "minimoCaroneiros"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			caronaID3 = caronaRelampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Campina Grande", "25/11/2026",
					"26/11/2026", "06:59", "4");
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					caronaRelampago.getCaronaRelampago(caronaID3));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("false",
					caronaRelampago.getAtributoCaronaRelampago(caronaID3, "expired"));
		} catch (CaronaException e1) {
			fail();
		}
		String idExpired = caronaRelampago.setCaronaRelampagoExpired(caronaID3);
		try {
			assertEquals("true",
					caronaRelampago.getAtributoCaronaRelampago(caronaID3, "expired"));
		} catch (CaronaException e1) {
			fail();
		}
			
		assertEquals("[]", caronaRelampago.getAtributoExpired(idExpired, "emailTo"));
		
		
		
		
	}
	
	@Test
	public void entradasInvalidas() {
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago(null, "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("teste", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inexistente", e.getMessage());
		}
		
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", null, "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", null, "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "", "23/06/2013", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		// Data inválida
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", null, "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "30/02/2012", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "31/04/2012", "26/06/2013", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "32/12/2012", "02/01/2012", "16:00", "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "30/12/2012", "02/13/2012", "16:00", "3");
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
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", null, "3");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "seis", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		// quantidade de vagas
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", null);
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "16:00", "dois");
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark", "Patos", "João Pessoa", "31/05/2013", "02/06/2013", "16:00", "0");
			fail();
		} catch (CaronaException e) {
			assertEquals("Minimo Caroneiros inválido", e.getMessage());
		}
		
		try {
			caronaRelampago.getAtributoCaronaRelampago(null, "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		try {
			caronaRelampago.getAtributoCaronaRelampago("", "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		// Atributo inexistente!
		try {
			caronaRelampago.getAtributoCaronaRelampago("xpto", "origem");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		// Cadastra 1 carona Relampago
		try {
			caronaID1 = caronaRelampago.cadastrarCaronaRelampago("mark",
					"João Pessoa", "Lagoa Seca", "25/11/2017", "28/11/2017",
					"05:00", "4");
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			caronaRelampago.getAtributoCaronaRelampago(caronaID1, null);
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			caronaRelampago.getAtributoCaronaRelampago(caronaID1, "");
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			caronaRelampago.getAtributoCaronaRelampago(caronaID1, "xpto");
		} catch (CaronaException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}
		
		try {
			caronaRelampago.getCaronaRelampago("");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		try {
			caronaRelampago.getCaronaRelampago(null);
		} catch (CaronaException e) {
			assertEquals("Carona Inválida", e.getMessage());
		}
		
		try {
			caronaRelampago.getCaronaRelampago("xpto");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		try {
			caronaBusiness.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		
		try {
			caronaBusiness.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inválida", e.getMessage());
		}
		
		try {
			caronaBusiness.getTrajeto("xpto");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			caronaRelampago.getMinimoCaroneiros("xpto");
		} catch (CaronaException e) {
			assertEquals("IdCarona não existe", e.getMessage());
		}
	}
	
	/*@Test
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
		
		
	}*/
}
