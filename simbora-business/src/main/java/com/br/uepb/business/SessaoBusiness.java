package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.constants.SessaoException;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * Controla a {@link Sessao}. M�todos: <li>abrirSessao</li> <li>encerrarSessao</li>
 * <li>getSessao</li> <li>setUsuarios</li>
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SessaoBusiness {

	private static List<SessaoDomain> sessoes = new ArrayList<SessaoDomain>();
	/**
	 * Foi necessario colocar <code>static</code> para n�o ser necess�rio
	 * instanciar outra {@link List}.
	 */
	private static List<UsuarioDomain> usuarios = new UsuarioDaoImp().list();

	/**
	 * Ao abrir a sess�o � necess�rio o login e senha do usu�rio. Esse metodo
	 * retornar� o idSessao que � o login do usu�rio. Caso os parametros tenham
	 * sido inseridos errados ser� gerado um erro de <b>Usu�rio inexistente</b>
	 *
	 * <br>
	 * 
	 * @see Usuario
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws SessaoException
	 *
	 */
	public String abrirSessao(String login, String senha)
			throws SessaoException {
		for (UsuarioDomain usuario : usuarios) {
			if (usuario.getLogin().equals(login)
					&& usuario.getSenha().equals(senha)) {
				SessaoDomain ss = new SessaoDomain();
				ss.setIdSessao(usuario.getLogin());
				ss.setIdUsuario(login);
				sessoes.add(ss);
				return ss.getIdSessao();
			} else if (login == null || login.isEmpty()
					|| usuario.getLogin().equals(login)
					|| usuario.getSenha().equals(senha)) {
				throw new SessaoException("Login inv�lido");
			}
		}
		throw new SessaoException("Usu�rio inexistente");
	}

	/**
	 * Encerra a Sess�o, para isso ele busca na {@link List} de {@link Usuario}
	 * e quando achar o usu�rio correspondente ao login remove ele da lista de
	 * {@link Sessao}.
	 * 
	 * @param login
	 */
	public void encerrarSessao(String login) {
		for (SessaoDomain sessao : sessoes) {
			if (sessao.getIdUsuario().equals(login)) {
				sessoes.remove(sessao);
				break;
			}
		}
	}

	/**
	 * Verifica se a idSessao passada existe.
	 * 
	 * @param idSessao
	 * @return {@link Boolean}
	 */
	public static boolean hasSessao(String idSessao) {
		for (SessaoDomain sessao : sessoes) {
			if (sessao.getIdSessao().equals(idSessao)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a variavel da List<Sessao>.
	 * 
	 * @return vari�vel da List<Sessao>
	 */
	public static List<SessaoDomain> getSessoes() {
		return sessoes;
	}

	/**
	 * Retorna a vari�vel da List<Usuario>
	 * 
	 * @return Variavel da lista de usuarios.
	 */
	public static List<UsuarioDomain> getUsuarios() {
		return usuarios;
	}

	/**
	 * Adiciona uma Lista de usuarios na lista privada da classe.
	 * (List<Usuario>).
	 * 
	 * @param usuarios
	 */
	public static void setUsuarios(List<UsuarioDomain> usuarios) {
		SessaoBusiness.usuarios = usuarios;
	}

}
