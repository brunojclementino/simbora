package com.br.uepb.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.CaronaMunicipalDaoImpl;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaMunicipalDamain;

public class CaronaMunicipalBusiness {

	private static List<CaronaDomain> interesseCaronasRelamlago = new CaronaMunicipalDaoImpl()
	.list();
	public static List<CaronaDomain> caronas = new CaronaDaoImp().list();

	
	public String localizarCaronaMunicipal(String idSessao, String cidade, 
			String origem, String destino) throws CaronaException{
		
		if (idSessao == null) {
			throw new CaronaException("Sessão inválida");
		}

		if (cidade == null || cidade.isEmpty()) {
			throw new CaronaException("Cidade inexistente");
		}
				
		if (origem.equals("-") || origem.equals("()") || origem.equals("!")
				|| origem.equals("!?")) {
			throw new CaronaException("Origem inválida");
		}

		if (destino.equals(".") || destino.equals("()") || destino.equals("!?")) {
			throw new CaronaException("Destino inválido");
		}

		if (!origem.isEmpty() && !destino.isEmpty()) {
			return origemDestinoCarona(origem, destino); 
		}

		if (origem.isEmpty() && destino.isEmpty()) {
			return origemDestinoCarona();
		}

		if (origem.isEmpty() && !destino.isEmpty()) {
			return destinoCarona(destino);
		}

		if (!origem.isEmpty() && destino.isEmpty()) {
			return origemCarona(origem);
		}
		return "";
	}
	
	/**
	 * Esse método será chamado se tanto a origem quanto o destino forem
	 * <code>null</code>.
	 * 
	 * @return todos os id's das coronas.
	 */
	private String origemDestinoCarona() {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0 (Serve para
							// auxiliar na formata��o da string de retorno
		for (CaronaDomain carona : caronas) {
			if (!flag) {
				ids += ",";
			}
			ids += carona.getIdCarona();
			flag = false;
		}

		return ids + "}";
	}

	/**
	 * Esse método será chamado caso a origem e destino não for vazias!
	 * 
	 * @param origem
	 * @param destino
	 * @return os ids das coronas que correspendem ao mesmo destino e origem.
	 */
	private String origemDestinoCarona(String origem, String destino) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaDomain carona : caronas) {
			if (carona.getLocalDeOrigem().equals(origem)
					&& carona.getLocalDeDestino().equals(destino)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getIdCarona();
				flag = false;
			}
			/*if (ids.equals("{0") || ids.equals("{0,")) {
				ids = "{";
				flag = true;
			}*/
		}

		return ids + "}";
	}

	/**
	 * Esse método retorna todos os id's das caronas que têm a mesma origem do
	 * parametro do metodo.
	 * 
	 * @param origem
	 * @return todos os ids da carona que tem origem iguais ao passado pelo
	 *         parametro.
	 */
	private String origemCarona(String origem) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaDomain carona : caronas) {
			if (carona.getLocalDeOrigem().equals(origem)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getIdCarona();
				flag = false;
			}
		}

		return ids + "}";
	}

	/**
	 * Esse método retorna todos os id's das caronas que têm a mesmo destino do
	 * parametro do metodo.
	 * 
	 * @param destino
	 * @return todos os ids da carona que tem destino iguais ao passado pelo
	 *         parametro.
	 */
	private String destinoCarona(String destino) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaDomain carona : caronas) {
			if (carona.getLocalDeDestino().equals(destino)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getIdCarona();
				flag = false;
			}
		}

		return ids + "}";
	}

	
	/**
	 * Carona feitas na zona urbana serão identificadas como uma carona
	 * qualquer, mas para identificar que é dentro na cidade terá o
	 * identificador da carona um 'm' no final do codigo.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return
	 * @throws CaronaException
	 */
	public String cadastrarCaronaMunicipal(String idSessao, String origem, 
			String destino, String cidade, String data, String hora,
			String vagas) throws CaronaException {
		
		if (idSessao == null || idSessao.trim().isEmpty()) {
			throw new CaronaException("Sessão inválida");
		}
		if (!SessaoBusiness.hasSessao(idSessao)) {
			throw new CaronaException("Sessão inexistente");
		}
		if (origem == null || origem.trim().isEmpty()) {
			throw new CaronaException("Origem inválida");
		}
		if (destino == null || destino.trim().isEmpty()) {
			throw new CaronaException("Destino inválido");
		}
		if (data == null || data.trim().isEmpty()) {
			throw new CaronaException("Data inválida");
		}
		if (!isData(data)) {
			throw new CaronaException("Data inválida");
		}
		if (hora == null || hora.trim().isEmpty()) {
			throw new CaronaException("Hora inválida");
		}
		if (!isHora(hora)) {
			throw new CaronaException("Hora inválida");
		}
		if (vagas == null || vagas.trim().isEmpty()) {
			throw new CaronaException("Vaga inválida");
		}
		try {
			Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new CaronaException("Vaga inválida");
		}

		CaronaDomain carona = new CaronaDomain();

		carona.setIdCarona((caronas.size()));
		int id = carona.getIdCarona();
		
		carona.setLocalDeOrigem(origem);
		carona.setLocalDeDestino(destino);
		carona.setLocalDeOrigem(cidade);
		carona.setData(data);
		carona.setHorario(hora);
		carona.setQtdDeVagas(vagas);
		carona.setIdSessao(idSessao);
		
		CaronaMunicipalDaoImpl dao = new CaronaMunicipalDaoImpl();
		
		CaronaMunicipalDamain municipal = new CaronaMunicipalDamain();
		municipal.setCarona(carona);
		municipal.setCidade(cidade);
	
		dao.save(municipal);
		caronas = dao.list();
		
		return getCarona(carona);
	}

	private String getCarona(CaronaDomain carona) {
		for (CaronaDomain caronaDomain : caronas) {
			if (caronaDomain.getIdSessao().equals(carona.getIdSessao())
					&& caronaDomain.getData().equals(carona.getData())
					&& caronaDomain.getLocalDeOrigem().equals(carona.getLocalDeOrigem())) {
				return caronaDomain.getIdCarona()+"";
			}		
		}
		return null;
	}
	
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
	public String localizarCaronaMunicipal(String idSessao, String cidade) throws CaronaException { 
		if (idSessao == null) {
			throw new CaronaException("Sessão inválida");
		}
		if (cidade == null || cidade.trim().isEmpty()) {
			throw new CaronaException("Cidade inválido");
		}
		if (!cidade.isEmpty()) {
			return destinoCidade(cidade);
		}
		return null;
	}

	private String destinoCidade(String cidade) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaDomain carona : caronas) {
			if (carona.getLocalDeOrigem().equals(cidade)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getIdCarona();
				flag = false;
			}
		}

		return ids + "}";
	}

}
