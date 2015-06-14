package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.PontoDeEncontroDao;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.PontoDeEncontroDomain;
import com.br.uepb.util.HibernateUtil;



public class PontoDeEncontroDaoImp implements PontoDeEncontroDao{

	@Override
	public void save(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(pontoDeEncontro);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public PontoDeEncontroDomain getPontoDeEncontro(String id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			PontoDeEncontroDomain ponto = (PontoDeEncontroDomain) session.get(PontoDeEncontroDomain.class, Integer.parseInt(id));
			HibernateUtil.closedSession();
			return ponto;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<PontoDeEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<PontoDeEncontroDomain> lista = session.createQuery("from PontoDeEncontroDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(pontoDeEncontro);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public void update(PontoDeEncontroDomain pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(pontoDeEncontro);
		t.commit();
		HibernateUtil.closedSession();
	}
	
	@Override
	public void excluirTudo() {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete from PontoDeEncontroDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
}