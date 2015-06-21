package com.br.uepb.junit;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.accept.SimboraEasyAccept;
import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;

/**
 * Cria os tests referentes a carona. Utiliza do Anotation FixMethodOrder para
 * determina a ordem de execusão dos metodos (Essa fun��o executa os metodos em
 * ordem alfabetica, por isso que os metodos estão com o prefixo test[LETRA]_
 * 
 * @author Lucas Miranda e Bruno Clementino
 */
public class CaronaTest {

	CaronaBusiness carona;
	UsuarioBusiness usuarioBusiness;
	SessaoBusiness sessaoBusiness;
	String sessaoID1 = "";

	@Before
	public void inicializar() throws SessaoException {

		carona = new CaronaBusiness();
		usuarioBusiness = new UsuarioBusiness();
		sessaoBusiness = new SessaoBusiness();

		new SimboraEasyAccept().zerarSistema();
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg", "Palo Alto, California", "mark@facebook.com");
		
		sessaoID1 = sessaoBusiness.abrirSessao("mark", "m@rk");
		
	}

	@Test
	public void localizarCadastrarCarona() {

		try { 
			assertEquals("{}", carona.localizarCarona(sessaoID1,
					"Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1,
					"São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1,
					"Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
	}

	@Test
	public void cadastrarCarona() {
		String caronaID1 = "",caronaID2 = "",caronaID3 = "",caronaID4 = "",caronaID5="";

		try {
			caronaID1 = carona.cadastrarCarona(sessaoID1, "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			fail();
		} 

		try {
			assertEquals("Campina Grande",
					carona.getAtributoCarona(caronaID1, "origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					carona.getAtributoCarona(caronaID1, "destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Campina Grande - João Pessoa",
					carona.getTrajeto(caronaID1));
		} catch (CaronaException e) {
			fail();
		}

		// Cadastrando a segunda carona
		try {
			caronaID2 =  carona.cadastrarCarona(sessaoID1,
					"Rio de Janeiro", "São Paulo", "31/05/2013", "08:00", "2");
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("31/05/2013", carona.getAtributoCarona(caronaID2, "data"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("2", carona.getAtributoCarona(caronaID2, "vagas"));
		} catch (Exception e) {
			fail();
		}

		// Cadastrando a Terceira carona
		try {
			caronaID3=carona.cadastrarCarona(sessaoID1, "João Pessoa",
					"Campina Grande", "25/11/2026", "06:59", "4");
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					carona.getCarona(caronaID3));
		} catch (Exception e) {
			fail();
		}

		// Cadastro da quarta carona
		try {
			caronaID4=carona.cadastrarCarona(sessaoID1, "João Pessoa",
					"Lagoa Seca", "25/11/2026", "05:00", "4");
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2026, as 05:00",
					carona.getCarona(caronaID4));
		} catch (Exception e) {
			fail();
		}
		// Cadastro da quinto carona
		try {
			caronaID5=carona.cadastrarCarona(sessaoID1, "João Pessoa",
					"Lagoa Seca", "25/11/2017", "05:00", "4");
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00",
					carona.getCarona(caronaID5));
		} catch (Exception e) {
			fail();
		}
		
		// Localizar caronas
		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1, "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID2+"}", carona.localizarCarona(sessaoID1, "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID3+"}", carona.localizarCarona(sessaoID1, "João Pessoa", "Campina Grande"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID3+","+caronaID4+","+caronaID5+"}", carona.localizarCarona(sessaoID1, "João Pessoa", ""));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID2+"}", carona.localizarCarona(sessaoID1, "", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID1+","+caronaID2+","+caronaID3+","+caronaID4+","+caronaID5+"}", carona.localizarCarona(sessaoID1, "", ""));
		} catch (CaronaException e) {
			fail();
		}
		
	}

	@Test
	public void cadastrarCaronasTest() {
		usuarioBusiness.getUsuarios().clear();
		
		try {
			carona.cadastrarCarona(null, "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("test", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inexistente", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, null, "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, " ", "São Paulo", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}

		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", null, "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "São Paulo", " ", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", null, "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Patos", "João Pessoa", " ", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "30/02/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "32/12/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		// Hora
		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "23/06/2013", null, "3");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			carona.cadastrarCarona(sessaoID1, "Patos", "João Pessoa", "23/06/2013", "", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Patos", "João Pessoa", "23/06/2013", "seis", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		// Quantidade de vagas
		try {
			carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "23/06/2013", "16:00", null);
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona(sessaoID1, "Patos", "João Pessoa", "31/05/2013", "08:00", "duas");
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		// Atributos da corona
		try {
			carona.getAtributoCarona(null, "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		
		try {
			carona.getAtributoCarona("  ", "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		
		try {
			carona.getAtributoCarona("xpto", "destino");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		try {
			carona.getAtributoCarona("2", null);
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			carona.getAtributoCarona("2", " ");
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			carona.getAtributoCarona("2", "xpto");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		// getCarona
		try {
			carona.getCarona("");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		try {
			carona.getCarona(null);
		} catch (CaronaException e) {
			assertEquals("Carona Inválida", e.getMessage());
		}
		
		try {
			carona.getCarona("	");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		// getTrajeto
		try {
			carona.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inválida", e.getMessage());
		}
		try {
			carona.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			carona.getTrajeto("xpto");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		
		//Localizar carona - Tudo tem que dá erro!
		try {
			carona.localizarCarona(sessaoID1, "-", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		//Destino errado
		try {
			carona.localizarCarona(sessaoID1, "Campina Grande", ".");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	
		sessaoBusiness.encerrarSessao("m@rk");


		assertEquals(null, carona.getCarona());
	}

	@Test
	public void localizarCaronasTests () {


		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1, "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1, "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1, "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Cadastrar Caronas
		String caronaID1 = "",caronaID2= "",caronaID3= "",caronaID4= "",caronaID5= "",caronaID6= "";
		try {
			caronaID1 = carona.cadastrarCarona(sessaoID1, "Cajazeiras", "Patos", "20/07/2013", "14:00", "4");
		} catch (CaronaException e) {
			fail();
		}
		try {
			caronaID2 = carona.cadastrarCarona(sessaoID1, "São Francisco", "Palo Alto", "12/09/2013", "21:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		try {
			caronaID3 = carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "01/06/2013", "12:00", "1");
		} catch (CaronaException e) {
			fail();
		}
		try {
			caronaID4=carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "02/06/2013", "12:00", "3");
		} catch (CaronaException e) {
			fail();
		}
		try {
			caronaID5=carona.cadastrarCarona(sessaoID1, "Campina Grande", "João Pessoa", "04/06/2013", "16:00", "2");
		} catch (CaronaException e) {
			fail();
		}
		try {
			caronaID6=carona.cadastrarCarona(sessaoID1, "Leeds", "Londres", "10/02/2013", "10:00", "3");
		} catch (CaronaException e) {
			fail();
		}
		
		// Localizar as contas
		
		try {
			assertEquals("{"+caronaID2+"}", carona.localizarCarona(sessaoID1, "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona(sessaoID1, "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{"+caronaID3+","+caronaID4+","+caronaID5+"}", carona.localizarCarona(sessaoID1, "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Testar os possiveis erros
		try {
			carona.localizarCarona(sessaoID1, "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona.localizarCarona(sessaoID1, "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona(sessaoID1, "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	}
	
	@Test
	public void getCaronaUsuarioTest() throws CaronaException {
		String caronaID1, caronaID2;
		caronaID1 = carona.cadastrarCarona(sessaoID1, "Cajazeiras", "Patos", "20/07/2013", "14:00", "4");
		
		assertEquals(caronaID1, carona.getCaronaUsuario(sessaoID1, "1"));
		
		//É esperado vazio porque o usuario só tem uma carona cadastrada
		assertEquals("", carona.getCaronaUsuario(sessaoID1, "2"));
		
		caronaID2 = carona.cadastrarCarona(sessaoID1, "Alagoa Nova", "Campina Grande", "12/06/2015", "11:00", "3");
		
		assertEquals("{"+caronaID1+","+caronaID2+"}", carona.getTodasCaronasUsuario(sessaoID1));
		
	}
	
	@Test
	public void caronaPreferencialTest() throws CaronaException{
		String caronaID1, caronaID2;
		caronaID1 = carona.cadastrarCarona(sessaoID1, "Cajazeiras", "Patos", "20/07/2013", "14:00", "4");
		try {
			carona.definirCaronaPreferencial("5");
		} catch (CaronaException e) {
			assertEquals("Carona inválida", e.getMessage());
		}
		
		try {
			carona.definirCaronaPreferencial(caronaID1);
		} catch (Exception e) {
			fail();
		}
		
		try {
			carona.isCaronaPreferencial("");
		} catch (Exception e) {
			assertEquals("Carona inválida", e.getMessage());
		}

		assertEquals(true, carona.isCaronaPreferencial(caronaID1));
		
		try {
			carona.getUsuariosPreferenciaisCarona("");
		} catch (Exception e) {
			assertEquals("Carona inexistente", e.getMessage());
		}
		
		assertEquals("[]", carona.getUsuariosPreferenciaisCarona(caronaID1));
	}
	
}
