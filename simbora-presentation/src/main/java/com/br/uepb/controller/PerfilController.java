package com.br.uepb.controller;

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

import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class PerfilController {

	UsuarioBusiness usuariobusiness;

	@RequestMapping(value = "/home/perfil.html", method = RequestMethod.GET)
	public ModelAndView showPerfil(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("perfil");
		modelAndView.addObject("usuarioDomain", new UsuarioDomain());
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
