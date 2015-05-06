package com.br.uepb.junit;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;

public class ComfirmacaoDePresencaTest {

	UsuarioBusiness usuario;
	CaronaBusiness carona;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitarVagas;
	
	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitarVagas = new SolicitacaoVagasBusiness();
		
		usuario.encerrarSistema();
		carona.encerrarSistema();
		solicitarVagas.encerrarSistema();
		
		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");
		usuario.criarUsuario("bill", "bilz@o", "Willian Henry Gates III", "Medina, Washington", "billzin@gmail.com");
		usuario.criarUsuario("vader", "d4rth", "Anakin Skywalker", "Death Star I", "darthvader.com");
		
	}

	@Test
	public void confirmacaoTest() {
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "02/06/2013", "12:00", "3");
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "04/06/2013", "16:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		// Iniciar Sessao
		
		try {
			sessao.abrirSessao("bill", "bilz@o");
		} catch (SessaoException e) {
			fail();
		}
		
		// Requisitar vagas
		try {
		solicitarVagas.solicitarVaga("bill", "0");
		} catch (Exception e) {
			fail();
		}
	}
}
