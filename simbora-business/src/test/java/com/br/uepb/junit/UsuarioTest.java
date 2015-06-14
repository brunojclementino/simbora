package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class UsuarioTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;

	@Before 
	public void iniciarTest() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		usuario.encerrarSistema();
	}

	/**
	 * Tests de criação de usuários
	 */
	@Test
	public void criarUsuarios_EV() {
		usuario.encerrarSistema();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		usuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");

		usuario.criarUsuario("bill", "severino", "William Henry Gates III",
				"Medina, Washington", "billzin@msn.com");

		// Abrir Sessão
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		//
		assertEquals("Mark Zuckerberg",
				usuario.getAtributoUsuario("mark", "nome"));

		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("mark", "endereco"));

		// Abrir Sessão
		try {
			sessao.abrirSessao("steve", "5t3v3");
		} catch (SessaoException e) {
			assertEquals("", e.getMessage());
		}
		//
		assertEquals("Steven Paul Jobs",
				usuario.getAtributoUsuario("steve", "nome"));

		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("steve", "endereco"));

		usuario.getUsuarios().clear();
	}

	@Test
	public void criarUsuario_EI() {

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		// Tentativas de criar usuário (Falta de algum atributo)
		// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
		// endereco="Palo Alto, California" email="mark@facebook.com"
		// Login = null
		try {
			usuario.criarUsuario(null, "xptoz", "xpto", "xpto",
					"logininvalido@gmail.com");
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// Login = ""
		try {
			usuario.criarUsuario(" ", "xptoz", "xpto", "xpto",
					"logininvalido@gmail.com");
		} catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// Nome = null
		try {
			usuario.criarUsuario("xpto", "xptoz", null, "xpto",
					"logininvalido@gmail.com");
		} catch (UsuarioException e) {
			assertEquals("Nome inválido", e.getMessage());
		}
		// Nome = ""
		try {
			usuario.criarUsuario("xpto", "xptoz", " 	", "xpto",
					"logininvalido@gmail.com");
		} catch (UsuarioException e) {
			assertEquals("Nome inválido", e.getMessage());
		}
		// Email = null
		try {
			usuario.criarUsuario("xpto", "xptoz", "patriciano", "xpto", null);
		} catch (UsuarioException e) {
			assertEquals("Email inválido", e.getMessage());
		}
		// Email = ""
		try {
			usuario.criarUsuario("xpto", "xptoz", "pantcho", "xpto", "	");
		} catch (UsuarioException e) {
			assertEquals("Email inválido", e.getMessage());
		}

		// erro: Já existe um usuário com este email
		try {
			usuario.criarUsuario("xpto", "tttppp", "markito", "xpto",
					"mark@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("Já existe um usuário com este email", e.getMessage());
		}
		// login: Já existe um usuário com este email
		try {
			usuario.criarUsuario("mark", "tttppp", "markito", "xpto",
					"markinho@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("Já existe um usuário com este login", e.getMessage());
		}

		// Abrir sessao
		// login = null
		try {
			sessao.abrirSessao(null, "test");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// login = ""
		try {
			sessao.abrirSessao(" 	", "segundotest");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// login = inválido
		try {
			sessao.abrirSessao("mark", "test");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// login = ""
		try {
			sessao.abrirSessao("mark", "segundotest");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		// login = ""
		try {
			sessao.abrirSessao("xpto", "maisXptoAinda");
		} catch (SessaoException e) {
			assertEquals("Usuário inexistente", e.getMessage());
		}
		usuario.getUsuarios().clear();
	}

	@Test
	public void getAtributo() {
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			usuario.getAtributoUsuario(null, "nome");	
		}catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			usuario.getAtributoUsuario(" ", "nome");	
		}catch (UsuarioException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			usuario.getAtributoUsuario("xpto", "nome");	
		}catch (UsuarioException e) {
			assertEquals("Usuário inexistente", e.getMessage());
		}		
		
		try {
			usuario.getAtributoUsuario("mark", null);	
		}catch (UsuarioException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		try {
			usuario.getAtributoUsuario("mark", "");	
		}catch (UsuarioException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}				
		try {
			usuario.getAtributoUsuario("mark", "xpto");	
		}catch (UsuarioException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}
		try {
			usuario.getAtributoUsuario("mark", "email");	
		}catch (UsuarioException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}		
	}
	
	@Test
	public void encerraSistema() {
		usuario.encerrarSistema();
	}
}
