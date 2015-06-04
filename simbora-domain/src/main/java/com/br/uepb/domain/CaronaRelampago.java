package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CARONARELAMPAGO")
public class CaronaRelampago {
	
	@Id
	@Column	(name = "idCarona")
	private String idCarona;
	private String origem;
	private String destino;
	private String dataIda;
	private String dataVolta;
	private String hora;
	private String minimoCaroneiros;
	
	
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
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getDataIda() {
		return dataIda;
	}
	public void setDataIda(String dataIda) {
		this.dataIda = dataIda;
	}
	public String getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMinimoCaroneiros() {
		return minimoCaroneiros;
	}
	public void setMinimoCaroneiros(String minimoCaroneiros) {
		this.minimoCaroneiros = minimoCaroneiros;
	}
}
