package com.br.uepb.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;


/**
 * Classe respons�vel por gerenciar a carona. Define as funcionalidades de cadastrar carona, localizar caronas e
 * outras funcionalidades ligadas a carona.
 * 
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class CaronaBusiness {

	public static List<CaronaDomain> caronas = new CaronaDaoImp().list();
	CaronaDomain carona;
	List<SessaoDomain> sessao = SessaoBusiness.getSessoes();

	public void zerarSistema() {
		for (CaronaDomain carona : caronas) {
			try {
				new CaronaDaoImp().save(carona);
			} catch (Exception e) {
			}
		}
		caronas.clear();
	}

	/**
	 * Esse metodo dever� retornar as poss�veis caronas cadastradas no sistema.
	 * Dependendo dos valores passados nos parametros dever� ser retornado a 
	 * origem e destino que se deseja.  
	 *  
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return <code>Se</code> origem for <code>null</code> ent�o retorna 
	 * @throws Exception
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {
		if (idSessao == null) {
			throw new CaronaException("Sess�o inv�lida");
		}

		if (origem.equals("-") || origem.equals("()") || origem.equals("!")
				|| origem.equals("!?")) {
			throw new CaronaException("Origem inv�lida");
		}

		if (destino.equals(".") || destino.equals("()") || destino.equals("!?")) {
			throw new CaronaException("Destino inv�lido");
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

	private String origemDestinoCarona(String origem, String destino) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for (CaronaDomain carona : caronas) {
			if (carona.getLocalDeOrigem().equals(origem)
					&& carona.getLocalDeDestino().equals(destino)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getIdCarona();
				flag = false;
			}
			if (ids.equals("{0") || ids.equals("{0,")) {
				ids = "{";
				flag = true;
			}
		}

		return ids + "}";
	}

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

	private String origemCarona(String origem) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
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

	private String destinoCarona(String destino) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
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
	 * Cadastra a carona e retorna um identificador da carona.
	 * 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param qtdDeVagas
	 * @return idCarona
	 * @throws Exception
	 */
	public String cadastrarCarona(String idSessao, String origem, 
			String destino, String data, String hora, String qtdDeVagas)
			throws Exception {
		if (idSessao == null || idSessao.trim().isEmpty()) {
			throw new SessaoException("Sess�o inv�lida");
		}
		if (!SessaoBusiness.hasSessao(idSessao)) {
			throw new SessaoException("Sess�o inexistente");
		}
		if (origem == null || origem.trim().isEmpty()) {
			throw new CaronaException("Origem inv�lida");
		}
		if (destino == null || destino.trim().isEmpty()) {
			throw new CaronaException("Destino inv�lido");
		}
		if (data == null || data.trim().isEmpty()) {
			throw new CaronaException("Data inv�lida");
		}
		if (!isData(data)) {
			throw new CaronaException("Data inv�lida");
		}
		if (hora == null || hora.trim().isEmpty()) {
			throw new CaronaException("Hora inv�lida");
		}
		if (!isHora(hora)) {
			throw new CaronaException("Hora inv�lida");
		}
		if (qtdDeVagas == null 
				|| qtdDeVagas.trim().isEmpty()) {
			throw new CaronaException("Vaga inv�lida");
		}
		try {
			Integer.parseInt(qtdDeVagas);
		} catch (Exception e) {
			throw new CaronaException("Vaga inv�lida");
		}

		carona = new CaronaDomain();
		carona.setLocalDeOrigem(origem);
		carona.setLocalDeDestino(destino);
		carona.setData(data);
		carona.setHorarioDeSaida(hora);
		carona.setQtdDeVagas(qtdDeVagas);
		carona.setIdSessao(idSessao);

		caronas.add(carona);

		carona.setIdCarona((caronas.indexOf(carona)) + "");

		return carona.getIdCarona() + "";

	}

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

	/**
	 * Retorna o atributo que o usu�rio precisar (origem, destino, data e hora).
	 * 
	 * @param idCarona
	 * @param atributo
	 * @return
	 * @throws CaronaException
	 */
	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException {
		if (idCarona == null || idCarona.equals("")) {
			throw new CaronaException("Identificador do carona � inv�lido");
		}

		if (atributo == null) {
			throw new CaronaException("Atributo inv�lido");
		}
		if (atributo.equals("")) {
			throw new CaronaException("Atributo inv�lido");
		}
		if (!idCaronaExistir(idCarona)) {
			throw new CaronaException("Item inexistente");
		}

		if (atributo.equals("origem")) {
			return caronas.get(Integer.valueOf(idCarona)).getLocalDeOrigem();
		}
		if (atributo.equals("destino")) {
			return caronas.get(Integer.valueOf(idCarona)).getLocalDeDestino();
		}
		if (atributo.equals("data")) {
			return caronas.get(Integer.valueOf(idCarona)).getData();
		}
		if (atributo.equals("vagas")) {
			return caronas.get(Integer.valueOf(idCarona)).getQtdDeVagas() + "";
		}

		throw new CaronaException("Atributo inexistente");
	}

	/**
	 * Verifica se existe a carona!
	 * 
	 * @param idCarona
	 * @return {@link Boolean}
	 */
	private boolean idCaronaExistir(String idCarona) {
		for (CaronaDomain carona : caronas) {
			if ((carona.getIdCarona().equals(idCarona))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a origem e destino da carona.
	 * 
	 * @param idCarona
	 * @return origem - destino
	 * @throws CaronaException
	 */
	public String getTrajeto(String idCarona) throws CaronaException {
		if (idCarona == null) {
			throw new CaronaException("Trajeto Inv�lida");
		}
		if (idCarona.trim().isEmpty()) {
			throw new CaronaException("Trajeto Inexistente");
		}
		try {
			Integer.valueOf(idCarona);
		} catch (NumberFormatException e) {
			throw new CaronaException("Trajeto Inexistente");
		}		
		try {
			return caronas.get(Integer.valueOf(idCarona)).getLocalDeOrigem()
				+ " - "
				+ caronas.get(Integer.valueOf(idCarona)).getLocalDeDestino();
		} catch (Exception e) {
			throw new CaronaException("");										
		}
		
	}

	/**
	 * 
	 * @param idCarona
	 * @return
	 * @throws CaronaException
	 */
	public String getCarona(String idCarona) throws CaronaException {
		if (idCarona == null) {
			throw new CaronaException("Carona Inv�lida");
		}
		if (idCarona.isEmpty()) {
			throw new CaronaException("Carona Inexistente");
		}
		int valor;
		try {
			valor = Integer.valueOf(idCarona);
			if (valor > caronas.size()) {
				throw new CaronaException("Carona Inexistente");
			}
		} catch (Exception e) {
			throw new CaronaException("Carona Inexistente");
		}

		CaronaDomain carona = caronas.get(Integer.valueOf(idCarona));
		return carona.getLocalDeOrigem() + " para "
				+ carona.getLocalDeDestino() + ", no dia " + carona.getData()
				+ ", as " + carona.getHorarioDeSaida();
	}

	public static List<CaronaDomain> getCaronas() {
		return caronas;
	}

	public static void setCaronas(List<CaronaDomain> caronas) {
		CaronaBusiness.caronas = caronas;
	}

	public CaronaDomain getCarona() {
		return carona;
	}

	public void setCarona(CaronaDomain carona) {
		this.carona = carona;
	}

	public List<SessaoDomain> getSessao() {
		return sessao;
	}

	public void setSessao(List<SessaoDomain> sessao) {
		this.sessao = sessao;
	}

	public void reduzQtdVagas(String idcarona) {

		for (CaronaDomain carona : caronas) {
			if (carona.getIdCarona().equals(idcarona)) {
				int qtdVagasAtual = Integer.parseInt(carona.getQtdDeVagas()) - 1;
				carona.setQtdDeVagas(qtdVagasAtual + "");
			}
		}
	}

	public void aumentaQtdVagas(String idcarona) {

		for (CaronaDomain carona : caronas) {
			if (carona.getIdCarona().equals(idcarona)) {
				int qtdVagasAtual = Integer.parseInt(carona.getQtdDeVagas()) + 1;
				carona.setQtdDeVagas(qtdVagasAtual + "");
			}
		}
	}

	public static boolean ehMotorista(String login, String idCarona) {
		for (CaronaDomain carona : caronas) {
			if (idCarona.equals(carona.getIdCarona())
					&& login.equals(carona.getIdSessao())) {
				return true;
			}
		}

		return false;
	}

	public String getCaronaUsuario(String idSessao, String indexCarona) {
		try {
			
			int indice = Integer.parseInt(indexCarona) - 1;
			int cont = 0;
			// percorre as caronas para verificar qual a carona x do usuario
			for (CaronaDomain carona : caronas) {
				if (carona.getIdSessao().equals(idSessao)) {
					if (cont == indice) {
						return carona.getIdCarona();
					}
					cont++;
				}
			}

		} catch (Exception e) {

		}
		return "";
	}

	public String getTodasCaronasUsuario(String idSessao) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for (CaronaDomain carona : caronas) {
			if (carona.getIdSessao().equals(idSessao)) {
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
