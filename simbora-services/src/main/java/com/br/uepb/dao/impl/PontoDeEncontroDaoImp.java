package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.PontoDeEncontroDao;
import com.br.uepb.domain.PontoDeEncontroDomain;
import com.br.uepb.util.HibernateUtil;



public class PontoDeEncontroDaoImp implements PontoDeEncontroDao{

	@Override
	public void save(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(pontoDeEncontro);
		t.commit();
	}

	@Override
	public PontoDeEncontroDomain getPontoDeEncontro(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (PontoDeEncontroDomain) session.load(PontoDeEncontroDomain.class, idLogin);
	}

	@Override
	public List<PontoDeEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<PontoDeEncontroDomain> lista = session.createQuery("from PontoDeEncontroDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(pontoDeEncontro);
		t.commit();
	}

	@Override
	public void update(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(pontoDeEncontro);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<PontoDeEncontroDomain> list = list();
        for(PontoDeEncontroDomain pontoDeEncontro:list){
        	remove(pontoDeEncontro);
        }
    } 
	
}