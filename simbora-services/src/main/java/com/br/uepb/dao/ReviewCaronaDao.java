package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.ReviewCaronasDomain;

/**
 * Interface de Carona com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface ReviewCaronaDao {

	public void save(ReviewCaronasDomain reviewCaronas);

	public ReviewCaronasDomain getCarona(String idCarona);

	public List<ReviewCaronasDomain> list();

	public void remove(ReviewCaronasDomain reviewCaronas);

	public void update(ReviewCaronasDomain reviewCaronas);

	public void excluirTudo();

}
