package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table (name="CARONAMUNICIPAL")
public class CaronaMunicipalDamain {

	final static Logger logger = Logger.getLogger(CaronaDomain.class);
	
	@Id
	@GeneratedValue
	private int  id;
	private String cidade;
	
	@OneToOne
	@Column(name = "idCarona")
	private CaronaDomain carona;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the carona
	 */
	public CaronaDomain getCarona() {
		return carona;
	}

	/**
	 * @param carona the carona to set
	 */
	public void setCarona(CaronaDomain carona) {
		this.carona = carona;
	}
	
	
}
