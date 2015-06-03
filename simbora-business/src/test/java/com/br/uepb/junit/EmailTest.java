package com.br.uepb.junit;

import static org.junit.Assert.*;

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

public class EmailTest {

	UsuarioBusiness usuarioBusiness;
	CaronaBusiness caronaBusiness;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitacaoVagasBusiness;
	SolicitacaoPontoDeEncontroBusiness solicitarPontoBusiness;

	@Before
	public void test() {
		usuarioBusiness = new UsuarioBusiness();
		caronaBusiness = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitacaoVagasBusiness = new SolicitacaoVagasBusiness();
		solicitarPontoBusiness = new SolicitacaoPontoDeEncontroBusiness();

		usuarioBusiness.encerrarSistema();
		caronaBusiness.encerrarSistema();
		sessao.getSessoes().clear();
		sessao.getUsuarios().clear();
		solicitacaoVagasBusiness.encerrarSistema();
		solicitarPontoBusiness.encerrarSistema();
	}

	@Test
	public void enviarEmailTest() {
		try {
			usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			fail();
		}

		try {
			usuarioBusiness.criarUsuario("bill", "bilz@o", "Bill Clinton",
					"Hollywood, California", "bill@gmail.com");
		} catch (UsuarioException e) {
			fail();
		}

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0",
					caronaBusiness.cadastrarCarona("mark", "Campina Grande",
							"João Pessoa", "02/06/2013", "12:00", "1"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("1",
					caronaBusiness.cadastrarCarona("mark", "Campina Grande",
							"João Pessoa", "04/06/2013", "16:00", "2"));
		} catch (CaronaException e) {
			e.printStackTrace();
			fail();
		}

		// Bill
		try {
			sessao.abrirSessao("bill", "bilz@o");
		} catch (SessaoException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals("0V",
					solicitacaoVagasBusiness.solicitarVaga("bill", "0"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("Campina Grande",
					solicitarPontoBusiness.getAtributoSolicitacao("0V",
							"origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					solicitarPontoBusiness.getAtributoSolicitacao("0V",
							"destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Mark Zuckerberg",
					solicitarPontoBusiness.getAtributoSolicitacao("0V",
							"Dono da carona"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Bill Clinton",
					solicitarPontoBusiness.getAtributoSolicitacao("0V",
							"Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}

		assertTrue(usuarioBusiness.enviarEmail("mark",
				"bill@gmail.com", "A solicitação foi recebida"));

		solicitacaoVagasBusiness.aceitarSolicitacao("mark", "0V");

		try {
			assertEquals("0", caronaBusiness.getAtributoCarona("0", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		assertTrue(usuarioBusiness.enviarEmail("mark",
				"bill@gmail.com", "A carona foi confirmada"));

		try {
			assertEquals("1V", solicitacaoVagasBusiness.solicitarVaga("bill", "1"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			assertEquals("Campina Grande", solicitarPontoBusiness.getAtributoSolicitacao("1V", "origem") );
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("João Pessoa", solicitarPontoBusiness.getAtributoSolicitacao("1V", "destino"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("Mark Zuckerberg", solicitarPontoBusiness.getAtributoSolicitacao("1V", "Dono da carona") );
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("Bill Clinton", solicitarPontoBusiness.getAtributoSolicitacao("1V", "Dono da solicitacao"));
		} catch (CaronaException e) {
			fail();
		}
	
		try {
			assertTrue(usuarioBusiness.enviarEmail("mark", "bill@gmail.com", "A carona foi confirmada"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			solicitacaoVagasBusiness.rejeitarSolicitacao("bill", "1V");
		} catch (Exception e) {
			fail();
		}
		
		try {
			assertEquals("2", caronaBusiness.getAtributoCarona("1", "vagas"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertTrue(usuarioBusiness.enviarEmail("mark", "bill@gmail.com"
									, "A carona foi rejeitada por falta de vaga"));
		} catch (Exception e) {
			fail();
		}		
				
	}
}