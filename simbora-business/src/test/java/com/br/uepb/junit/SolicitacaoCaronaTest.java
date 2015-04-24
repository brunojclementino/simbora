package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.UsuarioDaoImp;
public class SolicitacaoCaronaTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness carona;

	@Before
	public void inicializar() {
		new CaronaDaoImp().excluirTudo();
		new UsuarioDaoImp().excluirTudo();
		
		usuario = new UsuarioBusiness();
		carona = new CaronaBusiness();
		sessao = new SessaoBusiness();
		
		carona.zerarSistema();
		usuario.zerarSistema();
	}

	@Test
	public void testA_criarUsuarioErro() {
		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",	"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("Usu�rio existente", e.getMessage());
		}

		// Abrir a sessao!
		try {
			Assert.assertEquals("mark", sessao.abrirSessao("mark", "m@rk"));
		} catch (UsuarioException e) {
			assertEquals("Login inv�lido", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void testB_criarCarona() {		
		
		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",	"Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			assertEquals("Usu�rio existente", e.getMessage());
		}

		// Abre a sess�o
		try {
			assertEquals("mark", sessao.abrirSessao("mark", "m@rk"));
		} catch (SessaoException e1) {
			assertEquals("Usu�rio inexistente", e1.getMessage());
		}

		// Erro no IDCarona == null
		try {
			assertEquals("0", carona.cadastrarCarona(null, "Campina Grande",
					"Jo�o Pessoa", "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Sess�o inv�lida", e.getMessage());
		}

		// Erro no IDCarona que n�o exista
		try {
			assertEquals("0",
					carona.cadastrarCarona("Batman", "Campina Grande",
							"Jo�o Pessoa", "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Sess�o inexistente", e.getMessage());
		}

		// Erro na origem == null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", null,
					"Jo�o Pessoa", "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Origem inv�lida", e.getMessage());
		}

		// Erro no destino == null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					null, "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Destino inv�lido", e.getMessage());
		}

		// Erro na data == null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", null, "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Data inv�lida", e.getMessage());
		}

		// Erro na data == null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "dia deus dar�", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Data inv�lida", e.getMessage());
		}

		// Erro no Horario == null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "22/12/2014", null, "4"));
		} catch (Exception e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}

		// Erro no Horario == String
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "22/12/2014", "22 Horas", "4"));
		} catch (Exception e) {
			assertEquals("Hora inv�lida", e.getMessage());
		}

		// Erro na quantidade de vaga = string
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "22/12/2014", "22:00", "Quatro"));
		} catch (Exception e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}

		// Erro na quantidade de vaga = null
		try {
			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "22/12/2014", "22:00", null));
		} catch (Exception e) {
			assertEquals("Vaga inv�lida", e.getMessage());
		}

		// Erro no IDCarona, dever� ser retornada o erro que a sess�o n�o
		// existe!
		try {

			assertEquals("0", carona.cadastrarCarona("mark", "Campina Grande",
					"Jo�o Pessoa", "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Sess�o inexistente", e.getMessage());
		}
	}

	@Test
	public void testC_localizarCaronasTest() {

		try {

			assertEquals("0", carona.cadastrarCarona("luc@s", "Campina Grande",
					"Jo�o Pessoa", "22/04/2015", "10:00", "4"));
		} catch (Exception e) {
			assertEquals("Sess�o inexistente", e.getMessage());
		}

		// T�m-se dois cadastro m@rk, luc@s e de bruno!
		// origem esta String vazia!
		try {
			carona.localizarCarona("mark", "", "Jo�o Pessoa");
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}
