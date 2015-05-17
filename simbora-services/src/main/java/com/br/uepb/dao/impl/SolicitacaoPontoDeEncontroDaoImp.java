package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.SolicitacaoPontoDeEncontroDao;
import com.br.uepb.domain.SolicitacaoPontoDeEncontroDomain;
import com.br.uepb.util.HibernateUtil;



public class SolicitacaoPontoDeEncontroDaoImp implements SolicitacaoPontoDeEncontroDao{

	@Override
	public void save(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(solicitacaoPontoDeEncontro);
		t.commit();
	}

	@Override
	public SolicitacaoPontoDeEncontroDomain getSolicitacaoPontoDeEncontro(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SolicitacaoPontoDeEncontroDomain) session.load(SolicitacaoPontoDeEncontroDomain.class, idLogin);
	}

	@Override
	public List<SolicitacaoPontoDeEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoPontoDeEncontroDomain> lista = session.createQuery("from SolicitacaoPontoDeEncontroDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(solicitacaoPontoDeEncontro);
		t.commit();
	}

	@Override
	public void update(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(solicitacaoPontoDeEncontro);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SolicitacaoPontoDeEncontroDomain> list = list();
        for(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro:list){
        	remove(solicitacaoPontoDeEncontro);
        }
    } 
	
}