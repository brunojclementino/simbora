package com.br.uepb.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

public class CadastroRelampago {

	UsuarioBusiness usuario;
	SessaoBusiness sessao;
	CaronaBusiness caronaBusiness;
	PerfilBusiness perfil;
	
	@Before 
	public void before() {
		usuario = new UsuarioBusiness();
		sessao = new SessaoBusiness();
		caronaBusiness = new CaronaBusiness();
		perfil = new PerfilBusiness();
		
		usuario.encerrarSistema();
		caronaBusiness.encerrarSistema();
		sessao.getSessoes().clear();
		sessao.getUsuarios().clear();
		perfil.zerarSistema();
		try {
			usuario.criarUsuario("mark", "m@rk", "Mark Zuckeberg", "Palo Alto, California", "mark@facebook.com");
		} catch (UsuarioException e) {
			fail();
		}
		
		try {
			sessao.abrirSessao("mark", "m@rk");
		} catch (SessaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test() {
		perfil.cadastrarCaronaRelampago("mark", "Campina Grande", "João Pessoa", "23/06/2013", "26/06/2013", "16:00", "3");
	}

}
