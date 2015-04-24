package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
@Entity
@Table(name="SOLICITACAOPONTODEENCONTRO")
public class SolicitacaoPontoDeEncontroDomain {
	/**
	 * Armazena respectivamente os pontos de sugest�o (0), resposta (1) e
	 * confirma��o (2).
	 * 
	 */
	@OneToMany
    @OrderColumn(name="idPonto")
	private PontoDeEncontroDomain[] pontoDeEncontro = new PontoDeEncontroDomain[3];
	
	@Id
	@Column(name = "idSugestao")
	private String idSugestao;
	private boolean emAndamento = true;// Se a solicita��o ainda n�o foi
										// conclu�da

	public PontoDeEncontroDomain getPontoDeEncontro(int indice) {
		return pontoDeEncontro[indice];
	}

	public void setPontoDeEncontro(PontoDeEncontroDomain pontoDeEncontro, int indice) {
		this.pontoDeEncontro[indice] = pontoDeEncontro;
	}

	public String getIdSugestao() {
		return idSugestao;
	}

	public void setIdSugestao(String idSugestao) {
		this.idSugestao = idSugestao;
	}

	public boolean isEmAndamento() {
		return emAndamento;
	}

	public void setEmAndamento(boolean emAndamento) {
		this.emAndamento = emAndamento;
	}
}
