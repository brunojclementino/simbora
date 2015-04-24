package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.UsuarioDomain;

/**
 * Interface do usu�rio com as opera��es b�sicas do CRUD.
 * @author Lucas Miranda
 *
 */
public interface UsuarioDao {

	public void save(UsuarioDomain usuario);
	public UsuarioDomain getUsuario(String login);
	public List<UsuarioDomain> list();
	public void remove(UsuarioDomain usuario);
	public void update(UsuarioDomain usuario);
	public void excluirTudo();
	 
}
