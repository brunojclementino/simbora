package com.br.uepb.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.type.TrueFalseType;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.CaronaMunicipalDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.CaronaMunicipalDomain;
import com.br.uepb.domain.SessaoDomain;


public class CaronaMunicipalBusiness {


	public static Logger logger = Logger.getLogger(CaronaMunicipalBusiness.class);
	CaronaMunicipalDomain caronaMunicipal;
	CaronaMunicipalDaoImp caronaMunicipalDaoImpl = new CaronaMunicipalDaoImp();

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
//		if (!SessaoBusiness.hasSessao(idSessao)) {
//			throw new CaronaException("Sessão inexistente");
//		}
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
		carona.setData(data);
		carona.setHora(hora);
		carona.setIdUsuario(idSessao);
		carona.setDestino(destino);
		carona.setOrigem(origem);
		carona.setVagas(vagas);
		caronaMunicipal = new CaronaMunicipalDomain();
		caronaMunicipal.setCidade(cidade);
		carona.setCaronaMunicipal(caronaMunicipal);
		caronaMunicipalDaoImpl.save(carona);
		return caronaMunicipalDaoImpl.getId()+"";
	}

	
	/**
	 * Return a String do id da carona municipal.
	 * @param carona2
	 * @return
	 */
	/*private String getCaronaUsuario(CaronaMunicipalDomain carona2) {
		for (CaronaMunicipalDomain caronaMunicipalDomain : caronas) {
			if (caronaMunicipalDomain.getId() == carona2.getId()) {
				return carona2.getId()+"";
			}
			
		}
		return null;
	}*/

	public boolean getAtributo(String idCarona, String atributo) {
		if(atributo.equals("ehMunicipal")){
			return caronaMunicipalDaoImpl.ehMunicipal(idCarona);
		}
		return false;
	}

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

	/**
	 * Verifica se é motorista.
	 * 
	 * @param login
	 * @param idCarona
	 * @return Se for motorista {@link TrueFalseType}, caso contrario
	 *         <code>false</code>
	 */
	/*public boolean ehMotorista(String login, String idCarona) {
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (idCarona.equals(carona.getId())
					&& login.equals(carona.getIdUsuario())) {
				return true;
			}
		}

		return false;
	}*/

	/**
	 * Retorna o id da carona.
	 * 
	 * @param idSessao
	 * @param indexCarona
	 * @return
	 */
	/*public String getCaronaUsuario(String idSessao, String indexCarona) {
		try {

			int indice = Integer.parseInt(indexCarona) - 1;
			int cont = 0;
			// percorre as caronas para verificar qual a carona x do usuario
			for (CaronaMunicipalDomain carona : caronas) {
				if (carona.getCarona().getIdSessao().equals(idSessao)) {
					if (cont == indice) {
						return carona.getCarona().getIdCarona()+"";
					}
					cont++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}*/

	/**
	 * Retorna todas as caronas dos usuarios.
	 * 
	 * @param idSessao
	 * @return
	 */
	/*public String getTodasCaronasUsuario(String idSessao) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaMunicipalDomain carona : caronas) {
			if (carona.getCarona().getIdSessao().equals(idSessao)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getCarona().getIdCarona();
				flag = false;
			}
		}
		return ids + "}";
	}*/

	private String destinoCidade(String cidade) {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (carona.getCaronaMunicipal().getCidade().equals(cidade)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getId();
				flag = false;
			}
		}

		return ids + "}";
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
	 * Esse método será chamado se tanto a origem quanto o destino forem
	 * <code>null</code>.
	 * 
	 * @return todos os id's das coronas.
	 */
	private String origemDestinoCarona() {
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0 (Serve para
							// auxiliar na formata��o da string de retorno
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (!flag) {
				ids += ",";
			}
			ids += carona.getId();
			flag = false;
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
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (carona.getOrigem().equals(origem)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getId();
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
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (carona.getDestino().equals(destino)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getId();
				flag = false;
			}
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
		for (CaronaDomain carona : getCaronasMunicipais()) {
			if (carona.getOrigem().equals(origem)
					&& carona.getDestino().equals(destino)) {
				if (!flag) {
					ids += ",";
				}
				ids += carona.getId();
				flag = false;
			}
			/*if (ids.equals("{0") || ids.equals("{0,")) {
				ids = "{";
				flag = true;
			}*/
		}

		return ids + "}";
	}


	private List<CaronaDomain> getCaronasMunicipais() {
		return caronaMunicipalDaoImpl.list();
	}

}
