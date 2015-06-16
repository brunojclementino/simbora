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

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class PaginaPrincipalController {

	
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	
	@RequestMapping(value = "/home/paginaprincipal.html", method = RequestMethod.GET)
    public ModelAndView showCadastroUsuario(HttpServletRequest request){
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("paginaprincipal");		
		modelAndView.addObject("usuarioDomain", new UsuarioDomain());
		
		
		return modelAndView;
    }
	
	@RequestMapping(value = "/home/paginaprincipal.html", method = RequestMethod.POST)
	public ModelAndView addNovoUsuario(@ModelAttribute("cadastrousuario") @Valid UsuarioDomain usuarioDomain, BindingResult bindingResult, HttpServletRequest request, ModelMap modelo) throws Exception {
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home/paginaprincipal.html");

		if(bindingResult.hasErrors()){
			modelAndView.addObject("usuarioDomain", usuarioDomain);			
			return modelAndView;
		}
		
		return modelAndView;
	}

}