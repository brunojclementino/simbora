package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARONARELAMPAGO")
public class CaronaRelampagoDomain {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String dataVolta;
	private String expirou = "false";

	/**
	 * @return the idCarona
	 */
	public int getIdCarona() {
		return id;
	}

	/**
	 * @param idCarona
	 *            the idCarona to set
	 */
	public void setIdCarona(int idCarona) {
		this.id = idCarona;
	}

	public String getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}

	public String getExpirou() {
		return expirou;
	}

	public void setExpirou(String expirou) {
		this.expirou = expirou;
	}

}
