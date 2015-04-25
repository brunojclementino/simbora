package com.br.uepb.business;


import java.util.ArrayList; 
import java.util.List;

import org.apache.log4j.Logger;

import com.br.uepb.constants.PerfilException;
import com.br.uepb.constants.UsuarioException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

/**
 * Esta classe gerencia o perfil do usuário. Responsável por transmitir as informações relacionadas
 * as atividades dos usuários.
 * Define os métodos de acesso público: visualizarPerfil e getAtributoPerfil
 * 
 * @author Lucas Miranda e Bruno Clementino
 *
 */
public class PerfilBusiness {
	
	public final static Logger logger = Logger.getLogger(PerfilBusiness.class);
	
	public static List<String> caronasSegurasTranquilas = new ArrayList<>();
	public static List<String> caronasNaoFuncionaram = new ArrayList<>();
	public static List<String> faltaramNasVagas = new ArrayList<>();
	public static List<String> presenteNasVagas = new ArrayList<>();
	
	public String visualizarPerfil(String idSessao, String login) throws PerfilException{
		
		if(login==null || login.trim().isEmpty()){
			throw new PerfilException("Login inv�lido"); 
		}
		
		if(idSessao==null || idSessao.trim().isEmpty()){
			throw new PerfilException("Sess�o inv�lida");
		}
		
		for(UsuarioDomain usuario : new UsuarioBusiness().usuarios){
			if(usuario.getLogin().equals(login)){
				return usuario.getLogin();
			}
		}
		
		throw new PerfilException("Login inv�lido");
		
	}
	
	public String getAtributoPerfil(String login, String atributo) throws PerfilException{
		if(atributo == null || atributo.trim().isEmpty()){ 
			throw new PerfilException("Atributo inválido");
		}
		if(login==null || login.trim().isEmpty()){
			throw new PerfilException("Login inválido");
		}
	
		for(UsuarioDomain usuario : new UsuarioBusiness().usuarios) {
			if(usuario.getLogin().equals(login)){
				return getAtributo(login, atributo); 
			}  
		}
		throw new UsuarioException("Login inválido");
		
	}

	private String getAtributo(String login, String atributo) throws PerfilException {
		
		if(atributo.equals("historico de caronas")){
			String caron = "[";
			for(CaronaDomain carona : CaronaBusiness.getCaronas()){
				if(carona.getIdSessao().equals(login)){
					caron+=carona.getIdCarona();
				}
			} 
			return caron+"]";
		}
		
		if(atributo.equals("historico de vagas em caronas")){
			String caron = "[";
			for(SolicitacaoVagasDomain solicitacaoVagas : SolicitacaoVagasBusiness.solicitacoesVagas){
				if(solicitacaoVagas.getIdSessao().equals(login)){
					caron+=solicitacaoVagas.getIdCarona();
				}
			}
			return caron+"]";
		}
		
		if(atributo.equals("caronas seguras e tranquilas")){
			int caron = 0;
			for(String idCarona : caronasSegurasTranquilas){
				if(CaronaBusiness.ehMotorista(login, idCarona)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("caronas que n�o funcionaram")){
			int caron = 0;
			for(String idCarona : caronasNaoFuncionaram){
				if(CaronaBusiness.ehMotorista(login, idCarona)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("faltas em vagas de caronas")){
			int caron = 0;
			for(String idUsuario : faltaramNasVagas){
				if(idUsuario.equals(login) && SolicitacaoVagasBusiness.ehCaroneiro(login)){
					caron++;
				}
			}
			return caron+"";
		}
		
		if(atributo.equals("presen�as em vagas de caronas")){
			int caron = 0;
			for(String idUsuario : presenteNasVagas){
				if(idUsuario.equals(login) && SolicitacaoVagasBusiness.ehCaroneiro(login)){
					caron++;
				}
			}
			return caron+"";
		}
		
		return new UsuarioBusiness().getAtributoUsuario(login, atributo);
	}

}
