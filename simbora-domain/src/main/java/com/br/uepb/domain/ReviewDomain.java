package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name="REVIEW")
public class ReviewDomain {

	final static Logger logger = Logger.getLogger(ReviewDomain.class);
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String login;//Quem faz a avaliação
	private String idAvaliado;//Identificação do que está sendo avaliado
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getIdAvaliado() {
		return idAvaliado;
	}
	public void setIdAvaliado(String idCarona) {
		this.idAvaliado = idCarona;
	}
}
