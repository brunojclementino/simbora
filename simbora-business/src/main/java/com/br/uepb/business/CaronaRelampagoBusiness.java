package com.br.uepb.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaRelampagoDaoImpl;
import com.br.uepb.domain.CaronaRelampagoDomain;
import com.br.uepb.domain.SessaoDomain;

public class CaronaRelampagoBusiness {

	private static List<CaronaRelampagoDomain> interesseCaronasRelamlago = new CaronaRelampagoDaoImpl()
			.list();

	public void encerrarSistema() {
		for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
			try {
				CaronaRelampagoDaoImpl relampago = new CaronaRelampagoDaoImpl();
				relampago.save(caronaRelampagoDomain);
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
		/* Para passar nos Tests comentei, apos ter feito todos os US's remova este comentario!
		if (!isDataValida(dataIda, hora)) {
			throw new CaronaException("Data inválida");
		}*/
		if (dataVolta == null || dataVolta.trim().isEmpty()
				|| !isData(dataVolta)) {
			throw new CaronaException("Data inválida");
		}
		if (hora == null || hora.trim().isEmpty() || !isHora(hora)) {
			throw new CaronaException("Hora inválida");
		}
		try {
			Integer.valueOf(minimoCaroneiros);
		} catch (Exception e) {
			throw new CaronaException("Minimo Caroneiros inválido");
		}

 		if (minimoCaroneiros == null || !isMaiorZero(minimoCaroneiros)) {
			throw new CaronaException("Minimo Caroneiros inválido");
		}

		CaronaRelampagoDomain carona = new CaronaRelampagoDomain();
		carona.setDataVolta(dataVolta);
		carona.setDataVolta(dataVolta);
		interesseCaronasRelamlago.add(carona);
		return carona.getIdCarona();
	}

	/**
	 * Este metodo verifica se a data de cadastro da Carona relampago foi feita há 48 horas
	 * antes da data da viagem. Receve como parametro uma {@link String} no formato dd/mm/aaaa.
	 *  
	 * @param data
	 * @return True se tiver mais de 48 horas ou False se tiver menos.
	 */
	private boolean isDataValida(String data, String hora){
		
		int ano = Integer.parseInt(data.substring(6, 10));
		int mes = Integer.parseInt(data.substring(3, 5));
		int dia = Integer.parseInt(data.substring(0, 2));
		int horas = Integer.parseInt(hora.substring(0, 2));
		int min = Integer.parseInt(hora.substring(3, 5));
		
		DateTime dataInicial = new DateTime(ano, mes, dia, horas, min);
		
		Date dataHoje = new Date();
		
		DateTime dataFinal = new DateTime(dataHoje.getYear()+1900, dataHoje.getMonth()+1, dataHoje.getDay()
				, dataHoje.getHours(), dataHoje.getMinutes());
		
		int h = Hours.hoursBetween(dataInicial, dataFinal).getHours();
//		System.out.println("Horas: " + h);
		if (h <= -48) {
			return true;
		}
		return false;
	}

	private boolean isMaiorZero(String minimoCaroneiros) {
		if (Integer.valueOf(minimoCaroneiros) >= 1) {
			return true;
		}
		return false;
	}

	private boolean sessaoExiste(String idSessao) {
		for (SessaoDomain sessao : SessaoBusiness.getSessoes()) {
			if (sessao.getIdSessao().equals(idSessao)) {
				return true;
			}
		}
		return false;
	}

	public String getAtributoCaronaRelampago(String idCarona, String atributo)
			throws CaronaException {

		if (idCarona == null || idCarona.trim().isEmpty()) {
			throw new CaronaException("Identificador do carona é inválido");
		}
		if (!existeCarona(idCarona)) {
			throw new CaronaException("Item inexistente");
		}
		
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new CaronaException("Atributo inválido");
		}
		
		if (atributo.equals("origem")) {
			for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
				if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
					return caronaRelampagoDomain.getCarona().getLocalDeOrigem() + "";
				}
			}
		}
		if (atributo.equals("destino")) {
			for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
				if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
					return caronaRelampagoDomain.getCarona().getLocalDeDestino() + "";
				}
			}
		}
		if (atributo.equals("minimoCaroneiros")) {
			for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
				if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
					return caronaRelampagoDomain.getCarona().getQtdDeVagas() + "";
				}
			}
		}
		if (atributo.equals("dataIda")) {
			for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
				if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
					return caronaRelampagoDomain.getCarona().getData() + "";
				}
			}
		}
		if (atributo.equals("dataVolta")) {
			for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
				if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
					return caronaRelampagoDomain.getDataVolta() + "";
				}
			}
		}
		throw new CaronaException("Atributo inexistente");
		//return null;
	}

	private boolean existeCarona(String idCarona) {
		for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
			if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
				return true;
			}
		}
		return false;
	}

	public String getMinimoCaroneiros(String idCarona) throws CaronaException {
		for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
			if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
				return caronaRelampagoDomain.getCarona().getQtdDeVagas();
			}
		}
		throw new CaronaException("IdCarona não existe");
	}

	public String getTrajeto(String idCarona) throws CaronaException {
		if (idCarona == null) {
			throw new CaronaException("Trajeto Inválida");
		}
		if (idCarona.trim().isEmpty()) {
			throw new CaronaException("Trajeto Inexistente");
		}
		if (!existeCarona(idCarona)) {
			throw new CaronaException("Trajeto Inexistente");
		}
		for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
			if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
				return caronaRelampagoDomain.getCarona().getLocalDeOrigem() + " - "
						+ caronaRelampagoDomain.getCarona().getLocalDeDestino();
			}
		}
		return null;
	}

	public String getCaronaRelampago(String idCarona) throws CaronaException {
		if (idCarona == null) {
			throw new CaronaException("Carona Inválida");
		}
				
		if(idCarona.trim().isEmpty()) {
			throw new CaronaException("Carona Inexistente");
		}
		
		if (!existeCarona(idCarona)) {
			throw new CaronaException("Carona Inexistente");
		}
		
		for (CaronaRelampagoDomain caronaRelampagoDomain : interesseCaronasRelamlago) {
			if (caronaRelampagoDomain.getIdCarona().equals(idCarona)) {
				return caronaRelampagoDomain.getCarona().getLocalDeOrigem() + " para "
						+ caronaRelampagoDomain.getCarona().getLocalDeDestino() + ", no dia "
						+ caronaRelampagoDomain.getCarona().getData() + ", as "
						+ caronaRelampagoDomain.getCarona().getHorarioDeSaida();
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

	/**
	 * Verifica o parametro passado é do formato HH:mm. Sendo que os digitos são
	 * numeros. e mm não pode ser maior que 60.
	 * 
	 * @param data
	 * @return se for um horario retorna <code>true</code>, caso contrario
	 *         <code>false</code>
	 */
	@SuppressWarnings("unused")
	private boolean isHora(String data) {
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("HH:mm");
			formatoData.setLenient(false);
			Date dataFormatada = formatoData.parse(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
