package com.br.uepb.dao;

import java.util.List; 

import com.br.uepb.domain.CaronaDomain;


/**
 * Interface de Carona Municipal com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface CaronaMunicipalDao {

	public void save(CaronaDomain carona);

	public CaronaDomain getCarona(String idCarona);

	public List<CaronaDomain> list();

	public void remove(CaronaDomain carona);

	public void update(CaronaDomain carona);

	public void excluirTudo();

}
