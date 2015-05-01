package com.br.uepb.business;


import java.util.List;

import com.br.uepb.dao.impl.SolicitacaoVagasDaoImp;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;

/**
 * O responsável por gerenciar as solicitações de vagas nas caronas pelos usuúrios
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class SolicitacaoVagasBusiness {

	SolicitacaoVagasDomain solicitacaoVagas;
	public static List<SolicitacaoVagasDomain> solicitacoesVagas = new SolicitacaoVagasDaoImp().list();
	
	public void zerarSistema(){
		for (SolicitacaoVagasDomain solicitacaoVagas : solicitacoesVagas) {
			try {
				new SolicitacaoVagasDaoImp().save(solicitacaoVagas);
			} catch (Exception e) {
			}
		}
		solicitacoesVagas.clear();
	}
	
	public String solicitarVaga(String idSessao, String idCarona){
		
		solicitacaoVagas=new SolicitacaoVagasDomain();
		
		solicitacaoVagas.setIdSessao(idSessao);
		solicitacaoVagas.setIdCarona(idCarona);
		
		solicitacoesVagas.add(solicitacaoVagas);
		
		solicitacaoVagas.setIdSolicitacao(solicitacoesVagas.indexOf(solicitacaoVagas) +"V");//id da solicita��o de vaga
		
		return solicitacaoVagas.getIdSolicitacao();
	}
	
	public void aceitarSolicitacao(String idSessao, String idSolicitacao){
		
		for(SolicitacaoVagasDomain solicitacao : solicitacoesVagas){
			
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				solicitacao.setStatus("Aceita");
				new CaronaBusiness().reduzQtdVagas(solicitacao.getIdCarona());
				return;
			}			
		}		
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		for(SolicitacaoVagasDomain solicitacao : solicitacoesVagas){
			
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				if(solicitacao.getStatus().equals("Pendente")){

					solicitacao.setStatus("Recusada");
					return;
				}else{
					throw new Exception("Solicitação inexistente");
				}				
			}
			
		}
		
	}
	
	public String getAtributo(String idSolicitacao, String atributo) {
		for(SolicitacaoVagasDomain solicitacao : solicitacoesVagas){
			if(solicitacao.getIdSolicitacao().equals(idSolicitacao)){
				try {
					return new CaronaBusiness().getAtributoCarona(solicitacao.getIdCarona(), atributo);
				} catch (Exception e) {
				}	
				if (atributo.equals("Dono da carona")) {
					for(CaronaDomain carona: CaronaBusiness.getCaronas()){
						if(carona.getIdCarona().equals(solicitacao.getIdCarona())){
							return new UsuarioBusiness().getAtributoUsuario(carona.getIdSessao(), "nome");
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
	
	public static boolean ehCaroneiro(String login){
		
		return false;
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		
		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for(SolicitacaoVagasDomain solicitacao : solicitacoesVagas){

			if(solicitacao.getIdCarona().equals(idCarona) && 
					CaronaBusiness.ehMotorista(idSessao, idCarona) &&
					solicitacao.getStatus().equals("Aceita")){
				
				if (!flag) {
					ids += ",";
				}
				ids += solicitacao.getIdSolicitacao();
				flag = false;
				
			}			
		}
		return ids + "}";
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona) {

		String ids = "{";
		boolean flag = true;// indica se a quantidade de ids � 0
		for(SolicitacaoVagasDomain solicitacao : solicitacoesVagas){

			if(solicitacao.getIdCarona().equals(idCarona) && 
					CaronaBusiness.ehMotorista(idSessao, idCarona) &&
					solicitacao.getStatus().equals("Pendente")){

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
