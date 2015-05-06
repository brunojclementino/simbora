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
	 * Armazena respectivamente os pontos de sugestão (0), resposta (1) e
	 * confirmação (2).
	 * 
	 */
	@OneToMany
    @OrderColumn(name="idPonto")
	private PontoDeEncontroDomain[] pontoDeEncontro = new PontoDeEncontroDomain[3];
	
	@Id
	@Column(name = "idSugestao")
	private String idSugestao; 
	private boolean emAndamento = true;
		// Se a solicitação ainda não foi concluída

	/**
	 * Retorna o ponto de encontro.
	 * @param indice
	 * @return ponto de encontro.
	 */
	public PontoDeEncontroDomain getPontoDeEncontro(int indice) {
		return pontoDeEncontro[indice];
	}

	/**
	 * Define o ponto de encontro. Dependendo do indice poderá definida como sugestão (0), 
	 * resposta (1) ou confirmação (2).
	 * @param pontoDeEncontro
	 * @param indice
	 */
	public void setPontoDeEncontro(PontoDeEncontroDomain pontoDeEncontro, int indice) {
		this.pontoDeEncontro[indice] = pontoDeEncontro;
	}

	/**
	 * @return id da sugestão.
	 */
	public String getIdSugestao() { 
		return idSugestao;
	}

	/**
	 * Atribui o id da sugestão da carona.
	 * @param idSugestao
	 */
	public void setIdSugestao(String idSugestao) { 
		this.idSugestao = idSugestao;
	}
	
	/**
	 * 
	 * @return status do andamento da sugestão da carona. <code>true</code> ou <code>false</code>.
	 */
	public boolean isEmAndamento() { 
		return emAndamento;
	}

	/**
	 * Define o status da sugestão da carona.
	 * @param emAndamento
	 */
	public void setEmAndamento(boolean emAndamento) { 
		this.emAndamento = emAndamento;
	}
}
