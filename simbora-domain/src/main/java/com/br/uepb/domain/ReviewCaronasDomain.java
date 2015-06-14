package com.br.uepb.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REVIEWCARONAS")
public class ReviewCaronasDomain {

	@Id
	@GeneratedValue
	private int id;
	private String caronaSeguraTranquila;
	private String caronaNaoFuncionou;
	private String faltouNaVaga;
	private String presenteNaVaga;
	
	public String getCaronaSeguraTranquila() {
		return caronaSeguraTranquila;
	}
	public void setCaronaSeguraTranquila(String caronaSeguraTranquila) {
		this.caronaSeguraTranquila = caronaSeguraTranquila;
	}
	public String getCaronaNaoFuncionou() {
		return caronaNaoFuncionou;
	}
	public void setCaronaNaoFuncionou(String caronaNaoFuncionou) {
		this.caronaNaoFuncionou = caronaNaoFuncionou;
	}
	public String getFaltouNaVaga() {
		return faltouNaVaga;
	}
	public void setFaltouNaVaga(String faltouNaVaga) {
		this.faltouNaVaga = faltouNaVaga;
	}
	public String getPresenteNaVaga() {
		return presenteNaVaga;
	}
	public void setPresenteNaVaga(String presenteNaVaga) {
		this.presenteNaVaga = presenteNaVaga;
	}
}
