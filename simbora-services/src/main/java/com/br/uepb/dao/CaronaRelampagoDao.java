package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.CaronaRelampago;

public interface CaronaRelampagoDao {

	public void save(CaronaRelampago carona);

	public CaronaRelampago getCarona(String idCarona);

	public List<CaronaRelampago> list();

	public void remove(CaronaRelampago carona);

	public void update(CaronaRelampago carona);

	public void excluirTudo();

}
