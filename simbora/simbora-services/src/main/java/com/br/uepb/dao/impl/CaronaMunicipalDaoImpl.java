package com.br.uepb.dao.impl;

import java.util.List; 

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaMunicipalDao;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaMunicipalDamain;
import com.br.uepb.util.HibernateUtil;

public class CaronaMunicipalDaoImpl implements CaronaMunicipalDao {

	@Override
	public void save(CaronaMunicipalDamain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
	}

	@Override
	public CaronaMunicipalDamain getCarona(String idCarona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaMunicipalDamain) session.load(
				CaronaMunicipalDamain.class, idCarona);
	}

	@Override
	public List<CaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery(
				"from CaronaDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaMunicipalDamain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaMunicipalDamain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}

	@Override
	public void excluirTudo() {
		
	}

}
