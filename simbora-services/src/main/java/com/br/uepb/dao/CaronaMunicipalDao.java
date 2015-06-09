package com.br.uepb.dao;

import java.util.List; 

import com.br.uepb.domain.CaronaMunicipalDomain;


/**
 * Interface de Carona Municipal com as operações CRUD.
 * 
 * @author Lucas Miranda e Bruno Clementino.
 */
public interface CaronaMunicipalDao {

	public void save(CaronaMunicipalDomain carona);

	public CaronaMunicipalDomain getCarona(String idCarona);

	public List<CaronaMunicipalDomain> list();

	public void remove(CaronaMunicipalDomain carona);

	public void update(CaronaMunicipalDomain carona);

	public void excluirTudo();

}
