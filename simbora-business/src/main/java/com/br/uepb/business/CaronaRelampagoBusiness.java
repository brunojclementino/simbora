package com.br.uepb.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaRelampagoDaoImpl;
import com.br.uepb.domain.CaronaRelampago;
import com.br.uepb.domain.SessaoDomain;

public class CaronaRelampagoBusiness {

	private static List<CaronaRelampago> interesseCaronasRelamlago = new CaronaRelampagoDaoImpl().list();
	
	public void encerrarSistema() {
		for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
			try {
				CaronaRelampagoDaoImpl relampago = new CaronaRelampagoDaoImpl();
				relampago.save(caronaRelampago);
			} catch (Exception e) {
				e.getMessage();
			}			
		}
		interesseCaronasRelamlago.clear();
	}
	public String cadastrarCaronaRelampago(String idSessao, String origem,
			String destino, String dataIda, String dataVolta, String hora,
			String minimoCaroneiros) throws CaronaException {		
		if (idSessao == null) {
			throw new CaronaException("Sessão inválida");
		}
		if (idSessao.trim().isEmpty()) {
			throw new CaronaException("Sessão inválida");
		}
		
		if (!sessaoExiste(idSessao)) {
			throw new CaronaException("Sessão inexistente");
		}
		
		if (origem == null || origem.trim().isEmpty()) {
			throw new CaronaException("Origem inválida");
		}
		
		if (destino == null || destino.trim().isEmpty()) {
			throw new CaronaException("Destino inválido");
		}
		
		if (dataIda == null || dataIda.trim().isEmpty() || !isData(dataIda)) {
			throw new CaronaException("Data inválida");
		}
		
		if (dataVolta == null || dataVolta.trim().isEmpty() || !isData(dataVolta)) {
			throw new CaronaException("Data inválida");
		}
		
		CaronaRelampago carona = new CaronaRelampago();
			carona.setOrigem(origem);
			carona.setDestino(destino);
			carona.setDataIda(dataIda);
			carona.setDataVolta(dataVolta);
			carona.setHora(hora);
			carona.setMinimoCaroneiros(minimoCaroneiros);
			carona.setIdCarona(interesseCaronasRelamlago.size()+"R");
		interesseCaronasRelamlago.add(carona);
		return carona.getIdCarona();
	}

	private boolean sessaoExiste(String idSessao) {
		for (SessaoDomain sessao : SessaoBusiness.getSessoes()) {
			if (sessao.getIdSessao().equals(idSessao)) {
				return true;
			}
		}
		return false;
	}
	public String getAtributoCaronaRelampago(String idCarona, String atributo) {
		if (atributo.equals("origem")) {
			for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
				if (caronaRelampago.getIdCarona().equals(idCarona)) {
					return caronaRelampago.getOrigem()+"";
				}				
			}
		}
		if (atributo.equals("destino")) {
			for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
				if (caronaRelampago.getIdCarona().equals(idCarona)) {
					return caronaRelampago.getDestino()+"";
				}				
			}
		}
		if (atributo.equals("minimoCaroneiros")) {
			for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
				if (caronaRelampago.getIdCarona().equals(idCarona)) {
					return caronaRelampago.getMinimoCaroneiros()+"";
				}				
			}
		}
		if (atributo.equals("dataIda")) {
			for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
				if (caronaRelampago.getIdCarona().equals(idCarona)) {
					return caronaRelampago.getDataIda()+"";
				}				
			}
		}
		if (atributo.equals("dataVolta")) {
			for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
				if (caronaRelampago.getIdCarona().equals(idCarona)) {
					return caronaRelampago.getDataVolta()+"";
				}				
			}
		}
		return null;
	}

	public String getMinimoCaroneiros(String idCarona) throws CaronaException {
		for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
			if (caronaRelampago.getIdCarona().equals(idCarona)) {
				return caronaRelampago.getMinimoCaroneiros();
			}				
		}
		throw new CaronaException("IdCarona não existe");
	}
	public String getTrajeto(String idCarona) throws CaronaException {
		for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
			if (caronaRelampago.getIdCarona().equals(idCarona)) {
				return caronaRelampago.getOrigem() +" - " + caronaRelampago.getDestino();
			}				
		}
		return null;
	}
	public String getCaronaRelampago(String idCarona) {
		for (CaronaRelampago caronaRelampago : interesseCaronasRelamlago) {
			if (caronaRelampago.getIdCarona().equals(idCarona)) {
				return caronaRelampago.getOrigem()+" para "+caronaRelampago.getDestino()
						+", no dia "+caronaRelampago.getDataIda()+", as "+caronaRelampago.getHora();
			}
			
		}
		return null;
	}
	
	/**
	 * Verifica se o parametro data é um formato da data com o padrão
	 * dia/mes/ano. Sendo que o dia tem dois digitos, o mes dois digitos e ano
	 * quatro digitos. (dd/mm/aaaa)
	 * 
	 * @param data
	 * @return se a data seguir o padrão (dd/mm/aaaa) retorna <code>true</code>,
	 *         caso contrario <code>false</code>
	 */
	@SuppressWarnings("unused")
	private boolean isData(String data) {
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			formatoData.setLenient(false);
			Date dataFormatada = formatoData.parse(data);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
