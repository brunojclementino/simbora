package com.br.uepb.dao.impl;

import java.util.List; 

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaInteresseDao;
import com.br.uepb.domain.CaronaInteresseDomain;
import com.br.uepb.util.HibernateUtil;

public class CaronaInteresseDaoImpl implements CaronaInteresseDao {


	@Override
	public void save(CaronaInteresseDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
		HibernateUtil.closedSession();
	}
	@Override
	public CaronaInteresseDomain getCarona(String idCarona) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			CaronaInteresseDomain carona = (CaronaInteresseDomain) session.get(CaronaInteresseDomain.class, Integer.parseInt(idCarona));
			HibernateUtil.closedSession();
			return carona;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<CaronaInteresseDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaInteresseDomain> lista = session.createQuery("from CaronaInteresseDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		return lista;
	}

	@Override
	public void remove(CaronaInteresseDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
		HibernateUtil.closedSession();
	}

	@Override
	public void update(CaronaInteresseDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
		HibernateUtil.closedSession();
	}
	
	@Override 
	public void excluirTudo() {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete from CaronaInteresseDomain where id <> null").executeUpdate();
		t.commit();
		HibernateUtil.closedSession();
    } 
	
	public int getId(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Integer> id = session.createQuery("SELECT MAX(id) FROM CaronaInteresseDomain").list();
		t.commit();
		HibernateUtil.closedSession();
		
		return id.get(0);
	}
}
	
