package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.accept.SimboraEasyAccept;
import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;

public class VisualizarPerfilTeste {

	UsuarioBusiness usuario;
	PerfilBusiness perfil;
	SessaoBusiness sessao;
	CaronaBusiness carona;
	SolicitacaoVagasBusiness solicitacaoVagas;
	String sessaoID1 = "", sessaoID2 = "";
	String caronaID1 = "",caronaID2 = "";
	String solicitacaoID1 = "",solicitacaoID2 = "";
	
	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		perfil = new PerfilBusiness();
		sessao = new SessaoBusiness();
		carona = new CaronaBusiness();
		solicitacaoVagas = new SolicitacaoVagasBusiness();
		new SimboraEasyAccept().zerarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");
		usuario.criarUsuario("bruno", "brun0", "Bruno Clementino", "Alagoa Nova, Paraíba", "bruno@facebook.com");
		try {
			sessaoID1 = sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		try {
			sessaoID2 = sessao.abrirSessao("bruno", "brun0");
		} catch (SessaoException e) {
			fail();
		}
		try {
			caronaID1 = carona.cadastrarCarona(sessaoID1, "Campina Grande", "Alagoa Nova", "30/05/2012", "19:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		try {
			solicitacaoID1 = solicitacaoVagas.solicitarVaga(sessaoID2, caronaID1);
		} catch (CaronaException e) {
			fail();
		}
		try {
			solicitacaoVagas.aceitarSolicitacao(sessaoID1, solicitacaoID1);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void criarUsuario() {
		
		try {
			perfil.visualizarPerfil(sessaoID1, "mark");
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("Mark Zuckerberg", perfil.getAtributoPerfil(sessaoID1, "nome"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("Palo Alto, California", perfil.getAtributoPerfil(sessaoID1, "endereco"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("mark@facebook.com", perfil.getAtributoPerfil(sessaoID1, "email"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("["+caronaID1+"]", perfil.getAtributoPerfil(sessaoID1, "historico de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("["+caronaID1+"]", perfil.getAtributoPerfil("bruno", "historico de vagas em caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil(sessaoID1, "caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil(sessaoID1, "caronas que não funcionaram"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil(sessaoID1, "faltas em vagas de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		try {
			assertEquals("0", perfil.getAtributoPerfil(sessaoID1, "presenças em vagas de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		// Cadastrar carona
		try {
			caronaID2 = carona.cadastrarCarona(sessaoID1, "São Francisco", "Palo Alto", "12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		
		// 
		try {
			perfil.visualizarPerfil(sessaoID1, "mark");
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("Mark Zuckerberg", perfil.getAtributoPerfil("mark", "nome"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("Palo Alto, California", perfil.getAtributoPerfil("mark", "endereco"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("mark@facebook.com", perfil.getAtributoPerfil("mark", "email"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("["+caronaID1+caronaID2+"]", perfil.getAtributoPerfil("mark", "historico de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("[]", perfil.getAtributoPerfil("mark", "historico de vagas em caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil("mark", "caronas seguras e tranquilas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil("mark", "caronas que não funcionaram"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil("mark", "faltas em vagas de caronas"));
		} catch (PerfilException e) {
			fail();
		}
		
		try {
			assertEquals("0", perfil.getAtributoPerfil("mark", "presenças em vagas de caronas"));
		} catch (PerfilException e) {
			fail(); 
		}
	}
	
	@Test
	public void errosPossiveis() {
		
		
		try {
			perfil.visualizarPerfil(sessaoID1, "pedro");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		try {
			perfil.visualizarPerfil(sessaoID1, "	");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		try {
			perfil.visualizarPerfil(null, "mark");
		} catch (PerfilException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		// Erros de getAtributoPerfil
		try {
			perfil.getAtributoPerfil(null, "caronas que não funcionaram");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			perfil.getAtributoPerfil("	", "caronas que não funcionaram");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			perfil.getAtributoPerfil("mark", "	");
		} catch (PerfilException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			perfil.getAtributoPerfil("mark", null);
		} catch (PerfilException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			perfil.getAtributoPerfil("pedro", "caronas que não funcionaram");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
	}
}