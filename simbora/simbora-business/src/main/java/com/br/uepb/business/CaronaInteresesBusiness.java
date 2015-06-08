package com.br.uepb.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaInteresseDaoImpl;
import com.br.uepb.domain.CaronaInteresseDomain;

public class CaronaInteresesBusiness {

	CaronaInteresseDomain caronaAux;
	public static Logger logger = Logger
			.getLogger(CaronaInteresesBusiness.class);
	
	private static List<CaronaInteresseDomain> interesseCaronas = new CaronaInteresseDaoImpl().list();

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

		caronaAux = new CaronaInteresseDomain();		
			caronaAux.setOrigem(origem);
			caronaAux.setDestino(destino);
			caronaAux.setData(data);
			caronaAux.setHoraInicio(horaInicio);
			caronaAux.setHoraFim(horaFim);
			caronaAux.setIdSessao(idSessao);
			caronaAux.setId((Integer.valueOf(interesseCaronas.size()))+"I");
		interesseCaronas.add(caronaAux);

		return caronaAux.getId();
	}

	public static List<CaronaInteresseDomain> getInteresseCaronas() {
		return interesseCaronas;
	}

	public static void setInteresseCaronas(
			List<CaronaInteresseDomain> interesseCaronas) {
		CaronaInteresesBusiness.interesseCaronas = interesseCaronas;
	}

	public void encerrarSistema() {
		for (CaronaInteresseDomain carona : interesseCaronas) {
			try {
				CaronaInteresseDaoImpl caronaInteresseDaoImp = new CaronaInteresseDaoImpl();
				caronaInteresseDaoImp.save(carona);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		interesseCaronas.clear();
	}
}
