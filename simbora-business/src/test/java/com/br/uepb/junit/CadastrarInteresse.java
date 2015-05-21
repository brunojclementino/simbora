package com.br.uepb.junit;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class CadastrarInteresse {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness carona;
	PerfilBusiness perfil;

	@Before
	public void iniciarTest() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		carona = new CaronaBusiness();
		perfil = new PerfilBusiness();

		carona.encerrarSistema();
		usuario.encerrarSistema();
		// Criar os usuarios.
		try {
			usuario.criarUsuario("zezyt0", "z3z1t0", "Jose de zito",
					"Rua belarmina pereira 452, João Pessoa",
					"zezyto@gmail.com");
		} catch (UsuarioException e) {
			fail();
		}

		try {
			usuario.criarUsuario("manelito", "w4n3l1t0", "Manel da Silva",
					"Rua adamastor pitaco 24, João Pessoa",
					"manel@yahoo.com.br");
		} catch (UsuarioException e) {
			fail();
		}
		try {
			usuario.criarUsuario("jucaPeroba", "juqinha", "Juca Peroba",
					"Rua 13 de maio, Caruaru", "jucaPeroba@gmail.com");
		} catch (UsuarioException e) {
			fail();
		}
		try {
			usuario.criarUsuario("mariano0ab", "mariozinho", "Mariano Silva",
					"Rua 27 de maio, Campina Grande",
					"marianoAdvogado@gmail.com");
		} catch (UsuarioException e) {
			fail();
		}
		try {
			usuario.criarUsuario("caba", "Marcin", "Marcio Sarvai",
					"Rua 21 de maio, Campina Grande", "marcioSarvai@gmail.com");
		} catch (UsuarioException e) {
			fail();
		}

		// Abrir a sessao
		try {
			sessao.abrirSessao("zezyt0", "z3z1t0");
		} catch (SessaoException e) {
			fail();
		}

		try {
			assertEquals("Jose de zito",
					usuario.getAtributoUsuario("zezyt0", "nome"));
		} catch (UsuarioException e) {
			fail();
		}

		try {
			assertEquals("Rua belarmina pereira 452, João Pessoa",
					usuario.getAtributoUsuario("zezyt0", "endereco"));
		} catch (UsuarioException e) {
			fail();
		}

		// Abrir outra sessao
		try {
			sessao.abrirSessao("manelito", "w4n3l1t0");
		} catch (SessaoException e) {
			fail();
		}

		try {
			assertEquals("Manel da Silva",
					usuario.getAtributoUsuario("manelito", "nome"));
		} catch (UsuarioException e) {
			fail();
		}

		try {
			assertEquals("Rua adamastor pitaco 24, João Pessoa",
					usuario.getAtributoUsuario("manelito", "endereco"));
		} catch (UsuarioException e) {
			fail();
		}
		// Abrir outras sessões
		try {
			sessao.abrirSessao("jucaPeroba", "juqinha");
		} catch (SessaoException e) {
			fail();
		}
		try {
			sessao.abrirSessao("mariano0ab", "mariozinho");
		} catch (SessaoException e) {
			fail();
		}
		try {
			sessao.abrirSessao("caba", "Marcin");
		} catch (SessaoException e) {
			fail();
		}

	}

	// US11
	@Test
	public void mostrarInteresse() {
		try {
			assertEquals("0I", carona.cadastrarInteresse("zezyt0",
					"João Pessoa", "Campina Grande", "23/06/2013", "06:00",
					"16:00"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("1I", carona.cadastrarInteresse("manelito",
					"Campina Grande", "João Pessoa", "25/06/2013", "11:00",
					"18:00"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("2I", carona.cadastrarInteresse("mariano0ab",
					"Campina Grande", "João Pessoa", "23/06/2013", "", "18:00"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("3I", carona.cadastrarInteresse("caba",
					"Campina Grande", "João Pessoa", "23/06/2013", "", "18:00"));
		} catch (CaronaException e) {
			fail();
		}

		// Linha 45
		try {
			assertEquals("0",
					carona.cadastrarCarona("jucaPeroba", "Campina Grande",
							"João Pessoa", "23/06/2013", "16:00", "3"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("1",
					carona.cadastrarCarona("jucaPeroba", "João Pessoa",
							"Campina Grande", "25/06/2013", "14:00", "4"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("2",
					carona.cadastrarCarona("mariano0ab", "João Pessoa",
							"Campina Grande", "25/06/2013", "15:00", "1"));
		} catch (CaronaException e) {
			fail();
		}

		// Verificar perfil
		try {
			assertEquals(
					"[Carona cadastrada no dia 23/06/2013, ás 16:00 de acordo com os seus interesses registrados. Entrar em contato com jucaPeroba@gmail.com]",
					perfil.verificarMensagensPerfil("mariano0ab"));
		} catch (Exception e) { 
			fail();
		}
	}

	@Test
	public void tratamentoErros() {
		// Tratamento dos erros
		// Erros na origem
		try {
			carona.cadastrarInteresse("zezyt0", "-", "João Pessoa",
					"23/06/2013", "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.cadastrarInteresse("zezyt0", "!", "João Pessoa",
					"23/06/2013", "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		// Erros no destino
		try {
			carona.cadastrarInteresse("zezyt0", "Campina Grande", "!",
					"23/06/2013", "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}

		try {
			carona.cadastrarInteresse("zezyt0", "Campina Grande", "-",
					"23/06/2013", "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}

		// Data inválida
		try {
			carona.cadastrarInteresse("zezyt0", "Campina Grande",
					"João Pessoa", "", "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			carona.cadastrarInteresse("zezyt0", "Campina Grande",
					"João Pessoa", null, "06:00", "16:00");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
	}
}