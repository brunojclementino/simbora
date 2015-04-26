package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;


public class PerfilUsuarioTeste {

	UsuarioBusiness usuarioBusiness;
	PerfilBusiness perfilUsuario;
	SessaoBusiness sessao;
	CaronaBusiness caronaBusiness;
	
	@Before
	public void test() {
		usuarioBusiness = new UsuarioBusiness();
		perfilUsuario = new PerfilBusiness();
		sessao = new SessaoBusiness();
		caronaBusiness = new CaronaBusiness();
		
		caronaBusiness.caronas.clear();
		usuarioBusiness.usuarios.clear();
	}

	@Test
	public void criarUsuario() {

		usuarioBusiness.criarUsuario("ana", "@na", "Ana Tenorio",
				"Guanabira, Para�ba", "anagatinha95@msn.com");

		try {
			sessao.abrirSessao("ana", "@na");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		// Ana vai criar uma carona
		try {
			caronaBusiness.cadastrarCarona("ana", "Campina Grande", "Patos", "30/11/2015", "22:00", "2");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		// Fazer os Tests de login errado
		try {
			perfilUsuario.getAtributoPerfil(null, "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		// Fazer os Tests de login errado
		try {
			perfilUsuario.getAtributoPerfil(" ", "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		// Fazer os tests com login inexistente

		try {
			perfilUsuario.getAtributoPerfil("rodolfo", "historico de caronas");
		} catch (PerfilException e) {
			assertEquals("", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		// Acessar os perfis que não existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", null);

		} catch (PerfilException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "      ");

		} catch (PerfilException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}		
		
		// Atributos que n�o existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", "quantidade de filhos");
		} catch (PerfilException e) {
			assertEquals("Login inv�lido", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}		
		// Acessar os perfis que existam!
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de caronas");

		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		 
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "");
		} catch (PerfilException e) {
			assertEquals("Atributo inválido", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			perfilUsuario.getAtributoPerfil("ana", "historico de vagas em caronas");
		} catch (PerfilException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
	}
}
