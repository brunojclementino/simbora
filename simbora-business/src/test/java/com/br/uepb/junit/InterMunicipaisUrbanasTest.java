package com.br.uepb.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.accept.SimboraEasyAccept;
import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.CaronaMunicipalBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;

public class InterMunicipaisUrbanasTest {

	UsuarioBusiness usuario;
	CaronaBusiness carona;
	CaronaMunicipalBusiness caronaMunicipal;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitacaoVagas;
	SolicitacaoPontoDeEncontroBusiness solicitacaoPontoEncontro;
	PerfilBusiness perfilCaroneiro;

	@Before
	public void inicializar() {
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		caronaMunicipal = new CaronaMunicipalBusiness();
		sessao = new SessaoBusiness();
		solicitacaoVagas = new SolicitacaoVagasBusiness();
		solicitacaoPontoEncontro = new SolicitacaoPontoDeEncontroBusiness();
		perfilCaroneiro = new PerfilBusiness();

		new SimboraEasyAccept().zerarSistema();

		usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		usuario.criarUsuario("bill", "bilz@o", "Willian Henry Gates III",
				"Medina, Washington", "billzin@gmail.com");

		usuario.criarUsuario("vader", "d4rth", "Anakin Skywalker",
				"Death Star I", "dartvander@empire.com");

	}

	@Test
	public void cadastrarCaronas() {
		String sessaoID1 = "", sessaoID2 = "";
		String caronaID1="", caronaID2="", caronaID3="", caronaID4="", caronaID5="", caronaID6="";
		try {
			 sessaoID1 = sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			fail();
		}

		try {
			caronaID1 = carona.cadastrarCarona(sessaoID1, "Campina Grande",
					"João Pessoa", "02/06/2013", "12:00", "3");
		} catch (CaronaException e) {
			fail();
		}

		try {
			caronaID2 = carona.cadastrarCarona(sessaoID1, "Souza",
					"João Pessoa", "08/06/2013", "14:00", "2");
		} catch (CaronaException e) {
			fail();
		}

		try {
			caronaID3 = carona.cadastrarCarona(sessaoID1, "Campina Grande",
					"Patos", "25/06/2013", "8:00", "1");
		} catch (CaronaException e) {
			fail();
		}

		try {
			caronaID4 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1,
					"Açude Velho", "Shopping Boulevard", "Campina Grande",
					"04/06/2013", "20:00", "2");
		} catch (CaronaException e) {
			fail();
		}

		try {
			caronaID5 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1,
					"Praía do Cajá", "Villa São Paulo", "João Pessoa",
					"04/06/2013", "20:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1,
					"Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa",
					"04/06/2013", "14:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		
		// GetAtributos
		try {
			assertEquals("false", carona.getAtributoCarona(caronaID1, "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("false", carona.getAtributoCarona(caronaID2, "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}

		try {
			assertEquals("false", carona.getAtributoCarona(caronaID3, "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		try {
			assertEquals("true", carona.getAtributoCarona(caronaID4, "ehMunicipal"));
		} catch (CaronaException e1) { 
			fail();
		}

		try {
			assertEquals("true", carona.getAtributoCarona(caronaID5, "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		
		try {
			assertEquals("true", carona.getAtributoCarona(caronaID6, "ehMunicipal"));
		} catch (CaronaException e1) {
			fail();
		}
		
		
		try {
			sessaoID2 = sessao.abrirSessao("bill", "bilz@o");
		} catch (SessaoException e) {
			fail();
		}
		
		try {
			assertEquals("{"+caronaID1+"}", carona.localizarCarona(sessaoID2, "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{"+caronaID4+"}", caronaMunicipal.localizarCaronaMunicipal(sessaoID2, "Campina Grande", "Açude Velho", "Shopping Boulevard"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{"+caronaID5+","+caronaID6+"}", caronaMunicipal.localizarCaronaMunicipal(sessaoID1, "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{}", caronaMunicipal.localizarCaronaMunicipal(sessaoID1, "João Pessoa", "",  "Açude Velho"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{}", caronaMunicipal.localizarCaronaMunicipal(sessaoID1, "João Pessoa", "Parque da Criança",  ""));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID4+","+caronaID5+","+caronaID6+"}", caronaMunicipal.localizarCaronaMunicipal(sessaoID1, "João Pessoa", "",  ""));
		} catch (CaronaException e) {
			fail();
		}
		
		// Testar possiveis erros no sistema 
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal("", "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "14:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "14:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Parque Solon de Lucena", "", "João Pessoa", "04/06/2013", "14:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "", "14:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/13/2013", "14:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "30:00", "2");
			fail();
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "14:00", "duas");
			fail();
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		try {
			caronaID6 = caronaMunicipal.cadastrarCaronaMunicipal(sessaoID1, "Manaíra Shopping", "Parque Solon de Lucena", "João Pessoa", "04/06/2013", "14:00", "");
			fail();
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		try {
			caronaMunicipal.localizarCaronaMunicipal(sessaoID2, null, "Açude Velho", "Shopping Boulevard");
		} catch (CaronaException e) {
			assertEquals("Cidade inexistente", e.getMessage());
		}
		 
		try {
			caronaMunicipal.localizarCaronaMunicipal(sessaoID2, "", "Açude Velho", "Shopping Boulevard");
			fail();
		} catch (CaronaException e) {
			assertEquals("Cidade inexistente", e.getMessage());
		}
		try {
			caronaMunicipal.localizarCaronaMunicipal(null, "", "Açude Velho", "Shopping Boulevard");
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		try {
			caronaMunicipal.localizarCaronaMunicipal(sessaoID2, "Campina Grande", "()", "Shopping Boulevard");
			fail();
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			caronaMunicipal.localizarCaronaMunicipal(sessaoID2, "Campina Grande", "Açude Velho", "()");
			fail();
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			assertEquals("{"+caronaID5+","+caronaID6+"}", caronaMunicipal.localizarCaronaMunicipal(null, "João Pessoa"));
			fail();
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		try {
			assertEquals("{"+caronaID5+","+caronaID6+"}", caronaMunicipal.localizarCaronaMunicipal(sessaoID1, ""));
			fail();
		} catch (CaronaException e) {
			assertEquals("Cidade inválido", e.getMessage());
		}
	}

}
