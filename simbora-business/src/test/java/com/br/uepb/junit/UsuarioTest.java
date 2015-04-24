package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.UsuarioDaoImp;
public class UsuarioTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;

	@Before
	public void iniciarTest() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();

		usuario.usuarios.clear();
		new UsuarioDaoImp().excluirTudo();

	}

	@Test
	public void testA_CriarUsuario() {
		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("J� existe um usu�rio com este login", e.getMessage());
		}
		try {
			usuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
					"Palo Alto, California", "jobs@apple.com");
		} catch (UsuarioException e) {
			assertEquals("J� existe um usu�rio com este login", e.getMessage());
		}

		try {
			usuario.criarUsuario("bill", "severino", "William Henry Gates III",
					"Medina, Washington", "billzin@msn.com");
		} catch (UsuarioException e) {
			assertEquals("J� existe um usu�rio com este login", e.getMessage());
		}
		try {
			usuario.criarUsuario("bill", "severino", "William Henry Gates III",
					"Medina, Washington", "billzin@msn.com");
		} catch (UsuarioException e) {
			assertEquals("J� existe um usu�rio com este login", e.getMessage());
		}

		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException er) {
			assertEquals("Usu�rio inexistente", er.getMessage());
		}

		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException user) {
			assertEquals("J� existe um usu�rio com este login",
					user.getMessage());
		}
	}

	@Test
	public void testC_GetAtributoUsuario() {

		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException user) {
			assertEquals("J� existe um usu�rio com este login",
					user.getMessage());
		}

		try {
			usuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
					"Palo Alto, California", "jobs@apple.com");
		} catch (UsuarioException user) {
			assertEquals("J� existe um usu�rio com este login",
					user.getMessage());
		}
		try {
			usuario.criarUsuario("bill", "severino", "William Henry Gates III",
					"Medina, Washington", "billzin@msn.com");
		} catch (UsuarioException user) {
			assertEquals("J� existe um usu�rio com este login",
					user.getMessage());
		}
		// / Login existente!
		try {
			usuario.criarUsuario(null, "m@ria", "Maria Guilhermina", "Paris",
					"Maria@msn.com");
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}

		// Nome invalido
		try {
			usuario.criarUsuario("maria", "m@ria", null, "Paris",
					"Maria@msn.com");
		} catch (UsuarioException e) {
			assertEquals("Nome inv�lido", e.getMessage());
		}
		// email invalido
		try {
			usuario.criarUsuario("Jose", "jose", "Jos� Maria", "Paris, Fran�a",
					"mark@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("J� existe um usu�rio com este email", e.getMessage());
		}

		// email invalido
		try {
			usuario.criarUsuario("Jose", "jose", "Jos� Maria", "Paris, Fran�a",
					null);
		} catch (UsuarioException e) {
			assertEquals("Email inv�lido", e.getMessage());
		}

		assertEquals("Mark Zuckerberg",
				usuario.getAtributoUsuario("mark", "nome"));
		assertEquals("Palo Alto, California",
				usuario.getAtributoUsuario("mark", "endereco"));

		assertEquals("William Henry Gates III",
				usuario.getAtributoUsuario("bill", "nome"));
		assertEquals("Medina, Washington",
				usuario.getAtributoUsuario("bill", "endereco"));

		// Test passagem de parametro errado!
		// login == null
		try {
			assertEquals("", usuario.getAtributoUsuario(null, "nome"));
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		}
		// login errado
		try {
			assertEquals("Mark Zuckerberg",
					usuario.getAtributoUsuario("markk", "nome"));
		} catch (UsuarioException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		// Segundo paramentro errado!
		try {
			assertEquals("Mark Zuckerberg",
					usuario.getAtributoUsuario("mark", "nombre"));
		} catch (UsuarioException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}

		sessao.encerrarSessao("mark");

	}

	@Test
	public void testD_zeraSistema() {
		usuario.zerarSistema();
	}
}
