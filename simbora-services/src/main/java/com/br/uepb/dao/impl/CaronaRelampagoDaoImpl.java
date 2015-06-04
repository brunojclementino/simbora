package com.br.uepb.dao.impl;

import java.util.List; 

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaRelampagoDao;
import com.br.uepb.domain.CaronaRelampago;
import com.br.uepb.util.HibernateUtil;

public class CaronaRelampagoDaoImpl implements CaronaRelampagoDao {

	@Override
	public void save(CaronaRelampago carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();		
	}

	@Override
	public CaronaRelampago getCarona(String idCarona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaRelampago) session.load(CaronaRelampago.class, idCarona);
	}

	@Override
	public List<CaronaRelampago> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaRelampago> lista = session.createQuery("from CaronaRelampago").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaRelampago carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaRelampago carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}

	@Override
	public void excluirTudo() {
		List<CaronaRelampago> list = list();
        for(CaronaRelampago carona:list){
        	remove(carona);
        }
	}

}