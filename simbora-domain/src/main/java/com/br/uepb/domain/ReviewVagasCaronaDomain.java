package com.br.uepb.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="REVIEWVAGASCARONA")
public class ReviewVagasCaronaDomain {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private ReviewDomain caronaSeguraTranquila;
	@OneToOne(cascade = CascadeType.ALL)
	private ReviewDomain caronaNaoFuncionou;
	@OneToOne(cascade = CascadeType.ALL)
	private ReviewDomain faltouNaVaga;
	@OneToOne(cascade = CascadeType.ALL)
	private ReviewDomain presenteNaVaga;
	
	public ReviewDomain getCaronaSeguraTranquila() {
		return caronaSeguraTranquila;
	}
	public void setCaronaSeguraTranquila(ReviewDomain caronaSeguraTranquila) {
		this.caronaSeguraTranquila = caronaSeguraTranquila;
	}
	public ReviewDomain getCaronaNaoFuncionou() {
		return caronaNaoFuncionou;
	}
	public void setCaronaNaoFuncionou(ReviewDomain caronaNaoFuncionou) {
		this.caronaNaoFuncionou = caronaNaoFuncionou;
	}
	public ReviewDomain getFaltouNaVaga() {
		return faltouNaVaga;
	}
	public void setFaltouNaVaga(ReviewDomain faltouNaVaga) {
		this.faltouNaVaga = faltouNaVaga;
	}
	public ReviewDomain getPresenteNaVaga() {
		return presenteNaVaga;
	}
	public void setPresenteNaVaga(ReviewDomain presenteNaVaga) {
		this.presenteNaVaga = presenteNaVaga;
	}
}
