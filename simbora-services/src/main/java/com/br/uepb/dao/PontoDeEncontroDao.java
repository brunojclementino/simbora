package com.br.uepb.dao;


import java.util.List;

import com.br.uepb.domain.PontoDeEncontroDomain;

public interface PontoDeEncontroDao {

	public void save(PontoDeEncontroDomain pontoDeEncontro);
	public PontoDeEncontroDomain getPontoDeEncontro(String idPontoDeEncontro);
	public List<PontoDeEncontroDomain> list();
	public void remove(PontoDeEncontroDomain pontoDeEncontro);
	public void update(PontoDeEncontroDomain pontoDeEncontro);
	public void excluirTudo();
	 
}
