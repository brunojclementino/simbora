package com.br.uepb.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;

public class SolicitacaoPontoEncontroTest {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	SolicitacaoVagasBusiness solicitarVagas;
	CaronaBusiness carona;
	SolicitacaoPontoDeEncontroBusiness pontoEncontro;
	
	@Before
	public void inicilizarTest() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		solicitarVagas = new SolicitacaoVagasBusiness();
		carona = new CaronaBusiness();
		
		usuario.usuarios.clear();
		solicitarVagas.solicitacoesVagas.clear();
		
	}
	
	@Test
	public void test() {
		
		/**
		 * Os Tests de cria��o de usu�rio e abrir sess�o foram feitas em UsuarioTest
		 */
		usuario.criarUsuario("hebert", "hebertPS", "Hebert Viana",
				"S�o Paulo", "hebert@facebook.com");
		try {
			sessao.abrirSessao("hebert", "hebertPS");
		} catch (SessaoException e) {
			assertEquals("Usu�rio inexistente", e.getMessage());
		}
		
		try {
			carona.cadastrarCarona("hebert", "S�o Paulo", "Campina Grande", "30/11/2015", "22:00", "4");
		} catch (Exception e) {
			assertEquals("Sess�o inexis tente", e.getMessage());
		}

		
		solicitarVagas.solicitarVaga("hebert", "2");
		
	
	}

}
