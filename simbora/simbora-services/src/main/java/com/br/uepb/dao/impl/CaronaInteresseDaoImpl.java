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
	}

	@Override
	public CaronaInteresseDomain getCarona(String idCarona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaInteresseDomain) session.load(CaronaInteresseDomain.class, idCarona);
	}

	@Override
	public List<CaronaInteresseDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaInteresseDomain> lista = session.createQuery("from CaronaInteresseDomain").list();
		t.commit();
		return lista;
	} 

	@Override
	public void remove(CaronaInteresseDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaInteresseDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<CaronaInteresseDomain> list = list();
        for(CaronaInteresseDomain carona:list){
        	remove(carona);
        }
    } 
}
