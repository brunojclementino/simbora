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
		HibernateUtil.closedSession();
	}

	@Override
	public SolicitacaoPontoDeEncontroDomain getSolicitacaoPontoDeEncontro(String idSolicitacao) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			SolicitacaoPontoDeEncontroDomain solicitacao = (SolicitacaoPontoDeEncontroDomain) session.get(SolicitacaoPontoDeEncontroDomain.class, Integer.parseInt(idSolicitacao));
			HibernateUtil.closedSession();
			return solicitacao;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<SolicitacaoPontoDeEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoPontoDeEncontroDomain> lista = session.createQuery("from SolicitacaoPontoDeEncontroDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(solicitacaoPontoDeEncontro);
		HibernateUtil.closedSession();
		t.commit();
	}

	@Override
	public void update(SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(solicitacaoPontoDeEncontro);
		t.commit();
		HibernateUtil.closedSession();
	}
	
	@Override
	public void excluirTudo() {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete from SolicitacaoPontoDeEncontroDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
	public int getId(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Integer> id = session.createQuery("SELECT MAX(id) FROM SolicitacaoPontoDeEncontroDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return id.get(0);
	}
	
}