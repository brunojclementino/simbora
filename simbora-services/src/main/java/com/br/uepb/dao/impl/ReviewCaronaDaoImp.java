package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.ReviewCaronaDao;
import com.br.uepb.domain.ReviewCaronasDomain;
import com.br.uepb.util.HibernateUtil;


public class ReviewCaronaDaoImp implements ReviewCaronaDao{

	@Override
	public void save(ReviewCaronasDomain reviewCaronas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(reviewCaronas);
		t.commit();
		HibernateUtil.closedSession();
	}
	@Override
	public ReviewCaronasDomain getCarona(String id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			ReviewCaronasDomain review = (ReviewCaronasDomain) session.get(ReviewCaronasDomain.class, Integer.parseInt(id));
			HibernateUtil.closedSession();
			return review;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<ReviewCaronasDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewCaronasDomain> lista = session.createQuery("from ReviewCaronasDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(ReviewCaronasDomain review) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(review);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public void update(ReviewCaronasDomain review) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(review);
		t.commit();
		HibernateUtil.closedSession();
	}
	
	@Override 
	public void excluirTudo() {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete from ReviewCaronasDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
	public List<String> getCaronasSegurasTranquilas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<String> lista = session.createQuery("SELECT caronaSeguraTranquila FROM ReviewCaronasDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<String> getCaronasNaoFuncionaram(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<String> lista = session.createQuery("SELECT caronaNaoFuncionou FROM ReviewCaronasDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<String> getFaltaramNasVagas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<String> lista = session.createQuery("SELECT faltouNaVaga FROM ReviewCaronasDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<String> getPresentesNasVagas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<String> lista = session.createQuery("SELECT presenteNaVaga FROM ReviewCaronasDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	//Este método retorna a tupla na tabela que está com o campo da coluna x vazio
	//para assim atualizar aquela tupla.
	public ReviewCaronasDomain getIdCampoVazio(String nomeDaColunaDaTabela) {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewCaronasDomain> id = session.createQuery("FROM ReviewCaronasDomain where "+nomeDaColunaDaTabela+" is null").list();
		t.commit();
		HibernateUtil.closedSession();
		try {
			//Caso tenha algum campo vazio na coluna x, será retornado a primeira tupla encontrada
			return id.get(0);
		} catch (Exception e) {
			//Caso não haja campo nulo, será retornado null
			return null;
		}
		
	}
	
}