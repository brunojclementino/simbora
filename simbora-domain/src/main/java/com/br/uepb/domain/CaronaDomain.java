package com.br.uepb.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Classe que define o local de origem, local de destino, data da carona,
 * horário de saída e a quantidade de vaga no carro.
 * 
 * @author Lucas Miranda
 * @author Bruno José Clementino
 *
 */

@Entity
@Table(name="CARONA")
public class CaronaDomain {
	
	final static Logger logger = Logger.getLogger(CaronaDomain.class);
	
	@Id
	@Column(name = "idCarona")
	private String idCarona;
	private String localDeOrigem;
	private String localDeDestino;
	private String data;
	private String horarioDeSaida;
	private String qtdDeVagas;

	
	private String idSessao;
	/**
	 * M�todo construtor default.
	 */
	public CaronaDomain() {
		// TODO Auto-generated constructor stub
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
	 * @return the localDeOrigem
	 */
	public String getLocalDeOrigem() {
		return localDeOrigem;
	}
	/**
	 * @param localDeOrigem the localDeOrigem to set
	 */
	public void setLocalDeOrigem(String localDeOrigem) {
		this.localDeOrigem = localDeOrigem;
	}
	/**
	 * @return the localDeDestino
	 */
	public String getLocalDeDestino() {
		return localDeDestino;
	}
	/**
	 * @param localDeDestino the localDeDestino to set
	 */
	public void setLocalDeDestino(String localDeDestino) {
		this.localDeDestino = localDeDestino;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the horarioDeSaida
	 */
	public String getHorarioDeSaida() {
		return horarioDeSaida;
	}
	/**
	 * @param horarioDeSaida the horarioDeSaida to set
	 */
	public void setHorarioDeSaida(String horarioDeSaida) {
		this.horarioDeSaida = horarioDeSaida;
	}
	/**
	 * @return the qtdDeVagas
	 */
	public String getQtdDeVagas() {
		return qtdDeVagas;
	}
	/**
	 * @param qtdDeVagas the qtdDeVagas to set
	 */
	public void setQtdDeVagas(String qtdDeVagas) {
		this.qtdDeVagas = qtdDeVagas;
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
}
