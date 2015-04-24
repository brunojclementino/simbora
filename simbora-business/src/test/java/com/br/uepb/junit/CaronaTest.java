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
 * determina a ordem de execu��o dos metodos (Essa fun��o executa os metodos em
 * ordem alfabetica, por isso que os metodos est�o com o prefixo test[LETRA]_
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
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

	}

	@Test
	public void testA_CadastrarCarona() throws Exception {
		// Ser�o criados 3 usuarios. (cria usuario, abrir sessao e criarCarona)
		usuarioBusiness.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		try {
			assertEquals("mark", sessaoBusiness.abrirSessao("mark", "m@rk"));
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}

		try {
			String v = business.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "27/10/2014", "02:09", "4");
			System.out.println("idCarona_Mark: " + v);
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Cria��o o 2� usu�rio
		usuarioBusiness.criarUsuario("thiago", "thi@go", "Thiago Batista",
				"Rua", "thiagobatista@gmail.com");
		try {
			sessaoBusiness.abrirSessao("thiago", "thi@go");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		try {
			String v = business.cadastrarCarona("thiago", "Campina Grande",
					"Jo�o Pessoa", "22/10/2014", "22:49", "3");
			System.out.println("IDCarona_thiago: " + v);
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Criando o 3� Usuario
		try {
		usuarioBusiness.criarUsuario("lucas", "luc@s", "Lucas Miranda",
				"Rua", "lucas@gmail.com");
		} catch (UsuarioException e) {
			assertEquals("Usu�rio j� existe", e.getMessage());
		}
		try {
			sessaoBusiness.abrirSessao("lucas", "luc@s");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		try {
			business.cadastrarCarona("lucas", "Campina Grande",
					"Jo�o Pessoa", "22/10/2014", "03:00", "3");
		} catch (Exception e) {
			assertEquals("Sess�o inexistente", e.getMessage());
		}

		// Criar usu�rio: Querendo uma carona
		usuarioBusiness.criarUsuario("bruno", "bruno2", "Bruno Clementino",
				"Rua", "bruno@gmail.com");
		try {
			sessaoBusiness.abrirSessao("bruno", "bruno2");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		
		// Cadastro com erros!
		try {
			business.cadastrarCarona("", "Adustina", "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (SessaoException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}
		// Oriem = "" ou null
		try {
			business.cadastrarCarona("bruno", "", "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		try {
			business.cadastrarCarona("bruno", null, "Campina Grande", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// Destino = "" ou null
		try {
			business.cadastrarCarona("bruno", "Adustina", " ", "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		try {
			business.cadastrarCarona("bruno", "Adustina", null, "01/04/2016", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// Data = "" ou null
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", "", "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inv�lida", e.getMessage());
		}
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", null, "10:00", "3");
		} catch (CaronaException e) {
			assertEquals("Data inv�lida", e.getMessage());
		}
		// Hora = "" ou null
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "", "3");
		} catch (CaronaException e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", null, "3");
		} catch (CaronaException e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}
		// Quantidade de vagas = "" ou null
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "10:00", "");
		} catch (CaronaException e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}
		try {
			business.cadastrarCarona("bruno", "Adustina", "Campina Grande", "01/04/2016", "10:00", null);
		} catch (CaronaException e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}
		
		
	}

	// Verificar se localiza a carona com a sess�o null
	@Test
	public void testB_LocalizarCaronaNull() throws Exception {
		// Localizar a sessao. Tests com o idSessao = null
		try {
			assertEquals("{}", business.localizarCarona(null,
					"Campina Grande", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}

		// origem = !
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "!", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = ()
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "()", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = -
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "-", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// origem = !?
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "!?", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}
		// destino = .
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "Campina Grande", "."));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// destino = ()
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "Campina Grande", "()"));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		// destino = !?
		try {
			assertEquals("{}",
					business.localizarCarona("bruno", "Campina Grande", "!?"));
		} catch (CaronaException e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}
		
		try {
			assertEquals("{1,2}", business.localizarCarona("bruno",
					"Campina Grande", "Jo�o Pessoa"));
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}

		// idSessao = null
		try {
			assertEquals("{}", business.localizarCarona(null,
					"S�o Francisco", "Palo Alto"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}

		try {
			assertEquals("{}", business.localizarCarona("", "S�o Francisco",
					"Palo Alto"));
		} catch (CaronaException e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}
	}

	@Test
	public void testC_origemDestino() {
		try {
			assertEquals("{0,1,2}", business.localizarCarona("bruno", "", "Jo�o Pessoa"));
		} catch (Exception e) {
			assertEquals(null, e.getMessage());
		}
		try {
			business.localizarCarona("bruno", "Campina Grande", "");
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}

		try {
			business.localizarCarona("bruno", "Cabaceiras", "Iguat�");
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
		try {
			business.localizarCarona("bruno", "", "");
		} catch (Exception e) {
			e.getMessage();
		}

		
		try {
			assertEquals("{1,2,3}", business.localizarCarona("bruno", null, null));
		} catch (Exception e) {
			assertEquals(null, e.getMessage());
		}

		try {
			assertEquals("{0,1,2}",business.localizarCarona("bruno", "", ""));
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testD_atributos() {
		// IdCarona == ""
		try {
			business.getAtributoCarona("", "Origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona � inv�lido", e.getMessage());
		}

		// IdCarona = null
		try {
			business.getAtributoCarona(null, "Origem");
		} catch (CaronaException e) {
			assertEquals("Identificador do carona � inv�lido", e.getMessage());
		}

		// IdCarona inexistente
		try {
			business.getAtributoCarona("9", "nome");
		} catch (CaronaException e) {
			assertEquals("Item inexistente", e.getMessage());
		}

		// Atributo = ""
		try {
			business.getAtributoCarona("0", "");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = null
		try {
			business.getAtributoCarona("0", null);
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = Origem
		try {
			business.getAtributoCarona("1", "origem");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}

		// Atributo = destino
		try {
			business.getAtributoCarona("0", "destino");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = data
		try {
			business.getAtributoCarona("0", "data");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = hora
		try {
			business.getAtributoCarona("0", "vagas");
		} catch (CaronaException e) {
			assertEquals("Atributo inv�lido", e.getMessage());
		}

		// Atributo = hora
		try {
			business.getAtributoCarona("0", "Quanto Custa");
		} catch (CaronaException e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}
	}

	@Test
	public void testE_trajeto() {
		// IdCarona = null
		try {
			business.getTrajeto(null);
		} catch (CaronaException e) {
			assertEquals("Trajeto Inv�lida", e.getMessage());
		}

		// IdCarona = ""
		try {
			business.getTrajeto("");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}

		// IdCarona = Alfanumerico
		try {
			business.getTrajeto("f");
		} catch (CaronaException e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			business.getTrajeto("2");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}
		try {
			business.getTrajeto("33");
		} catch (CaronaException e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testF_verCarona() {
		// IdCarona = "" ou null
		try {
			business.getCarona(null);
		} catch (Exception e) {
			assertEquals("Carona Inv�lida", e.getMessage());
		}
		try {
			business.getCarona("");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		try {
			business.getCarona("100");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
		try {
			assertEquals("S�o Paulo para Campina Grande, no dia 30/11/2015, as 22:00", business.getCarona("0"));
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}		

	}

	@Test
	public void testG_getCaronaTest() {
		// IDCarona existente!
		try {
			business.getCarona("bruno");
		} catch (CaronaException e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}

		try {
			business.getCarona("9");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}

		try {
			business.getCarona("k");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
	}

	@Test
	public void testH_encerrarSessao() {

		sessaoBusiness.encerrarSessao("bruno");
		sessaoBusiness.encerrarSessao("m@rk");

		// Limpar tudo do BD
		usuarioBusiness.zerarSistema();
		business.zerarSistema();
	}

	@Test
	public void testI_ZerarSistema() {
		business.zerarSistema();
		assertEquals(null, business.getCarona());
	}
}
