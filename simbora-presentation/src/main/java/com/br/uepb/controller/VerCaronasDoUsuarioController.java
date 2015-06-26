package com.br.uepb.controller;

import java.util.List;

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
public class VerCaronasDoUsuarioController {

	UsuarioBusiness usuarioBusiness;
	@RequestMapping(value = "/home/verCaronasDoUsuario.html", method = RequestMethod.GET)
	public ModelAndView mostarInformacoesDonoCarona(@RequestParam("login") String login, HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("vercaronasdousuario");
			usuarioBusiness = new UsuarioBusiness();
			UsuarioDomain usuario = usuarioBusiness.getUsuarioDomain(login);
			List<CaronaDomain> lstCaronas = new CaronaBusiness().getCaronasUsuario(login);
			request.getSession().setAttribute("lstCaronas", lstCaronas);
			modelAndView.addObject("usuarioDomain", usuario);
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
}
