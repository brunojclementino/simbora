package com.br.uepb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaDao;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.ReviewDomain;
import com.br.uepb.util.HibernateUtil;


public class CaronaDaoImp implements CaronaDao{

	@Override
	public void save(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
		HibernateUtil.closedSession();
	}
	
	@Override
	public CaronaDomain getCarona(String idCarona) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			CaronaDomain carona = (CaronaDomain) session.get(CaronaDomain.class, Integer.parseInt(idCarona));
			HibernateUtil.closedSession();
			return carona;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<CaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public void update(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
		session.close();
	}
	
	@Override 
	public void excluirTudo() {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete from CaronaDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
	public int getId(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Integer> id = session.createQuery("SELECT MAX(id) FROM CaronaDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		
		return id.get(0);
	}
	public List<String> getUsuariosPreferenciais(String login){//login do dono da carona que é preferencial
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<ReviewDomain> reviews = session.createQuery("select caronaSeguraTranquila from ReviewCaronasDomain").list();
		
		List<Integer> caronasDoUsuario = session.createQuery("select id from CaronaDomain where idUsuario = \'"+login+"\'").list();
		t.commit();
		HibernateUtil.closedSession();
		
		List<String> loginsPrioritarios = new ArrayList<>();
		for (ReviewDomain review : reviews) {
			if(caronasDoUsuario.contains(Integer.parseInt(review.getIdAvaliado()))){
				loginsPrioritarios.add(review.getLogin());
			}
			
		}
		
		return loginsPrioritarios;
	}
	
	public List<CaronaDomain> ultimasCaronas(int qtd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain "
				+ "where caronaRelampago_id=null and caronaMunicipal=null order by id desc").setMaxResults(qtd).list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<CaronaDomain> ultimasCaronasMunicipal(int qtd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain "
				+ "where caronaMunicipal!=null order by id desc").setMaxResults(qtd).list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
	public List<CaronaDomain> ultimasCaronasRelampago(int qtd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain "
				+ "where caronaRelampago_id!=null order by id desc").setMaxResults(qtd).list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}
}