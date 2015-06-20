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
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;

@Controller
public class BuscarCaronaController {

	private CaronaBusiness caronaBusiness = new CaronaBusiness();
	private SessaoBusiness sessaoBusiness = new SessaoBusiness();
	
	@RequestMapping(value = "/home/buscarCarona.html", method = RequestMethod.GET)
	public ModelAndView mostarCaronas(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("buscarCarona");
		modelAndView.addObject("caronaDomain", new CaronaDomain());
		modelAndView.addObject("sessaoDomain", new SessaoDomain());
		
		List<CaronaDomain> lstCaronas = caronaBusiness.getCaronas();
		request.getSession().setAttribute("lstCaronas", lstCaronas);
		
		return modelAndView;
	}

	@RequestMapping(value = "/home/buscarCarona.html", method = RequestMethod.POST)
	public ModelAndView buscarCarona(
			@ModelAttribute("caronaDomain") @Valid CaronaDomain caronaDomain, SessaoDomain sessaoDomain,
			BindingResult bindingResult, HttpServletRequest request, HttpSession session,
			ModelMap modelo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("buscarCarona.html");
		
		return modelAndView;
	}
}