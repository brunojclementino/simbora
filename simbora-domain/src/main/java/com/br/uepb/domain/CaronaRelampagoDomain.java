package com.br.uepb.domain;

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
	private String id;
	private String dataVolta;

	@OneToOne
	private CaronaDomain carona;

	/**
	 * @return the idCarona
	 */
	public String getIdCarona() {
		return id;
	}

	/**
	 * @param idCarona
	 *            the idCarona to set
	 */
	public void setIdCarona(String idCarona) {
		this.id = idCarona;
	}

	public String getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}

	/**
	 * @return the carona
	 */
	public CaronaDomain getCarona() {
		return carona;
	}

	/**
	 * @param carona
	 *            the carona to set
	 */
	public void setCarona(CaronaDomain carona) {
		this.carona = carona;
	}

}
