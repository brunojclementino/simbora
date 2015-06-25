package com.br.uepb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class RequisicaoDeVagaController {

	@RequestMapping(value = "/home/requisicaoDeVagaNaCarona.html", method = RequestMethod.GET)
	public ModelAndView showRequisicao(@RequestParam("idSolicitacao") String idSolicitacao, HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			SolicitacaoVagasBusiness solicitacaoVagasBusiness = new SolicitacaoVagasBusiness();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("requisicaodevaganacarona");
			
			SolicitacaoVagasDomain solicitacao = solicitacaoVagasBusiness.getSolicitacaoVagasDomain(idSolicitacao);
			
			UsuarioDomain usuario = new UsuarioBusiness().getUsuarioDomain(solicitacao.getIdSessao());
			CaronaDomain carona = new CaronaBusiness().getCaronaDomain(solicitacao.getIdCarona());
			modelAndView.addObject("usuarioDomain", usuario);
			modelAndView.addObject("caronaDomain", carona);
			modelAndView.addObject("solicitacaoDomain", solicitacao);
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
	
	@RequestMapping(value = "/home/aprovar.html", method = RequestMethod.GET)
	public ModelAndView aprovarRequisicao(@RequestParam("idSolicitacao") String idSolicitacao, HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			SolicitacaoVagasBusiness solicitacaoVagasBusiness = new SolicitacaoVagasBusiness();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("aprovar");
			String login = (String) request.getSession().getAttribute("sessao");
			try {
				solicitacaoVagasBusiness.aceitarSolicitacao(login, idSolicitacao);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
	
	@RequestMapping(value = "/home/rejeitar.html", method = RequestMethod.GET)
	public ModelAndView rejeitarRequisicao(@RequestParam("idSolicitacao") String idSolicitacao, HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			SolicitacaoVagasBusiness solicitacaoVagasBusiness = new SolicitacaoVagasBusiness();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("rejeitar");
			String login = (String) request.getSession().getAttribute("sessao");
			try {
				solicitacaoVagasBusiness.rejeitarSolicitacao(login, idSolicitacao);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
}
