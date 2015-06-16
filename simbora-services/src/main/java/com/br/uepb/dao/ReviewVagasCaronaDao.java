package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.ReviewVagasCaronaDomain;

/**
 * Interface de Carona com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface ReviewVagasCaronaDao {

	public void save(ReviewVagasCaronaDomain reviewCaronas);

	public ReviewVagasCaronaDomain getCarona(String idCarona);

	public List<ReviewVagasCaronaDomain> list();

	public void remove(ReviewVagasCaronaDomain reviewCaronas);

	public void update(ReviewVagasCaronaDomain reviewCaronas);

	public void excluirTudo();

}
