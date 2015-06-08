package com.br.uepb.junit;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.UsuarioBusiness;

public class UserTest {

	UsuarioBusiness user;
	
	@Before
	public void carrear() {
		user = new UsuarioBusiness();
	}
	
	@Test
	public void test() {
		user.criarUsuario("Lucas", "Lucas", "Lucas Miranda", "Rua", "lucas@gmail.com");
	}

}
