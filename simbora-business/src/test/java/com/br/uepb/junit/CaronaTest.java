package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.domain.CaronaDomain;

/**
 * Cria os tests referentes a carona. Utiliza do Anotation FixMethodOrder para
 * determina a ordem de execusão dos metodos (Essa fun��o executa os metodos em
 * ordem alfabetica, por isso que os metodos estão com o prefixo test[LETRA]_
 * 
 * @author Lucas Miranda e Bruno Clementino
 */
public class CaronaTest {

	CaronaDomain carona;
	CaronaBusiness business;
	UsuarioBusiness usuarioBusiness;
	SessaoBusiness sessaoBusiness;

	@Before
	public void test() {

		new CaronaDaoImp().excluirTudo();
		new UsuarioDaoImp().excluirTudo();

		carona = new CaronaDomain();
		business = new CaronaBusiness();
		usuarioBusiness = new UsuarioBusiness();
		sessaoBusiness = new SessaoBusiness();

		usuarioBusiness.usuarios.clear();
		sessaoBusiness.getSessoes().clear();
		business.getCaronas().clear();
	}

	@Test
	public void localizar_cadastrarCarona() {
		usuarioBusiness.usuarios.clear();
		sessaoBusiness.getSessoes().clear();
		business.getCaronas().clear();
		
		// Serão criados 3 usuarios. (cria usuario, abrir sessao e criarCarona)
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try {
			assertEquals("{}", business.localizarCarona("mark",
					"Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
			e.printStackTrace();
		}

		try {
			assertEquals("{}", business.localizarCarona("mark",
					"São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
			e.printStackTrace();
		}

		try {
			assertEquals("{}", business.localizarCarona("mark",
					"Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void cadastrarCarona() {
		business.caronas.clear();
		usuarioBusiness.usuarios.clear();

		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try {
			assertEquals("0",
					business.cadastrarCarona("mark", "Campina Grande",
							"João Pessoa", "23/06/2013", "16:00", "3"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		} catch (Exception e) {

		}

		try {
			assertEquals("Campina Grande",
					business.getAtributoCarona("0", "origem"));
		} catch (CaronaException e) {
			System.out.println(e.getMessage());
		}

		try {
			assertEquals("João Pessoa",
					business.getAtributoCarona("0", "destino"));
		} catch (CaronaException e) {
			System.out.println(e.getMessage());
		}

		try {
			assertEquals("Campina Grande - João Pessoa",
					business.getTrajeto("0"));
		} catch (CaronaException e) {
			System.out.println(e.getMessage());
		}

		// Cadastrando a segunda carona
		try {
			assertEquals("1", business.cadastrarCarona("mark",
					"Rio de Janeiro", "São Paulo", "31/05/2013", "08:00", "2"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			assertEquals("31/05/2013", business.getAtributoCarona("1", "data"));
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			assertEquals("2", business.getAtributoCarona("1", "vagas"));
		} catch (Exception e) {
			e.getMessage();
		}

		// Cadastrando a Terceira carona
		try {
			assertEquals("2", business.cadastrarCarona("mark", "João Pessoa",
					"Campina Grande", "25/11/2026", "06:59", "4"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(
					"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59",
					business.getCarona("2"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Cadastro da quarta carona
		try {
			assertEquals("3", business.cadastrarCarona("mark", "João Pessoa",
					"Lagoa Seca", "25/11/2026", "05:00", "4"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2026, as 05:00",
					business.getCarona("3"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Cadastro da quinto carona
		try {
			assertEquals("4", business.cadastrarCarona("mark", "João Pessoa",
					"Lagoa Seca", "25/11/2017", "05:00", "4"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			assertEquals(
					"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00",
					business.getCarona("4"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Localizar caronas
		try {
			assertEquals("{}", business.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{1}", business.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{2}", business.localizarCarona("mark", "João Pessoa", "Campina Grande"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{2,3,4}", business.localizarCarona("mark", "João Pessoa", ""));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{1}", business.localizarCarona("mark", "", "São Paulo"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{0,1,2,3,4}", business.localizarCarona("mark", "", ""));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
	}

	@Test
	public void cadastrarCaronasTest() {
		usuarioBusiness.usuarios.clear();
		business.caronas.clear();
		
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			business.cadastrarCarona(null, "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("test", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Sessão inexistente", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", null, "João Pessoa", "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", " ", "São Paulo", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}

		try {
			business.cadastrarCarona("mark", "Campina Grande", null, "23/06/2013", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "São Paulo", " ", "31/05/2013", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", null, "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Patos", "João Pessoa", " ", "08:00", "2");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "30/02/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "31/04/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "32/12/2012", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "32/12/2011", "16:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inválida", e.getMessage());
		}
		
		// Hora
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "23/06/2013", null, "3");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			business.cadastrarCarona("mark", "Patos", "João Pessoa", "23/06/2013", "", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Patos", "João Pessoa", "23/06/2013", "seis", "2");
		} catch (CaronaException e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		
		// Quantidade de vagas
		try {
			business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "23/06/2013", "16:00", null);
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		try {
			business.cadastrarCarona("mark", "Patos", "João Pessoa", "31/05/2013", "08:00", "duas");
		} catch (CaronaException e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		
		// Atributos da corona
		try {
			business.getAtributoCarona(null, "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		
		try {
			business.getAtributoCarona("  ", "origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona é inválido", e.getMessage());
		}
		
		try {
			business.getAtributoCarona("xpto", "destino");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		try {
			business.getAtributoCarona("2", null);
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			business.getAtributoCarona("2", " ");
		} catch (CaronaException e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
		
		try {
			business.getAtributoCarona("2", "xpto");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}
		
		// getCarona
		try {
			business.getCarona("");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		try {
			business.getCarona(null);
		} catch (CaronaException e) {
			assertEquals("Carona Inválida", e.getMessage());
		}
		
		try {
			business.getCarona("	");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		
		// getTrajeto
		try {
			business.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inválida", e.getMessage());
		}
		try {
			business.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			business.getTrajeto("xpto");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		
		//Localizar carona - Tudo tem que dá erro!
		try {
			business.localizarCarona("mark", "-", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		//Destino errado
		try {
			business.localizarCarona("mark", "Campina Grande", ".");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	}
	
	@Test
	public void encerrarSessao() {

		sessaoBusiness.encerrarSessao("m@rk");

		// Limpar tudo do BD
		usuarioBusiness.zerarSistema();
		business.zerarSistema();
	}

	@Test
	public void zerarSistema() {
		business.zerarSistema();
		assertEquals(null, business.getCarona());
	}

	@Test
	public void test_US03 () {
		usuarioBusiness.usuarios.clear();
		sessaoBusiness.getSessoes().clear();
		business.getCaronas().clear();
		
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			sessaoBusiness.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			assertEquals("Login inválido", e.getMessage());
		}

		try {
			assertEquals("{}", business.localizarCarona("mark", "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		try {
			assertEquals("{}", business.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		try {
			assertEquals("{}", business.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Cadastrar Caronas
		try {
			assertEquals("0", business.cadastrarCarona("mark", "Cajazeiras", "Patos", "20/07/2013", "14:00", "4"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("1", business.cadastrarCarona("mark", "São Francisco", "Palo Alto", "12/09/2013", "21:00", "2"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("2", business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "01/06/2013", "12:00", "1"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("3", business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "02/06/2013", "12:00", "3"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("4", business.cadastrarCarona("mark", "Campina Grande", "João Pessoa", "04/06/2013", "16:00", "2"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("5", business.cadastrarCarona("mark", "Leeds", "Londres", "10/02/2013", "10:00", "3"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Localizar as contas
		
		try {
			assertEquals("{1}", business.localizarCarona("mark", "São Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		try {
			assertEquals("{}", business.localizarCarona("mark", "Rio de Janeiro", "São Paulo"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		try {
			assertEquals("{2,3,4}", business.localizarCarona("mark", "Campina Grande", "João Pessoa"));
		} catch (CaronaException e) {
			e.getMessage();
		}
		
		// Testar os possiveis erros
		try {
			business.localizarCarona("mark", "()", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "!", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "!?", "João Pessoa");
		} catch (CaronaException e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			business.localizarCarona("mark", "Campina Grande", "()");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			business.localizarCarona("mark", "Campina Grande", "!?");
		} catch (CaronaException e) {
			assertEquals("Destino inválido", e.getMessage());
		}
	}
	
}
