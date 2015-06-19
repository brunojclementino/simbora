package com.br.uepb.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaInteresseDaoImpl;
import com.br.uepb.domain.CaronaInteresseDomain;

public class CaronaInteresesBusiness {

	
	public static Logger logger = Logger.getLogger(CaronaInteresesBusiness.class);
	
	CaronaInteresseDomain caronaInteresse;
	CaronaInteresseDaoImpl caronaInteresseDaoImpl = new CaronaInteresseDaoImpl();
	

	public String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim)
			throws CaronaException {

		if (origem.equals("-") || origem.equals("!")) {
			throw new CaronaException("Origem inválida");
		}
		if (destino.equals("-") || destino.equals("!")) {
			throw new CaronaException("Destino inválido");
		}
		if (data == null || data.trim().isEmpty()) {
			throw new CaronaException("Data inválida");
		}

		caronaInteresse = new CaronaInteresseDomain();		
			caronaInteresse.setOrigem(origem);
			caronaInteresse.setDestino(destino);
			caronaInteresse.setData(data);
			caronaInteresse.setHoraInicio(horaInicio);
			caronaInteresse.setHoraFim(horaFim);
			caronaInteresse.setIdSessao(idSessao);
		
		caronaInteresseDaoImpl.save(caronaInteresse);
		
		return caronaInteresseDaoImpl.getId()+"";
	}
}
