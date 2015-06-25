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
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class PaginaPrincipalController {

	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	SessaoBusiness sessaoBusiness = new SessaoBusiness();
	private CaronaBusiness caronaBusiness = new CaronaBusiness();

	@RequestMapping(value = "/home/paginaprincipal.html", method = RequestMethod.GET)
	public ModelAndView showPaginaPrincipal(HttpServletRequest request) {

		if(request.getSession().getAttribute("sessao")!=null){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("paginaprincipal");
			modelAndView.addObject("usuarioDomain", new UsuarioDomain());
			List<CaronaDomain> lstCaronas = caronaBusiness.getUltimasCaronas("caronaComum", 10);
			request.getSession().setAttribute("lstCaronas", lstCaronas);
			List<CaronaDomain> lstCaronasMunicipais = caronaBusiness.getUltimasCaronas("caronaMunicipal", 10);
			request.getSession().setAttribute("lstCaronasMunicipais", lstCaronasMunicipais);
			List<CaronaDomain> lstCaronasRelampagos = caronaBusiness.getUltimasCaronas("caronaRelampago", 10);
			request.getSession().setAttribute("lstCaronasRelampagos", lstCaronasRelampagos);
	
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	} 

	@RequestMapping(value = "/home/paginaprincipal.html", method = RequestMethod.POST)
	public ModelAndView addNovoUsuario(
			@ModelAttribute("usuarioDomain") @Valid UsuarioDomain usuarioDomain,
			BindingResult bindingResult, HttpServletRequest request,
			ModelMap modelo) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("paginaprincipal");

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("usuarioDomain", usuarioDomain);
			return modelAndView;
		}

		return modelAndView;
	}
}