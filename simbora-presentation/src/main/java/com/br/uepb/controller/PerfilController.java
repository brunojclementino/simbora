package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.ReviewDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class PerfilController {

	UsuarioBusiness usuariobusiness;
	SolicitacaoVagasBusiness solicitacaoBusiness;
	PerfilBusiness perfilBusiness;
	CaronaBusiness caronaBusiness;
	
	@RequestMapping(value = "/home/perfil.html", method = RequestMethod.GET)
	public ModelAndView showPerfil(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("perfil");
		
		usuariobusiness = new UsuarioBusiness();
		UsuarioDomain usuario = new UsuarioDomain();
		String login = (String)request.getSession().getAttribute("sessao");
		usuario.setLogin(login);
		usuario.setEmail(usuariobusiness.getAtributoUsuario(login, "email"));
		usuario.setEndereco(usuariobusiness.getAtributoUsuario(login, "endereco"));
		usuario.setNome(usuariobusiness.getAtributoUsuario(login, "nome"));
		
		modelAndView.addObject("usuarioDomain", usuario);
		
		solicitacaoBusiness = new SolicitacaoVagasBusiness();
		modelAndView.addObject("solicitacaoDomain", solicitacaoBusiness.getSolicitacoes(login));
		
		perfilBusiness = new PerfilBusiness();
		caronaBusiness = new CaronaBusiness();
		try {
			List<CaronaDomain> caronasHistorico = perfilBusiness.getHistoricoCaronas(login);
			modelAndView.addObject("carHist", caronasHistorico);
			 
			List<ReviewDomain> caronasSegurasTranquilas = perfilBusiness.getSegurasTransquilas(login);
			modelAndView.addObject("carSegTranq", caronasSegurasTranquilas);
			
			List<ReviewDomain> caronasNaoFunc = perfilBusiness.getNaoFunc(login);
			modelAndView.addObject("carNaoFunc", caronasNaoFunc);
			
			List<SolicitacaoVagasDomain> solicitacoes = solicitacaoBusiness.getAllSolicitacoesPendentes(login);
			modelAndView.addObject("solicitacoes", solicitacoes);
		} catch (PerfilException e) {
			e.printStackTrace();
		} 
		
		return modelAndView;
	}

	@RequestMapping(value = "/home/perfil.html", method = RequestMethod.POST)
	public ModelAndView mostarPerfil(
			@Valid UsuarioDomain usuarioDomain, HttpServletRequest request,
			HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/perfil");

		usuariobusiness = new UsuarioBusiness();
		UsuarioDomain u = new UsuarioDomain();

		u.setNome(usuarioDomain.getNome());
		u.setEndereco(usuarioDomain.getEndereco());
		u.setEmail(usuarioDomain.getEmail());
		session.setAttribute("n", u);
		modelAndView.addObject("usuario", (UsuarioDomain) u);
		
		return modelAndView;
	}
}
