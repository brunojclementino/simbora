package com.br.uepb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioDom {

	@NotNull(message = "O nome não pode ser nulo")
	@Size(min=2, max=30, message="Tamanho inválido de nome")
	private String nome;
	
	@NotNull(message="Cpf não pode ser nulo")
	@Pattern(regexp = "^[0-9]{1,3}$")
	private String cpf;

	private List<UsuarioDom> lstUsers;
	
	public UsuarioDom() {
		lstUsers = new ArrayList<UsuarioDom>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<UsuarioDom> getLstUsers() {
		return lstUsers;
	}

	public void setLstUsers(List<UsuarioDom> lstUsers) {
		this.lstUsers = lstUsers;
	}
	
}
