package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class VerUsuarioController {

	UsuarioBusiness usuarioBusiness;
	@RequestMapping(value = "/home/verUsuario.html", method = RequestMethod.GET)
	public ModelAndView mostarInformacoesDonoCarona(@RequestParam("login") String login, HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("verusuario");
			if(login.equals(request.getSession().getAttribute("sessao"))){
				return new ModelAndView("redirect:perfil.html");
			}
			usuarioBusiness = new UsuarioBusiness();
			UsuarioDomain usuario = usuarioBusiness.getUsuarioDomain(login);
			modelAndView.addObject("usuarioDomain", usuario);
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
}
