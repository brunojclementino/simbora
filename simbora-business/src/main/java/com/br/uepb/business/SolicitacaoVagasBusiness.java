package com.br.uepb.business;

import java.util.List;

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
	public static List<SolicitacaoVagasDomain> 
		solicitacoesVagas = new SolicitacaoVagasDaoImp().list();

	/**
	 * Salva todoas as solicitacaoVagas e depois limpa a lista de
	 * {@link SolicitacaoVagasDomain}.
	 */
	public void encerrarSistema() {
		for (SolicitacaoVagasDomain solicitacaoVagas : solicitacoesVagas) {
			try {
				SolicitacaoVagasDaoImp solicitacaoVagasDaoImp = new SolicitacaoVagasDaoImp();
				solicitacaoVagasDaoImp.save(solicitacaoVagas);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		solicitacoesVagas.clear();
	}

	/**
	 * Cria uma solicitação de vaga na carona.
	 * @param idSessao
	 * @param idCarona
	 * @return id da solicitação da vaga.
	 */
	public String solicitarVaga(String idSessao, String idCarona) {

		solicitacaoVagas = new SolicitacaoVagasDomain();

		solicitacaoVagas.setIdSessao(idSessao);
		solicitacaoVagas.setIdCarona(idCarona);

		solicitacoesVagas.add(solicitacaoVagas);

		solicitacaoVagas.setIdSolicitacao(solicitacoesVagas
				.indexOf(solicitacaoVagas) + "V");// id da solicitação de vaga

		return solicitacaoVagas.getIdSolicitacao();
	}

	/**
	 * Define o status da solicitação da carona para 'Aceita'.
	 * @param idSessao
	 * @param idSolicitacao
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) {

		for (SolicitacaoVagasDomain solicitacao : solicitacoesVagas) {

			if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
				solicitacao.setStatus("Aceita");
				new CaronaBusiness().reduzQtdVagas(solicitacao.getIdCarona());
				return;
			}
		}
	}

	/**
	 * Define o status da carona para 'Recusada' Se a solicitacaoVagas estiver 'Pendente'. Caso não encontre 
	 * retornará 'Solicitação inexistente'.
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		for (SolicitacaoVagasDomain solicitacao : solicitacoesVagas) {

			if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
				if (solicitacao.getStatus().equals("Pendente")) {

					solicitacao.setStatus("Recusada");
					return;
				} else {
					throw new Exception("Solicitação inexistente");
				}
			}

		}

	}

	/**
	 * Retorna o nome do Dono da solicitação ou Dono da Carona.
	 * @param idSolicitacao
	 * @param atributo
	 * @return no do Dono da carona ou da Solicitação da carona.
	 */
	public String getAtributo(String idSolicitacao, String atributo) {
		for (SolicitacaoVagasDomain solicitacao : solicitacoesVagas) {
			if (solicitacao.getIdSolicitacao().equals(idSolicitacao)) {
				try {
					return new CaronaBusiness().getAtributoCarona(
							solicitacao.getIdCarona(), atributo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (atributo.equals("Dono da carona")) {
					for (CaronaDomain carona : CaronaBusiness.getCaronas()) {
						if (carona.getIdCarona().equals(
								solicitacao.getIdCarona())) {
							return new UsuarioBusiness().getAtributoUsuario(
									carona.getIdSessao(), "nome");
						}
					}

				}

				if (atributo.equals("Dono da solicitacao")) {
					return new UsuarioBusiness().getAtributoUsuario(
							solicitacao.getIdSessao(), "nome");
				}
			}
		}

		return "";
	}

	
public static boolean ehCaroneiro(String login, String idCarona) {
		
		for (SolicitacaoVagasDomain solicitacaoVagas : solicitacoesVagas) {
			
			if(solicitacaoVagas.getIdCarona().equals(idCarona) && solicitacaoVagas.getIdSessao().equals(login)){
				return true;
			}
			
		}
		
		return false;
	}

	/**
	 * Retorna todos os ids das solicitações que foram aceitas.
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (SolicitacaoVagasDomain solicitacao : solicitacoesVagas) {

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
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona) { 

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids é 0
		for (SolicitacaoVagasDomain solicitacao : solicitacoesVagas) {

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
}
