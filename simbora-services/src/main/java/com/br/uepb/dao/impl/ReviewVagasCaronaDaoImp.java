package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.ReviewVagasCaronaDao;
import com.br.uepb.domain.ReviewVagasCaronaDomain;
import com.br.uepb.domain.ReviewDomain;
import com.br.uepb.util.HibernateUtil;


public class ReviewVagasCaronaDaoImp implements ReviewVagasCaronaDao{

	@Override
	public void save(ReviewVagasCaronaDomain reviewCaronas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(reviewCaronas);
		t.commit();
		HibernateUtil.closedSession();
	}
	@Override
	public ReviewVagasCaronaDomain getCarona(String id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			ReviewVagasCaronaDomain review = (ReviewVagasCaronaDomain) session.get(ReviewVagasCaronaDomain.class, Integer.parseInt(id));
			HibernateUtil.closedSession();
			return review;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<ReviewVagasCaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewVagasCaronaDomain> lista = session.createQuery("from ReviewVagasCaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(ReviewVagasCaronaDomain review) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(review);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public void update(ReviewVagasCaronaDomain review) {
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
		session.createQuery("delete from ReviewVagasCaronaDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
	public List<ReviewDomain> getCaronasSegurasTranquilas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewDomain> lista = session.createQuery("SELECT caronaSeguraTranquila FROM ReviewVagasCaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<ReviewDomain> getCaronasNaoFuncionaram(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewDomain> lista = session.createQuery("SELECT caronaNaoFuncionou FROM ReviewVagasCaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<ReviewDomain> getFaltaramNasVagas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewDomain> lista = session.createQuery("SELECT faltouNaVaga FROM ReviewVagasCaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<ReviewDomain> getPresentesNasVagas(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewDomain> lista = session.createQuery("SELECT presenteNaVaga FROM ReviewVagasCaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	//Este método retorna a tupla na tabela que está com o campo da coluna x vazio
	//para assim atualizar aquela tupla.
	public ReviewVagasCaronaDomain getIdCampoVazio(String nomeDaColunaDaTabela) {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewVagasCaronaDomain> id = session.createQuery("FROM ReviewVagasCaronaDomain where "+nomeDaColunaDaTabela+" is null").list();
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