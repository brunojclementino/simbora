package com.br.uepb.dao;

import java.util.List; 

import com.br.uepb.domain.CaronaInteresseDomain;

/**
 * Interface de Carona com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface CaronaInteresseDao {

	public void save(CaronaInteresseDomain carona);

	public CaronaInteresseDomain getCarona(String idCarona);

	public List<CaronaInteresseDomain> list();

	public void remove(CaronaInteresseDomain carona);

	public void update(CaronaInteresseDomain carona);

	public void excluirTudo();

}
