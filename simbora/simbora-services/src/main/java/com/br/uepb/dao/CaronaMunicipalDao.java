package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaMunicipalDamain;

public interface CaronaMunicipalDao {

	public void save(CaronaMunicipalDamain carona);

	public CaronaMunicipalDamain getCarona(String idCarona);

	public List<CaronaDomain> list();

	public void remove(CaronaMunicipalDamain carona);

	public void update(CaronaMunicipalDamain carona);

	public void excluirTudo();

}
