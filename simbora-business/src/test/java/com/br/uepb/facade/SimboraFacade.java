package com.br.uepb.facade;

import com.br.uepb.accept.SimboraEasyAccept;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.constants.UsuarioException;

import easyaccept.EasyAccept;


public class SimboraFacade {
	
	SimboraEasyAccept simboraEasyAccept = new SimboraEasyAccept();
	
	public void zerarSistema(){
		simboraEasyAccept.zerarSistema();
	}

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws UsuarioException{
		simboraEasyAccept.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha) throws SessaoException {
		return simboraEasyAccept.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo) throws UsuarioException{
		return simboraEasyAccept.getAtributoUsuario(login, atributo);
	}
	
	public void encerrarSessao(String login){
		simboraEasyAccept.encerrarSessao(login);
	}
	
	public void encerrarSistema(){
		simboraEasyAccept.encerrarSistema();
	}
		
	public String localizarCarona(String idSessao, String origem , String destino) throws Exception{
		return simboraEasyAccept.localizarCarona(idSessao, origem, destino);
	} 
	
	public String cadastrarCarona(String idSessao, String origem, String destino, String data, String hora, String qtdDeVagas) throws Exception{
		return simboraEasyAccept.cadastrarCarona(idSessao, origem, destino, data, hora, qtdDeVagas);
	}
	
	public String getAtributoCarona(String idCarona, String atributo) throws CaronaException{
		return simboraEasyAccept.getAtributoCarona(idCarona, atributo);
	}
	
	public String getTrajeto(String idCarona) throws CaronaException{
		return simboraEasyAccept.getTrajeto(idCarona);
	}
	
	public String getCarona(String idCarona) throws CaronaException{
		return simboraEasyAccept.getCarona(idCarona);
	}
	
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws Exception{
		return simboraEasyAccept.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}
	
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws Exception { 
		simboraEasyAccept.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);		
	}
	
	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {
		return simboraEasyAccept.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto) {
		return simboraEasyAccept.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws CaronaException {
		return simboraEasyAccept.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao) throws Exception{
		simboraEasyAccept.desistirRequisicao(idSessao, idCarona, idSolicitacao);
	}
	
	public String solicitarVaga(String idSessao, String idCarona) throws Exception{
		return simboraEasyAccept.solicitarVaga(idSessao, idCarona);
	}
	
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		simboraEasyAccept.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		simboraEasyAccept.rejeitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public String visualizarPerfil(String idSessao, String login) throws PerfilException{
		return simboraEasyAccept.visualizarPerfil(idSessao, login);
	}
	
	public String getAtributoPerfil(String login, String atributo) throws PerfilException{
		return simboraEasyAccept.getAtributoPerfil(login, atributo);
	}
	
	/*US07*/
	public void reiniciarSistema(){
		simboraEasyAccept.reiniciarSistema();
	}
	
	public String getCaronaUsuario(String idSessao, String indexCarona){
		return simboraEasyAccept.getCaronaUsuario(idSessao, indexCarona);
	}
	
	public String getTodasCaronasUsuario(String idSessao){
		
		return simboraEasyAccept.getTodasCaronasUsuario(idSessao);
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona){
		
		return simboraEasyAccept.getSolicitacoesConfirmadas(idSessao, idCarona);
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona){

		return simboraEasyAccept.getSolicitacoesPendentes(idSessao, idCarona);
	}
	public String getPontosSugeridos(String idSessao, String idCarona){

		return simboraEasyAccept.getPontosSugeridos(idSessao, idCarona);
	}
	public String getPontosEncontro(String idSessao, String idCarona){

		return simboraEasyAccept.getPontosEncontro(idSessao, idCarona);
	}
	
	public void reviewVagaEmCarona(String idSessao, String idCorona,
			String loginCaroneiro, String review) throws PerfilException {
		simboraEasyAccept.reviewVagaEmCarona(idSessao, idCorona, loginCaroneiro, review);
	}
	public void reviewCarona(String idSessao, String idCaroneiro, String review) throws PerfilException{
		simboraEasyAccept.reviewCarona(idSessao, idCaroneiro, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) throws CaronaException {
		return simboraEasyAccept.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, data, hora, vagas);
	}
	public String localizarCaronaMunicipal(String idSessao, String cidade, 
			String origem, String destino) throws CaronaException{
		return simboraEasyAccept.localizarCaronaMunicipal(idSessao, cidade, origem, destino);
	}
	public String localizarCaronaMunicipal(String idSessao, String cidade) throws CaronaException {
		return simboraEasyAccept.localizarCaronaMunicipal(idSessao, cidade);
	}

	public static void main(String[] args) {
		args = new String[] {"com.br.uepb.facade.SimboraFacade", "scripts/US01.txt",
				"scripts/US02.txt", "scripts/US03.txt", "scripts/US04.txt", "scripts/US05.txt"
				, "scripts/US06.txt", "scripts/US07.txt", "scripts/US08.txt", "scripts/US09.txt"
				,  "scripts/US10.txt"};
		EasyAccept.main(args); 
	} 
}
