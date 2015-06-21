package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Esta classe regista as solicitações do caroneiro. 
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
@Entity
@Table(name="SOLICITACAOVAGAS")
public class SolicitacaoVagasDomain {
	final static Logger logger = Logger.getLogger(SolicitacaoVagasDomain.class);
	@Id
	@GeneratedValue
	@Column(name = "idSolicitacao")
	private int idSolicitacao;
	private String idSessao;
	//identificação de quem solicitou a vaga
	private String idCarona;
	private String status = "Pendente";
	private int idPonto;
	//Se a solicitação foi Aceita, está Pendente, ou foi Recusada.
	
	public int getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}
	/**
	 * @return the idSolicitacao
	 */
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	/**
	 * @param idSolicitacao the idSolicitacao to set
	 */
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	/**
	 * @return the idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}
	/**
	 * @param idSessao the idSessao to set
	 */
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	/**
	 * @return the idCarona
	 */
	public String getIdCarona() {
		return idCarona;
	}
	/**
	 * @param idCarona the idCarona to set
	 */
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
		
}
