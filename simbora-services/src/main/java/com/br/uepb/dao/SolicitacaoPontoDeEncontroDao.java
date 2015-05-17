package com.br.uepb.dao;


import java.util.List;

import com.br.uepb.domain.SolicitacaoPontoDeEncontroDomain;


public interface SolicitacaoPontoDeEncontroDao {

	public void save(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro);
	public SolicitacaoPontoDeEncontroDomain getSolicitacaoPontoDeEncontro(String idSolicitacao);
	public List<SolicitacaoPontoDeEncontroDomain> list();
	public void remove(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro);
	public void update(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro);
	public void excluirTudo();
	 
}
