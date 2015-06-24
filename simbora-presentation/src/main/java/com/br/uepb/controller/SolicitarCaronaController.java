package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SolicitacaoVagasBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.CaronaException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoVagasDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class SolicitarCaronaController {

	UsuarioBusiness usuarioBusiness;
	CaronaBusiness caronaBusiness;
	SolicitacaoVagasBusiness solicitarVagasBusiness;
	
	@RequestMapping(value = "/home/solicitarVagaCarona.html", method = RequestMethod.GET)
	public ModelAndView mostarInformacoesDonoCarona(@RequestParam("idCarona") String idCarona, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solicitarVagaCarona");

		usuarioBusiness = new UsuarioBusiness();
		caronaBusiness = new CaronaBusiness();
		CaronaDomain carona = caronaBusiness.getCaronaDomain(idCarona);
		UsuarioDomain usuario = usuarioBusiness.getUsuarioDomain(carona.getIdUsuario());
		
		modelAndView.addObject("usuarioDomain", usuario);

		caronaBusiness = new CaronaBusiness();
		 
		modelAndView.addObject("caronaDomain", carona);	
		request.getSession().setAttribute("idCarona", idCarona);
		return modelAndView;
	}

	@RequestMapping(value = "/home/solicitarVagaCarona.html", method = RequestMethod.POST)
	public ModelAndView criarSocilitacao(
			@ModelAttribute("solicitacaoVagasDomain") @Valid SolicitacaoVagasDomain solicitarDomain,
			CaronaDomain caronaDomain, SessaoDomain sessaoDomain,
			BindingResult bindingResult, HttpServletRequest request,
			HttpSession session, ModelMap modelo) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		SolicitacaoVagasDomain solicitacaoVagasDomain = new SolicitacaoVagasDomain();

		usuarioBusiness = new UsuarioBusiness();
		String login = (String) request.getSession().getAttribute("sessao");
	
		solicitacaoVagasDomain.setIdCarona(caronaDomain.getIdUsuario());

		solicitarVagasBusiness = new SolicitacaoVagasBusiness();
		solicitarVagasBusiness.solicitarVaga(login, (String) request.getSession().getAttribute("idCarona"));
		session.removeAttribute("idCarona");
		modelAndView.setViewName("buscarCarona");

		return modelAndView;
	}
	
}