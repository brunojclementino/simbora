package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;

/**
 * Interface de Carona com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface CaronaDao {

	public void save(CaronaDomain carona);

	public CaronaDomain getCarona(String idCarona);

	public List<CaronaDomain> list();

	public void remove(CaronaDomain carona);

	public void update(CaronaDomain carona);

	public void excluirTudo();

}
