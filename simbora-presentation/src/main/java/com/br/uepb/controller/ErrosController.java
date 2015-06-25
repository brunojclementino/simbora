package com.br.uepb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ErrosController {

	@RequestMapping(value = "/home/errosolicitandovaganasuacarona.html", method = RequestMethod.GET)
	public ModelAndView showPerfil(HttpServletRequest request) {
		if(request.getSession().getAttribute("sessao")!=null){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("errosolicitandovaganasuacarona");
			return modelAndView;
		}
		return new ModelAndView("redirect:login.html");
	}
}
