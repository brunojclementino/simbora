package com.br.uepb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;

@Controller
public class SolicitacoesRecebidasController {

	private CaronaBusiness caronaBusiness = new CaronaBusiness();
	private SessaoBusiness sessaoBusiness = new SessaoBusiness();
	private SolicitacaoVagasBusiness solicitacoesBusiness = new SolicitacaoVagasBusiness();
	
	@RequestMapping(value = "/home/solicitacoesRecebidas.html", method = RequestMethod.GET)
	public ModelAndView mostarCaronas(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solicitacoesrecebidas");
		modelAndView.addObject("caronaDomain", new CaronaDomain());
		modelAndView.addObject("solicitacaoDomain", new SolicitacaoVagasDomain());
		
		List<SolicitacaoVagasDomain> solicitacoes = solicitacoesBusiness.getSolicitacoesDoMotorista((String)request.getSession().getAttribute("sessao"));
		List<CaronaDomain> lstCaronas = caronaBusiness.getCaronas();
		request.getSession().setAttribute("lstCaronas", lstCaronas);
		request.getSession().setAttribute("slicitacoes", solicitacoes);
		
		return modelAndView;
	}

	@RequestMapping(value = "/home/solicitacoesRecebidas.html", method = RequestMethod.POST)
	public ModelAndView buscarCarona(
			@ModelAttribute("caronaDomain") @Valid CaronaDomain caronaDomain, SessaoDomain sessaoDomain,
			BindingResult bindingResult, HttpServletRequest request, HttpSession session,
			ModelMap modelo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("buscarCarona.html");
		
		return modelAndView;
	}
}