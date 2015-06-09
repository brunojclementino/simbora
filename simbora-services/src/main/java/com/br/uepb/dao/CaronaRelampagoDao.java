package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.CaronaRelampagoDomain;

public interface CaronaRelampagoDao {

	public void save(CaronaRelampagoDomain carona);

	public CaronaRelampagoDomain getCarona(String idCarona);

	public List<CaronaRelampagoDomain> list();

	public void remove(CaronaRelampagoDomain carona);

	public void update(CaronaRelampagoDomain carona);

	public void excluirTudo();

}
