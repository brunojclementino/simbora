package com.br.uepb.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class CadastrarInteresse {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness carona;
	
	@Before
	public void iniciarTest() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		carona = new CaronaBusiness();
		
		carona.encerrarSistema();
		sessao.getSessoes().clear();
		sessao.getUsuarios().clear();
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
			assertEquals("0I", carona.cadastrarInteresse("zezyt0", "João Pessoa", "Campina Grande", "23/06/2013", "06:00", "16:00"));
		} catch(CaronaException e){
			fail();
		}
	}

}
