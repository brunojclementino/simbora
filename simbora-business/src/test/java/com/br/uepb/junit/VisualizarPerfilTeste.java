package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;

public class VisualizarPerfilTeste {

	UsuarioBusiness usuario;
	PerfilBusiness perfil;
	SessaoBusiness sessao;
	CaronaBusiness carona;
	
	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		perfil = new PerfilBusiness();
		sessao = new SessaoBusiness();
		carona = new CaronaBusiness();
		
		usuario.encerrarSistema();
		perfil.zerarSistema();
		sessao.getUsuarios().clear();
		sessao.getSessoes().clear();
		carona.encerrarSistema();
	}

	@Test
	public void criarUsuario() {
		
		usuario.encerrarSistema();
		perfil.zerarSistema();
		sessao.getUsuarios().clear();
		sessao.getSessoes().clear();
		carona.encerrarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		
		try {
			perfil.visualizarPerfil("mark", "mark");
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
			assertEquals("[]", perfil.getAtributoPerfil("mark", "historico de caronas"));
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
		
		// Cadastrar carona
		try {
			carona.cadastrarCarona("mark", "São Francisco", "Palo Alto", "12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		
		// 
		try {
			perfil.visualizarPerfil("mark", "mark");
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
			assertEquals("[0]", perfil.getAtributoPerfil("mark", "historico de caronas"));
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
		usuario.encerrarSistema();
		perfil.zerarSistema(); 
		sessao.getSessoes().clear();
		carona.encerrarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");

		try {
			perfil.visualizarPerfil("mark", "pedro");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		try {
			perfil.visualizarPerfil("mark", "	");
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
	}
}