package com.br.uepb.dao.impl;

import java.util.List;  

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaRelampagoDao;
import com.br.uepb.domain.CaronaRelampagoDomain;
import com.br.uepb.util.HibernateUtil;

public class CaronaRelampagoDaoImpl implements CaronaRelampagoDao {

	@Override
	public void save(CaronaRelampagoDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();		
	}

	@Override
	public CaronaRelampagoDomain getCarona(String idCarona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaRelampagoDomain) session.load(CaronaRelampagoDomain.class, idCarona);
	}

	@Override
	public List<CaronaRelampagoDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaRelampagoDomain> lista = session.createQuery("from CaronaRelampago").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaRelampagoDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaRelampagoDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}

	@Override
	public void excluirTudo() {
		List<CaronaRelampagoDomain> list = list();
        for(CaronaRelampagoDomain carona:list){
        	remove(carona);
        }
	}

}