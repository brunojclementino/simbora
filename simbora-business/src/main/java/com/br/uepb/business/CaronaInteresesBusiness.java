package com.br.uepb.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaInteresseDaoImpl;
import com.br.uepb.domain.CaronaInteresseDomain;
import com.br.uepb.domain.SessaoDomain;

public class CaronaInteresesBusiness {

	public static Logger logger = Logger
			.getLogger(CaronaInteresesBusiness.class);

	List<SessaoDomain> sessao = SessaoBusiness.getSessoes();
	private static List<CaronaInteresseDomain> interesseCaronas = new CaronaInteresseDaoImpl()
			.list();

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

		CaronaInteresseDomain caronaAux = new CaronaInteresseDomain();
		caronaAux.setOrigem(origem);
		caronaAux.setDestino(destino);
		caronaAux.setData(data);
		caronaAux.setHoraInicio(horaInicio);
		caronaAux.setHoraFim(horaFim);
		caronaAux.setIdSessao(idSessao);
		getInteresseCaronas().add(caronaAux);

		return getInteresseCaronas().indexOf(caronaAux) + "I";
	}

	public static List<CaronaInteresseDomain> getInteresseCaronas() {
		return interesseCaronas;
	}

	public static void setInteresseCaronas(List<CaronaInteresseDomain> interesseCaronas) {
		CaronaInteresesBusiness.interesseCaronas = interesseCaronas;
	}



}
