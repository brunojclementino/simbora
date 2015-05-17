package com.br.uepb.dao;


import java.util.List;

import com.br.uepb.domain.SolicitacaoVagasDomain;


public interface SolicitacaoVagasDao {

	public void save(SolicitacaoVagasDomain solicitacaoVagas);
	public SolicitacaoVagasDomain getSolicitacaoVagas(String idSolicitacaoVagas);
	public List<SolicitacaoVagasDomain> list();
	public void remove(SolicitacaoVagasDomain solicitacaoVagas);
	public void update(SolicitacaoVagasDomain solicitacaoVagas);
	public void excluirTudo();
	 
}
