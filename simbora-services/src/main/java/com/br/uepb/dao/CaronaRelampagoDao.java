package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;

public interface CaronaRelampagoDao {

	public void save(CaronaDomain carona);

	public CaronaDomain getCarona(String idCarona);

	public List<CaronaDomain> list();

	public void remove(CaronaDomain carona);

	public void update(CaronaDomain carona);

	public void excluirTudo();

}
