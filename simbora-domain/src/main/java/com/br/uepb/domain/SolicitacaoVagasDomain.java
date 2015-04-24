package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esta classe regista as solicita��es do caroneiro. 
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
@Entity
@Table(name="SOLICITACAOVAGAS")
public class SolicitacaoVagasDomain {
	
	@Id
	@Column(name = "idSolicitacao")
	private String idSolicitacao;
	private String idSessao;//identifica��o de quem solicitou a vaga
	private String idCarona;
	private String status = "Pendente";//Se a solicita��o foi Aceita, est� Pendente, ou foi Recusada.
	
	/**
	 * @return the idSolicitacao
	 */
	public String getIdSolicitacao() {
		return idSolicitacao;
	}
	/**
	 * @param idSolicitacao the idSolicitacao to set
	 */
	public void setIdSolicitacao(String idSolicitacao) {
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
