package com.br.uepb.domain;

import java.util.List;

/**
 * Define as informações básicas do usuário.
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class PerfilDomain {
	private String idUsuario;
	private List<String> idCaronas;

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idCaronas
	 */
	public List<String> getIdCaronas() {
		return idCaronas;
	}

	/**
	 * @param idCaronas
	 *            the idCaronas to set
	 */
	public void setIdCaronas(List<String> idCaronas) {
		this.idCaronas = idCaronas;
	}

}
