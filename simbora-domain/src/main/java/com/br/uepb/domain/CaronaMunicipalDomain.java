/**
 * 
 */
package com.br.uepb.domain;

import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Lucas e Bruno
 *
 */
@Entity
@Table (name="CARONAMUNICIPAL")
public class CaronaMunicipalDomain {
	
	@Id
	private String id;
	private String cidade;
	
	@OneToOne
	private CaronaDomain carona;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
