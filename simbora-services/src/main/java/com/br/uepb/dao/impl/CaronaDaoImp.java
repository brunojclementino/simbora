package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaDao;
import com.br.uepb.domain.CaronaDomain;
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
	public int getUsuariosPreferenciais(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Integer> id = session.createQuery("SELECT login FROM ReviewCaronasDomain where ").list();
		t.commit();
		HibernateUtil.closedSession();
		
		return id.get(0);
	}
	
}