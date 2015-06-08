package com.br.uepb.business;

import java.util.List;

import com.br.uepb.constants.CaronaException;
import com.br.uepb.dao.impl.PontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoPontoDeEncontroDaoImp;
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

	public static List<SolicitacaoPontoDeEncontroDomain> solicitacoes = new SolicitacaoPontoDeEncontroDaoImp()
			.list();
	private List<SolicitacaoVagasDomain> solicitacoesVagas = SolicitacaoVagasBusiness.solicitacoesVagas;

	private SolicitacaoPontoDeEncontroDomain solicitacaoEncontro;
	private PontoDeEncontroDomain pontoDeEncontro;

	/**
	 * Salva os pontos de encontro que foram aceitas e que ainda estão
	 * pendentes. Depois a List é limpada.
	 */
	public void encerrarSistema() {
		for (SolicitacaoPontoDeEncontroDomain solicitacaoPontoDeEncontro : solicitacoes) {
			try {
				PontoDeEncontroDaoImp pontoDeEncontroDaoImp = new PontoDeEncontroDaoImp();
				if (solicitacaoPontoDeEncontro.getPontoDeEncontro(0) != null)
					pontoDeEncontroDaoImp.save(solicitacaoPontoDeEncontro
							.getPontoDeEncontro(0));
				if (solicitacaoPontoDeEncontro.getPontoDeEncontro(1) != null)
					pontoDeEncontroDaoImp.save(solicitacaoPontoDeEncontro
							.getPontoDeEncontro(1));
				if (solicitacaoPontoDeEncontro.getPontoDeEncontro(2) != null)
					pontoDeEncontroDaoImp.save(solicitacaoPontoDeEncontro
							.getPontoDeEncontro(2));
				SolicitacaoPontoDeEncontroDaoImp solicitacaoPontoDeEncontroDaoImp = new SolicitacaoPontoDeEncontroDaoImp();
				solicitacaoPontoDeEncontroDaoImp
						.save(solicitacaoPontoDeEncontro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		solicitacoes.clear();
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

		solicitacoes.add(solicitacaoEncontro);
		solicitacaoEncontro.setIdSugestao(solicitacoes
				.indexOf(solicitacaoEncontro) + "PE");
		// Para gerar o id da solicitação. PE identifica que é Ponto de Encontro
		pontoDeEncontro.setIdPonto(solicitacaoEncontro.getIdSugestao() + "0");
		return solicitacaoEncontro.getIdSugestao();
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

		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {
			solicitacaoEncontro = solicitacao;
			if (solicitacao.getIdSugestao().equals(idSugestao)) {
				// 1 indica que esse ponto é resposta do motorista
				solicitacaoEncontro.setPontoDeEncontro(pontoDeEncontro, 1);
				pontoDeEncontro.setIdPonto(solicitacaoEncontro.getIdSugestao()
						+ "1");
			}
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
		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {
			encontro = solicitacao.getPontoDeEncontro(0);
			if (encontro.getIdSessao().equals(idSessao)
					&& encontro.getIdCarona().equals(idCarona)) {
				// 2 indica que esse ponto � a confirma��o do encontro pelo
				// caroneiro
				solicitacao.setPontoDeEncontro(pontoDeEncontro, 2);
				pontoDeEncontro.setIdPonto(solicitacao.getIdSugestao() + "2");
				return solicitacao.getIdSugestao();
			}
		}

		return "";
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws CaronaException {
		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {
			if (solicitacao.getIdSugestao().equals(idSolicitacao)) {
				// return new
				// CaronaController().getAtributoCarona(encontro.getIdCarona(),
				// atributo);
				return getAtributo(solicitacao, atributo);
			}
		}
		return new SolicitacaoVagasBusiness().getAtributo(idSolicitacao,
				atributo);
	}

	private String getAtributo(SolicitacaoPontoDeEncontroDomain solicitacao,
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
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {

		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {

			if (solicitacao.getIdSugestao().equals(idSolicitacao)
					&& solicitacao.isEmAndamento()) {
				PontoDeEncontroDomain encontro = solicitacao
						.getPontoDeEncontro(2);
				new CaronaBusiness().reduzQtdVagas(encontro.getIdCarona());
				solicitacao.setEmAndamento(false);
				return;
			}
		}
		throw new Exception("Solicitação inexistente");
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws Exception {
		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {

			if (solicitacao.getIdSugestao().equals(idSolicitacao)
					&& !solicitacao.isEmAndamento()) {
				PontoDeEncontroDomain encontro = solicitacao
						.getPontoDeEncontro(2);
				new CaronaBusiness().aumentaQtdVagas(encontro.getIdCarona());
				solicitacao.setEmAndamento(true);
				return;
			}
		}
		throw new Exception("Solicitação inexistente");
	}

	public String getPontosSugeridos(String idSessao, String idCarona) {

		String ids = "[";
		boolean flag = true;// indica se a quantidade de ids � 0
		PontoDeEncontroDomain pontoEncontro;
		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {
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
		System.out.println(solicitacoes.size());
		return ids + "]";
	}

	public String getPontosEncontro(String idSessao, String idCarona) {

		String ids = "[";
		boolean flag = true;// indica se a quantidade de ids � 0
		PontoDeEncontroDomain pontoEncontro;
		for (SolicitacaoPontoDeEncontroDomain solicitacao : solicitacoes) {
			try {// N�o identifiquei o erro que estava ocorrendo quando o
					// atributo pontoDeEncontro era null e tentava pegar
					// o valor dele e passar para a nova vari�vel
				pontoEncontro = solicitacao.getPontoDeEncontro(2);// Pontos de
																	// encontros
																	// confirmados
			} catch (Exception e) {
				pontoEncontro = null;
			}

			if (pontoEncontro != null
					&& pontoDeEncontro.getIdCarona().equals(idCarona)
					&& CaronaBusiness.ehMotorista(idSessao, idCarona)) {

				if (!flag) {
					ids += ",";
				}
				ids += pontoEncontro.getPontos();
				flag = false;

			}
		}
		System.out.println(solicitacoes.size());
		return ids + "]";
	}
}