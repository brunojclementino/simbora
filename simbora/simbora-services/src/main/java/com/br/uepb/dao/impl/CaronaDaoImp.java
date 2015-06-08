package com.br.uepb.dao.impl;

import java.util.List;

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
	}
	
	@Override
	public CaronaDomain getCarona(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaDomain) session.load(CaronaDomain.class, idLogin);
	}

	@Override
	public List<CaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}
	
	@Override 
	public void excluirTudo() {  
        List<CaronaDomain> list = list();
        for(CaronaDomain carona:list){
        	remove(carona);
        }
    }
	
	public CaronaDomain getIDCarona(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaDomain) session.load(CaronaDomain.class, carona.getIdCarona());
	}
}