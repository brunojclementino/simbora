package com.br.uepb.accept;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoPontoDeEncontroBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.dao.impl.CaronaDaoImp;
import com.br.uepb.dao.impl.PontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoPontoDeEncontroDaoImp;
import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.dao.impl.UsuarioDaoImp;
import com.br.uepb.util.HibernateUtil;

/**
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SimboraEasyAccept {

	UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	CaronaBusiness caronaBusiness = new CaronaBusiness();
	SessaoBusiness sessaoBusiness = new SessaoBusiness();
	//PontoDeEncontroController pontoDeEncontroController = new PontoDeEncontroController();
	SolicitacaoPontoDeEncontroBusiness solicitacaoEncontroBusiness = new SolicitacaoPontoDeEncontroBusiness();
	SolicitacaoVagasBusiness solicitacaoVagasBusiness = new SolicitacaoVagasBusiness();
	PerfilBusiness perfilBusiness = new PerfilBusiness();
	
	public void zerarSistema() {
		/*usuarioController.zerarSistema();
		caronaController.zerarSistema();
		solicitacaoEncontroController.zerarSistema();
		solicitacaoVagasController.zerarSistema();*/
		//pontoDeEncontroController.zerarSistema();
		usuarioBusiness.usuarios.clear();
		caronaBusiness.caronas.clear();
		solicitacaoVagasBusiness.solicitacoesVagas.clear();
		solicitacaoEncontroBusiness.solicitacoes.clear();
		new UsuarioDaoImp().excluirTudo();
		new CaronaDaoImp().excluirTudo();
		new SolicitacaoPontoDeEncontroDaoImp().excluirTudo();
		new SolicitacaoVagasDaoImp().excluirTudo();
		new PontoDeEncontroDaoImp().excluirTudo();
		
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws UsuarioException{
		usuarioBusiness.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws SessaoException{
		return sessaoBusiness.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo) throws UsuarioException{
		return usuarioBusiness.getAtributoUsuario(login, atributo);
	}
	
	public void encerrarSessao(String login){
		sessaoBusiness.encerrarSessao(login);
	}
	
	public void encerrarSistema(){
		usuarioBusiness.encerrarSistema();
		caronaBusiness.encerrarSistema();
		solicitacaoEncontroBusiness.encerrarSistema();
		solicitacaoVagasBusiness.encerrarSistema();
		HibernateUtil.closedSession();
	}
	
	
	//Metodos US02
	
	public String localizarCarona(String idSessao, String origem, String destino) throws Exception {
		return caronaBusiness.localizarCarona(idSessao, origem, destino);
	}
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String qtdDeVagas) throws Exception {
		return caronaBusiness.cadastrarCarona(idSessao, origem, destino, data, hora, qtdDeVagas);
	}
	
	public String getAtributoCarona(String idCarona, String atributo) throws CaronaException{
		return caronaBusiness.getAtributoCarona(idCarona, atributo);
	}
	
	public String getTrajeto(String idCarona) throws CaronaException{
		return caronaBusiness.getTrajeto(idCarona);
	}
	
	public String getCarona(String idCarona) throws CaronaException{
		return caronaBusiness.getCarona(idCarona);
	}
	
	//Metodos US04
	
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws Exception{
		return solicitacaoEncontroBusiness.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		solicitacaoEncontroBusiness.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	
	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {
		return solicitacaoEncontroBusiness.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto) {
		return solicitacaoEncontroBusiness.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws CaronaException {
		return solicitacaoEncontroBusiness.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao) throws Exception{
		solicitacaoEncontroBusiness.desistirRequisicao(idSessao, idCarona, idSolicitacao);
	}
	
	//Metodos US05
	public String solicitarVaga(String idSessao, String idCarona) throws Exception{
		return solicitacaoVagasBusiness.solicitarVaga(idSessao, idCarona);
	}
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		solicitacaoVagasBusiness.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		solicitacaoVagasBusiness.rejeitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public String visualizarPerfil(String idSessao, String login) throws PerfilException{
		return perfilBusiness.visualizarPerfil(idSessao, login);
	}
	
	public String getAtributoPerfil(String login, String atributo) throws PerfilException{
		return perfilBusiness.getAtributoPerfil(login, atributo);
	}
	
	public String getCaronaUsuario(String idSessao, String indexCarona){

		return caronaBusiness.getCaronaUsuario(idSessao, indexCarona);
	}

	public void reiniciarSistema() {
		SessaoBusiness.setUsuarios(new UsuarioDaoImp().list());
		UsuarioBusiness.usuarios = SessaoBusiness.getUsuarios();
		CaronaBusiness.caronas = new CaronaDaoImp().list();
		SolicitacaoVagasBusiness.solicitacoesVagas = new SolicitacaoVagasDaoImp().list();
		SolicitacaoPontoDeEncontroBusiness.solicitacoes = new SolicitacaoPontoDeEncontroDaoImp().list();
		
	}

	public String getTodasCaronasUsuario(String idSessao) {
		
		return caronaBusiness.getTodasCaronasUsuario(idSessao);
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		
		return solicitacaoVagasBusiness.getSolicitacoesConfirmadas(idSessao, idCarona);
	}

	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		
		return solicitacaoVagasBusiness.getSolicitacoesPendentes(idSessao, idCarona);
	}

	public String getPontosSugeridos(String idSessao, String idCarona) {
		
		return solicitacaoEncontroBusiness.getPontosSugeridos(idSessao, idCarona);
	}

	public String getPontosEncontro(String idSessao, String idCarona) {
		
		return solicitacaoEncontroBusiness.getPontosEncontro(idSessao, idCarona);
	}
	
}
