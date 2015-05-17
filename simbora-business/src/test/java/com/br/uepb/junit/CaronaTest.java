package com.br.uepb.junit;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void inicializar() {

		carona = new CaronaBusiness();
		usuarioBusiness = new UsuarioBusiness();
		sessaoBusiness = new SessaoBusiness();

		usuarioBusiness.encerrarSistema();
		sessaoBusiness.getSessoes().clear();
		carona.encerrarSistema();
	}

	@Test
	public void localizarCadastrarCarona() {
		usuarioBusiness.encerrarSistema();
		sessaoBusiness.getSessoes().clear();
		carona.getCaronas().clear();
		
		// Serão criados 3 usuarios. (cria usuario, abrir sessao e criarCarona)
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try { 
			assertEquals("{}", carona.localizarCarona("mark",
					"Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("{}", carona.localizarCarona("mark",
					"São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("{}", carona.localizarCarona("mark",
					"Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
	}

	@Test
	public void cadastrarCarona() {
		carona.caronas.clear();
		usuarioBusiness.encerrarSistema();
		sessaoBusiness.getSessoes().clear();
		
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try {
			assertEquals("0",
					carona.cadastrarCarona("mark", "Campina Grande",
							"João Pessoa", "23/06/2013", "16:00", "3"));
		} catch (CaronaException e) {
			fail();
		} 

		try {
			assertEquals("Campina Grande",
					carona.getAtributoCarona("0", "origem"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("João Pessoa",
					carona.getAtributoCarona("0", "destino"));
		} catch (CaronaException e) {
			fail();
		}

		try {
			assertEquals("Campina Grande - João Pessoa",
					carona.getTrajeto("0"));
		} catch (CaronaException e) {
			fail();
		}

		// Cadastrando a segunda carona
		try {
			assertEquals("1", carona.cadastrarCarona("mark",
					"Rio de Janeiro", "São Paulo", "31/05/2013", "08:00", "2"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("31/05/2013", carona.getAtributoCarona("1", "data"));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals("2", carona.getAtributoCarona("1", "vagas"));
		} catch (Exception e) {
			fail();
		}

		// Cadastrando a Terceira carona
		try {
			assertEquals("2", carona.cadastrarCarona("mark", "João Pessoa",
					"Campina Grande", "25/11/2026", "06:59", "4"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					carona.getCarona("2"));
		} catch (Exception e) {
			fail();
		}

		// Cadastro da quarta carona
		try {
			assertEquals("3", carona.cadastrarCarona("mark", "João Pessoa",
					"Lagoa Seca", "25/11/2026", "05:00", "4"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2026, as 05:00",
					carona.getCarona("3"));
		} catch (Exception e) {
			fail();
		}
		// Cadastro da quinto carona
		try {
			assertEquals("4", carona.cadastrarCarona("mark", "João Pessoa",
					"Lagoa Seca", "25/11/2017", "05:00", "4"));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00",
					carona.getCarona("4"));
		} catch (Exception e) {
			fail();
		}
		
		// Localizar caronas
		try {
			assertEquals("{}", carona.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{1}", carona.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{2}", carona.localizarCarona("mark", "João Pessoa", "Campina Grande"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{2,3,4}", carona.localizarCarona("mark", "João Pessoa", ""));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{1}", carona.localizarCarona("mark", "", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{0,1,2,3,4}", carona.localizarCarona("mark", "", ""));
		} catch (CaronaException e) {
			fail();
		}
		
	}

	@Test
	public void cadastrarCaronasTest() {
		usuarioBusiness.usuarios.clear();
		carona.encerrarSistema();
		
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
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
			carona.cadastrarCarona("mark", null, "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", " ", "São Paulo", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}

		try {
			carona.cadastrarCarona("mark", "Campina Grande", null, "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "São Paulo", " ", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", null, "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Patos", "João Pessoa", " ", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "30/02/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "31/04/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "32/12/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "32/12/2011", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		// Hora
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "23/06/2013", null, "3");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			carona.cadastrarCarona("mark", "Patos", "João Pessoa", "23/06/2013", "", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Patos", "João Pessoa", "23/06/2013", "seis", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		// Quantidade de vagas
		try {
			carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", null);
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("mark", "Patos", "João Pessoa", "31/05/2013", "08:00", "duas");
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
			carona.localizarCarona("mark", "-", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		//Destino errado
		try {
			carona.localizarCarona("mark", "Campina Grande", ".");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	
		sessaoBusiness.encerrarSessao("m@rk");

		// Limpar tudo do BD
		usuarioBusiness.encerrarSistema();
		carona.encerrarSistema();

		carona.caronas.clear();
		assertEquals(null, carona.getCarona());
	}

	@Test
	public void localizarCaronasTests () {
		usuarioBusiness.encerrarSistema();
		sessaoBusiness.getSessoes().clear();
		carona.encerrarSistema();
		
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try {
			assertEquals("{}", carona.localizarCarona("mark", "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Cadastrar Caronas
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013", "14:00", "4"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("1", carona.cadastrarCarona("mark", "São Francisco", "Palo Alto", "12/09/2013", "21:00", "2"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("2", carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "01/06/2013", "12:00", "1"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("3", carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "02/06/2013", "12:00", "3"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("4", carona.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "04/06/2013", "16:00", "2"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("5", carona.cadastrarCarona("mark", "Leeds", "Londres", "10/02/2013", "10:00", "3"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Localizar as contas
		
		try {
			assertEquals("{1}", carona.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			fail();
		}
		
		try {
			assertEquals("{}", carona.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			fail();
		}
		try {
			assertEquals("{2,3,4}", carona.localizarCarona("mark", "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			fail();
		}
		
		// Testar os possiveis erros
		try {
			carona.localizarCarona("mark", "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona.localizarCarona("mark", "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			carona.localizarCarona("mark", "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	}
	
}
