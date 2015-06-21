package com.br.uepb.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * Essa Classe faz todas as operações de criação de usuário, getting e settings,
 * verificar se o login é válido e retorna nos atributos (origem, destino,
 * horario da corona e data).
 * 
 * 
 * @author Lucas Miranda e Bruno Clementino
 * @see Usuario
 */
public class UsuarioBusiness {

	public static Logger logger = Logger.getLogger(UsuarioBusiness.class);
	
	UsuarioDomain usuario;
	String mensagemErro = "";
	
	UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();


	/**
	 * Para criar o usuario, ele faz uma verificação se o usuário é válido ou
	 * não.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws UsuarioException
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws UsuarioException {
		
		usuario = new UsuarioDomain();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setEndereco(endereco);
		usuario.setEmail(email);

		if (ehUsuarioValido(usuario) && ehUsuarioNovo(usuario)) {
			usuarioDaoImp.save(usuario);
		} else {
			throw new UsuarioException(mensagemErro);
		}
	}

	/**
	 * Percorre a lista de {@link Usuario} e verifica se já existe um Login ou
	 * email iguais. Caso não tenha returna um <code>true</code>.
	 * 
	 * @param user
	 * @return {@link Boolean}
	 */
	private boolean ehUsuarioNovo(UsuarioDomain user) {
		for (UsuarioDomain usuario : getUsuarios()) {
			if (usuario.getLogin().equals(user.getLogin())) {
				mensagemErro = "Já existe um usuário com este login";
				return false;
			}
			if (usuario.getEmail().equals(user.getEmail())) {
				mensagemErro = "Já existe um usuário com este email";
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se o login, nome e e-mail estão <code>null</code> ou vázios.
	 * 
	 * @param usuario
	 * @return
	 */
	private boolean ehUsuarioValido(UsuarioDomain usuario) {
		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
			mensagemErro = "Login inválido";
			return false;
		}
		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			mensagemErro = "Nome inválido";
			return false;
		}
		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			mensagemErro = "Email inválido";
			return false;
		}
		return true;
	}

	/**
	 * Retorna o atributo que usuario precisar (nome, endereço ou email). Para
	 * isso ele chama os metoso ehLoginExistente e ehAtributoExistente.
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws UsuarioException
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws UsuarioException {

		if (login == null || login.trim().isEmpty()) {
			throw new UsuarioException("Login inválido");
		}
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new UsuarioException("Atributo inválido");
		}
		if (!ehLoginExistente(login)) {
			throw new UsuarioException("Usuário inexistente");
		}
		if (!ehAtributoExistente(atributo)) {
			throw new UsuarioException("Atributo inexistente");
		}

		for (UsuarioDomain usuario : getUsuarios()) {
			if (usuario.getLogin().equals(login)) {
				switch (atributo) {
					case "endereco":
						return usuario.getEndereco();
					case "nome":
						return usuario.getNome();
					case "email":
						return usuario.getEmail();
					}
			}
		}
		return "";
	}

	/**
	 * Verifica se os atributos passados fazem parte dos atributos que seram
	 * mostrados.
	 * 
	 * @param atributo
	 * @return {@link Boolean}
	 */
	private boolean ehAtributoExistente(String atributo) {
		if (atributo.equals("nome") || atributo.equals("endereco")
				|| atributo.equals("email")) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se o login existe. 
	 * 
	 * @param login
	 * @return {@link Boolean}
	 */
	private boolean ehLoginExistente(String login) { 
		for (UsuarioDomain usuario : getUsuarios()) {
			if (usuario.getLogin().equals(login))
				return true;
		}
		return false;
	}

	public List<UsuarioDomain> getUsuarios() {
		return usuarioDaoImp.list();
	}
	
	public UsuarioDomain getUsuarioDomain(String login){
		return usuarioDaoImp.getUsuario(login);
	}
	
}
