package com.br.uepb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 * Gerencia os pontos de encontro. 
 * 
 * @author Lucas Miranda e Bruno Clementino
 */

@Entity
@Table(name="PONTODEENCONTRO")
public class PontoDeEncontroDomain {
	final static Logger logger = Logger.getLogger(PontoDeEncontroDomain.class);
	
	@Id
	@GeneratedValue
	private int idPonto;
	private String idCarona;
	private String pontos;
	private String idSessao;
	
	/**
	 * @return the idPonto
	 */
	public int getIdPonto() {
		return idPonto;
	}
	
	/**
	 * @param idPonto the idPonto to set
	 */
	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
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
	 * @return the pontos
	 */
	public String getPontos() {
		return pontos;
	}
	/**
	 * @param pontos the pontos to set
	 */
	public void setPontos(String pontos) {
		this.pontos = pontos;
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
