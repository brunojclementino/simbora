package com.br.uepb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;


/**
 * Usuário básico. Ele tem como fun��es: 
 * <li>logar ao sistema</li>
 * <li>buscar carona</li>
 * <li>solicitar carona</li>
 * <li>sugerir local da carona</li>
 * 
 * @author Lucas Miranda
 * @author Bruno José Clementino
 *
 */

@Entity
@Table(name="USUARIO")
public class UsuarioDomain implements Serializable{

	final static Logger logger = Logger.getLogger(UsuarioDomain.class);
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "login")
	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	/** 
	 * Método contrutor Default.
	 */
	public UsuarioDomain() {

	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
