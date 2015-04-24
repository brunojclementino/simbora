package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.SolicitacaoVagasDao;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.util.HibernateUtil;


public class SolicitacaoVagasDaoImp implements SolicitacaoVagasDao{

	@Override
	public void save(SolicitacaoVagasDomain solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(solicitacaoVagas);
		t.commit();
	}

	@Override
	public SolicitacaoVagasDomain getSolicitacaoVagas(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SolicitacaoVagasDomain) session.load(SolicitacaoVagasDomain.class, idLogin);
	}

	@Override
	public List<SolicitacaoVagasDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoVagasDomain> lista = session.createQuery("from SolicitacaoVagas").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SolicitacaoVagasDomain solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(solicitacaoVagas);
		t.commit();
	}

	@Override
	public void update(SolicitacaoVagasDomain solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(solicitacaoVagas);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SolicitacaoVagasDomain> list = list();
        for(SolicitacaoVagasDomain solicitacaoVagas:list){
        	remove(solicitacaoVagas);
        }
    } 
	
}