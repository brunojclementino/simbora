package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.CaronaMunicipalDao;
import com.br.uepb.domain.CaronaMunicipalDomain;
import com.br.uepb.util.HibernateUtil;

public class CaronaMunicipalDaoImpl implements CaronaMunicipalDao {

	@Override
	public void save(CaronaMunicipalDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
	}

	@Override
	public CaronaMunicipalDomain getCarona(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaMunicipalDomain) session.load(
				CaronaMunicipalDomain.class, idLogin);
	}

	@Override
	public List<CaronaMunicipalDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaMunicipalDomain> lista = session.createQuery(
				"from CaronaMunicipalDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaMunicipalDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaMunicipalDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}

	@Override
	public void excluirTudo() {
		List<CaronaMunicipalDomain> list = list();
		for (CaronaMunicipalDomain carona : list) {
			remove(carona);
		}
	}

}