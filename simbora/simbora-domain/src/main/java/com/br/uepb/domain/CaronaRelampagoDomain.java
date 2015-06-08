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
	@Column(name = "idCarona")
	@GeneratedValue
	private Long idCarona;
	private String dataVolta;

	@OneToOne
	@Column(name = "idCarona")
	private CaronaDomain carona;

	public CaronaRelampagoDomain() {
		carona = new CaronaDomain();
	}

	/**
	 * @return the idCarona
	 */
	public Long getIdCarona() {
		return idCarona;
	}

	/**
	 * @param idCarona
	 *            the idCarona to set
	 */
	public void setIdCarona(Long idCarona) {
		this.idCarona = idCarona;
	}

	public String getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}

	public CaronaDomain getCarona() {
		return carona;
	}

	public void setCarona(CaronaDomain carona) {
		this.carona = carona;
	}
}
