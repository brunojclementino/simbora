package com.br.uepb.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;

public class InterMunicipaisUrbanas {

	UsuarioBusiness usuario;
	CaronaBusiness carona;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitacaoVagas;
	SolicitacaoPontoDeEncontroBusiness solicitacaoPontoEncontro;
	PerfilBusiness perfilCaroneiro;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		solicitacaoVagas = new SolicitacaoVagasBusiness();
		solicitacaoPontoEncontro = new SolicitacaoPontoDeEncontroBusiness();
		perfilCaroneiro = new PerfilBusiness();

		usuario.encerrarSistema();
		carona.encerrarSistema();
		sessao.getSessoes().clear();
		solicitacaoVagas.encerrarSistema();
		solicitacaoPontoEncontro.encerrarSistema();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		usuario.criarUsuario("bill", "bilz@o", "Willian Henry Gates III",
				"Medina, Washington", "billzin@gmail.com");

		usuario.criarUsuario("vader", "d4rth", "Anakin Skywalker",
				"Death Star I", "dartvander@empire.com");

	}

	@Test
	public void cadastrarCaronas() {
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"João Pessoa", "02/06/2013", "12:00", "3"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("1", carona.cadastrarCarona("mark", "Souza",
					"João Pessoa", "08/06/2013", "14:00", "2"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("2", carona.cadastrarCarona("mark", "Campina Grande",
					"Patos", "25/06/2013", "8:00", "1"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("3m", carona.cadastrarCaronaMunicipal("mark",
					"Açude Velho", "Shopping Boulevard", "Campina Grande",
					"04/06/2013", "20:00", "2"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("4m", carona.cadastrarCaronaMunicipal("mark",
					"Praía do Cajá", "Villa São Paulo", "João Pessoa",
					"04/06/2013", "20:00", "2"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("5m", carona.cadastrarCaronaMunicipal("mark",
					"Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa",
					"04/06/2013", "14:00", "2"));
		} catch (CaronaException e) {
			fail();
		}
		
		// GetAtributos
		try {
			assertEquals("false", carona.getAtributoCarona("0", "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("false", carona.getAtributoCarona("1", "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("false", carona.getAtributoCarona("2", "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		try {
			assertEquals("true", carona.getAtributoCarona("3m", "ehMunicipal"));
		} catch (CaronaException e1) { 
			fail();
		}

		try {
			assertEquals("true", carona.getAtributoCarona("4m", "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		
		try {
			assertEquals("true", carona.getAtributoCarona("5m", "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		
		
		try {
			assertEquals("bill", sessao.abrirSessao("bill", "bilz@o"));
		} catch (SessaoException e) {
			fail();
		}
		
		try {
			assertEquals("{0}", carona.localizarCarona("bill", "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{3m}", carona.localizarCaronaMunicipal("bill", "Campina Grande", "Açude Velho", "Shopping Boulevard"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{4m,5m}", carona.localizarCaronaMunicipal("mark", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Testar possiveis erros no sistema 
		try {
			carona.localizarCaronaMunicipal("bill", null, "Açude Velho", "Shopping Boulevard");
		} catch (CaronaException e) {
			assertEquals("Cidade inexistente", e.getMessage());
		}
		 
		try {
			carona.localizarCaronaMunicipal("bill", "", "Açude Velho", "Shopping Boulevard");
		} catch (CaronaException e) {
			assertEquals("Cidade inexistente", e.getMessage());
		}
		
		carona.encerrarSistema();
		
	}

}
