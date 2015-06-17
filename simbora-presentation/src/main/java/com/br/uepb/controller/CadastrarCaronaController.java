package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.br.uepb.constants.CaronaException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;

@Controller
public class CadastrarCaronaController {

	private CaronaBusiness caronaBusiness = new CaronaBusiness();
	private SessaoBusiness sessaoBusiness = new SessaoBusiness();
	
	@RequestMapping(value = "/home/cadastrocarona.html", method = RequestMethod.GET)
	public ModelAndView showCadastroCarona(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cadastrocarona");
		modelAndView.addObject("caronaDomain", new CaronaDomain());
		modelAndView.addObject("sessaoDomain", new SessaoDomain());
		
		return modelAndView;
	}

	@RequestMapping(value = "/home/cadastrocarona.html", method = RequestMethod.POST)
	public ModelAndView addNovaCarona(
			@ModelAttribute("caronaDomain") @Valid CaronaDomain caronaDomain, SessaoDomain sessaoDomain,
			BindingResult bindingResult, HttpServletRequest request,
			ModelMap modelo) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cadastrocarona");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("caronaDomain", caronaDomain);
			modelAndView.addObject("sessaoDomain", sessaoDomain);
			modelAndView.setViewName("/paginaPrincipal");
			return modelAndView;
		}
		modelo.addAttribute("idSessao", sessaoDomain.getIdSessao());
		modelo.addAttribute("id", caronaDomain.getId());
		modelo.addAttribute("origem", caronaDomain.getOrigem());
		modelo.addAttribute("destino", caronaDomain.getDestino());
		modelo.addAttribute("data", caronaDomain.getData());
		modelo.addAttribute("hora", caronaDomain.getHora());
		modelo.addAttribute("vagas", caronaDomain.getVagas());
		modelo.addAttribute("idUsuario", caronaDomain.getIdUsuario());
		modelo.addAttribute("ehPreferencial", caronaDomain.getEhPreferencial());
		
		
		caronaBusiness.cadastrarCarona(sessaoDomain.getIdSessao(), caronaDomain.getOrigem(),
				caronaDomain.getDestino(), caronaDomain.getData(), caronaDomain.getHora(), caronaDomain.getVagas());
		
		
		return modelAndView;
	}
}