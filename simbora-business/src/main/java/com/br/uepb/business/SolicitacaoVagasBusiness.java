package com.br.uepb.business;

import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.PontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;

/**
 * O responsável por gerenciar as solicitações de vagas nas caronas pelos
 * usuúrios
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SolicitacaoVagasBusiness {

	SolicitacaoVagasDomain solicitacaoVagas;
	private SolicitacaoVagasDaoImp solicitacaoVagasDaoImp = new SolicitacaoVagasDaoImp();
	private EnviarEmail enviarEmail = new EnviarEmail();


	/**
	 * Cria uma solicitação de vaga na carona.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return id da solicitação da vaga.
	 * @throws CaronaException 
	 */
	public String solicitarVaga(String idSessao, String idCarona) throws CaronaException {
		try {
			CaronaDaoImp caronaDaoImp = new CaronaDaoImp();
			CaronaDomain carona = caronaDaoImp.getCarona(idCarona);
			if(carona.getEhPreferencial()){
				for (String login : caronaDaoImp.getUsuariosPreferenciais(carona.getIdUsuario())) {
					if(login.equals(idSessao)){
						solicitacaoVagas = new SolicitacaoVagasDomain();

						solicitacaoVagas.setIdSessao(idSessao);
						solicitacaoVagas.setIdCarona(idCarona);

						solicitacaoVagasDaoImp.save(solicitacaoVagas);
						return solicitacaoVagasDaoImp.getId()+"";
					}
				}
				throw new CaronaException("Usuário não está na lista preferencial da carona");
				
			}
			
		} catch (Exception e) {
			throw e;
		}


		solicitacaoVagas = new SolicitacaoVagasDomain();

		solicitacaoVagas.setIdSessao(idSessao);
		solicitacaoVagas.setIdCarona(idCarona);

		solicitacaoVagasDaoImp.save(solicitacaoVagas);
		return solicitacaoVagasDaoImp.getId()+"";
	}

	/**
	 * Define o status da solicitação da carona para 'Aceita'.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception 
	 * @throws CaronaException 
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		
			SolicitacaoVagasDomain solicitacao = solicitacaoVagasDaoImp.getSolicitacaoVagas(idSolicitacao);
			if(solicitacao.getStatus().equals("Aceita")){
				throw new Exception("Solicitação já foi aceita");
			}
			else if(solicitacao.getStatus().equals("Recusada")){
				throw new Exception("Solicitação foi recusada");
			}else{
				solicitacao.setStatus("Aceita");
				new CaronaBusiness().reduzQtdVagas(solicitacao.getIdCarona());
				solicitacaoVagasDaoImp.update(solicitacao);
			}
	}

	/**
	 * Define o status da carona para 'Recusada' Se a solicitacaoVagas estiver
	 * 'Pendente'. Caso não encontre retornará 'Solicitação inexistente'.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		try {
			SolicitacaoVagasDomain solicitacao = solicitacaoVagasDaoImp.getSolicitacaoVagas(idSolicitacao);
			if (solicitacao.getStatus().equals("Pendente")) {

				solicitacao.setStatus("Recusada");
				solicitacaoVagasDaoImp.update(solicitacao);
				return;
			} else {
				throw new Exception("Solicitação inexistente");
			}
			
		} catch (Exception e) {
			throw new CaronaException("Solicitação inexistente");
		}

	}

	/**
	 * Retorna o nome do Dono da solicitação ou Dono da Carona.
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return no do Dono da carona ou da Solicitação da carona.
	 */
	public String getAtributo(String idSolicitacao, String atributo) {
		
		SolicitacaoVagasDomain solicitacao = solicitacaoVagasDaoImp.getSolicitacaoVagas(idSolicitacao);
		try {
			return new CaronaBusiness().getAtributoCarona(
					solicitacao.getIdCarona(), atributo);
		} catch (Exception f) {
			f.printStackTrace();
		}
		if (atributo.equals("Dono da carona")) {
			CaronaDomain carona = new CaronaDaoImp().getCarona(solicitacao.getIdCarona());
			return new UsuarioBusiness().getAtributoUsuario(
					carona.getIdUsuario(), "nome");
		}

		if (atributo.equals("Dono da solicitacao")) {
			return new UsuarioBusiness().getAtributoUsuario(
					solicitacao.getIdSessao(), "nome");
		}
		if (atributo.equals("Ponto de Encontro")) {
			return new PontoDeEncontroDaoImp().getPontoDeEncontro(solicitacao.getIdPonto()+"").getPontos();
		}

		return "";
	}

	private List<SolicitacaoVagasDomain> getSolicitacoesVagas() {
		
		return solicitacaoVagasDaoImp.list();
	}

	public boolean ehCaroneiro(String login, String idCarona) {

		for (SolicitacaoVagasDomain solicitacaoVagas : getSolicitacoesVagas()) {

			if (solicitacaoVagas.getIdCarona().equals(idCarona)
					&& solicitacaoVagas.getIdSessao().equals(login)) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Retorna todos os ids das solicitações que foram aceitas.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (SolicitacaoVagasDomain solicitacao : getSolicitacoesVagas()) {

			if (solicitacao.getIdCarona().equals(idCarona)
					&& CaronaBusiness.ehMotorista(idSessao, idCarona)
					&& solicitacao.getStatus().equals("Aceita")) {

				if (!flag) {
					ids += ",";
				}
				ids += solicitacao.getIdSolicitacao();
				flag = false;

			}
		}
		return ids + "}";
	}

	/**
	 * Retorna todos os ids das solicitações que estão pendentes.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona) {

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (SolicitacaoVagasDomain solicitacao : getSolicitacoesVagas()) {

			if (solicitacao.getIdCarona().equals(idCarona)
					&& CaronaBusiness.ehMotorista(idSessao, idCarona)
					&& solicitacao.getStatus().equals("Pendente")) {

				if (!flag) {
					ids += ",";
				}
				ids += solicitacao.getIdSolicitacao();
				flag = false;
			}
		}
		return ids + "}";
	}
	public boolean enviarEmail(String idSessao, String destino, String mensagem){
		return enviarEmail.enviarEmail(destino, "Simbora", mensagem);
	}

}
