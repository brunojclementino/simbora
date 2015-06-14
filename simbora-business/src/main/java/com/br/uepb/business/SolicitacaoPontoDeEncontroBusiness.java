package com.br.uepb.business;

import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.PontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoPontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.PontoDeEncontroDomain;
import com.br.uepb.domain.SolicitacaoPontoDeEncontroDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * O responsável por gerenciar as solicitações de pontos de encontros feitas por
 * usuários a determinadas caronas
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SolicitacaoPontoDeEncontroBusiness {

	// US04
	/**
	 * Quando o caroneiro aceitar, deve-se atualizar a quantidade de vagas do
	 * carro.
	 * 
	 * @param idSessao
	 * @param idSolicitacao
	 */

	SolicitacaoPontoDeEncontroDaoImp solicitacaoPontoDeEncontroDaoImp = new SolicitacaoPontoDeEncontroDaoImp();

	private SolicitacaoPontoDeEncontroDomain solicitacaoEncontro;
	private PontoDeEncontroDomain pontoDeEncontro;

	/**
	 * Salva os pontos de encontro que foram aceitas e que ainda estão
	 * pendentes. Depois a List é limpada.
	 */
	public void encerrarSistema() {
	}

	/**
	 * Faz uma sugestão do ponto de encontro. Trata os possiveis erros de
	 * parametro <code>null</code>.
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return id da sugestão.
	 * @throws Exception
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {

		if (pontos == null || pontos.trim().isEmpty()) {
			throw new Exception("Ponto Inválido");
		}
		pontoDeEncontro = new PontoDeEncontroDomain();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(pontos);
		pontoDeEncontro.setIdSessao(idSessao);

		solicitacaoEncontro = new SolicitacaoPontoDeEncontroDomain();
		// 0 indica que esse ponto é o sugerido pelo caroneiro
		solicitacaoEncontro.setPontoDeEncontro(pontoDeEncontro, 0);
		solicitacaoEncontro.setPontoDeEncontro(new PontoDeEncontroDomain(), 1);
		solicitacaoEncontro.setPontoDeEncontro(new PontoDeEncontroDomain(), 2);

		solicitacaoPontoDeEncontroDaoImp.save(solicitacaoEncontro);
		return solicitacaoPontoDeEncontroDaoImp.getId()+"";
	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 * @return
	 * @throws Exception
	 */
	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {

		if (pontos == null || pontos.trim().isEmpty()) {
			throw new Exception("Ponto Inválido");
		}

		pontoDeEncontro = new PontoDeEncontroDomain();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(pontos);
		pontoDeEncontro.setIdSessao(idSessao);
		
		try {
			SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro = solicitacaoPontoDeEncontroDaoImp.getSolicitacaoPontoDeEncontro(idSugestao);
			solicitacaoPontoDeEncontro.setPontoDeEncontro(pontoDeEncontro, 1);
			solicitacaoPontoDeEncontroDaoImp.update(solicitacaoPontoDeEncontro);
			
		} catch (Exception e) {
			throw new CaronaException("Solicitação inválida");
		}

		return "";
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) {

		pontoDeEncontro = new PontoDeEncontroDomain();
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setPontos(ponto);
		pontoDeEncontro.setIdSessao(idSessao);

		PontoDeEncontroDomain encontro;
		for (SolicitacaoPontoDeEncontroDomain solicitacao : getSolicitacoes()) {
			encontro = solicitacao.getPontoDeEncontro(0);
			if (encontro.getIdSessao().equals(idSessao)
					&& encontro.getIdCarona().equals(idCarona)) {
				// 2 indica que esse ponto é confirmaçãoo do encontro pelo
				// caroneiro
				solicitacao.setPontoDeEncontro(pontoDeEncontro, 2);
				solicitacaoPontoDeEncontroDaoImp.update(solicitacao);
				SolicitacaoVagasDomain solicitacaoVaga = new SolicitacaoVagasDomain();
				solicitacaoVaga.setIdSessao(idSessao);
				solicitacaoVaga.setIdCarona(idCarona);
				solicitacaoVaga.setIdPonto(solicitacao.getPontoDeEncontro(2).getIdPonto());
				new SolicitacaoVagasDaoImp().save(solicitacaoVaga);
				return new SolicitacaoVagasDaoImp().getId()+"";
			}
		}

		return "";
	}

	private List<SolicitacaoPontoDeEncontroDomain> getSolicitacoes() {
		return solicitacaoPontoDeEncontroDaoImp.list();
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws CaronaException {
		/*try {
			SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro = solicitacaoPontoDeEncontroDaoImp.getSolicitacaoPontoDeEncontro(idSolicitacao);
			return getAtributo(solicitacaoPontoDeEncontro, atributo);
			
		} catch (Exception e) {*/
			return new SolicitacaoVagasBusiness().getAtributo(idSolicitacao, atributo);
		//}
		
	}

	/*private String getAtributo(SolicitacaoPontoDeEncontroDomain solicitacao,
			String atributo) {
		PontoDeEncontroDomain encontro = solicitacao.getPontoDeEncontro(2);
		try {
			return new CaronaBusiness().getAtributoCarona(
					encontro.getIdCarona(), atributo);
		} catch (Exception e) {
		}

		if (atributo.equals("Dono da carona")) {
			encontro = solicitacao.getPontoDeEncontro(1);
			return new UsuarioBusiness().getAtributoUsuario(
					encontro.getIdSessao(), "nome");
		}

		if (atributo.equals("Dono da solicitacao")) {
			encontro = solicitacao.getPontoDeEncontro(0);
			return new UsuarioBusiness().getAtributoUsuario(
					encontro.getIdSessao(), "nome");
		}

		if (atributo.equals("Ponto de Encontro")) {
			encontro = solicitacao.getPontoDeEncontro(2);
			// retorna apenas o ponto de encontro marcado
			return encontro.getPontos();
		}

		return "";
	}*/

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		
		try {
			new SolicitacaoVagasBusiness().aceitarSolicitacao(idSessao, idSolicitacao);
			
		} catch (Exception e) {
			throw new CaronaException("Solicitação inexistente");
		}
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws Exception {
		try {
			new SolicitacaoVagasDaoImp().remove(new SolicitacaoVagasDaoImp().getSolicitacaoVagas(idSolicitacao));
			
		} catch (Exception e) {
			throw new CaronaException("Solicitação inválida");
		}
	}

	public String getPontosSugeridos(String idSessao, String idCarona) {

		String ids = "[";
		boolean flag = true;// indica se a quantidade de ids é 0
		PontoDeEncontroDomain pontoEncontro;
		for (SolicitacaoPontoDeEncontroDomain solicitacao : getSolicitacoes()) {
			pontoEncontro = solicitacao.getPontoDeEncontro(0);
			if (pontoDeEncontro.getIdCarona().equals(idCarona)
					&& CaronaBusiness.ehMotorista(idSessao, idCarona)) {

				if (!flag) {
					ids += ",";
				}
				ids += pontoEncontro.getPontos();
				flag = false;

			}
		}
		return ids + "]";
	}

	public String getPontosEncontro(String idSessao, String idCarona) {

		String ids = "[";
		boolean flag = true;// indica se a quantidade de ids � 0
		PontoDeEncontroDomain pontoEncontro;
		for (SolicitacaoPontoDeEncontroDomain solicitacao : getSolicitacoes()) {
			try {// N�o identifiquei o erro que estava ocorrendo quando o
					// atributo pontoDeEncontro era null e tentava pegar
					// o valor dele e passar para a nova vari�vel
				pontoEncontro = solicitacao.getPontoDeEncontro(2);// Pontos de
																	// encontros
																	// confirmados
			} catch (Exception e) {
				pontoEncontro = null;
			}

			if (pontoEncontro != null && pontoEncontro.getPontos()!=null
					&& pontoDeEncontro.getIdCarona().equals(idCarona)
					&& CaronaBusiness.ehMotorista(idSessao, idCarona)) {

				if (!flag) {
					ids += ",";
				}
				ids += pontoEncontro.getPontos();
				flag = false;

			}
		}
		return ids + "]";
	}
}