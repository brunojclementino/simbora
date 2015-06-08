package com.br.uepb.dao.impl;

import org.springframework.stereotype.Service;

import com.br.uepb.dao.UserDAO;
import com.br.uepb.domain.UsuarioDom;

@Service
public class UserDAOImpl implements UserDAO {

	@Override
	public UsuarioDom getUser(String cpf) {
		UsuarioDom ud = new UsuarioDom();
		ud.setCpf("0538953958395839");
		ud.setNome("noca");
		return ud;
	}

}
