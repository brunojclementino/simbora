package com.br.uepb.controller;

import java.util.ArrayList;
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

import com.br.uepb.business.PerfilBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.PerfilException;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.PerfilDomain;
import com.br.uepb.domain.ResumoPerfil;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
public class ReviewUsuarioController {
	
	PerfilBusiness perfilBusiness = new PerfilBusiness(); 
	UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@RequestMapping(value = "/home/reviewusuario.html", method = RequestMethod.GET)
	public ModelAndView mostarCaronas(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("sessao")!=null){
			List<ResumoPerfil> resumoPerfil = new ArrayList<ResumoPerfil>(); 
			List<UsuarioDomain> user = usuarioBusiness.getUsuarios();
			ResumoPerfil resumo;
			for (UsuarioDomain usuarioDomain : user) {
				try {
					 resumo = new ResumoPerfil();
						resumo.setNomeUsuario(usuarioDomain.getLogin());
						resumo.setCaronaSeguraTranquila(perfilBusiness.getAtributoPerfil(usuarioDomain.getLogin(), "caronas seguras e tranquilas"));
						resumo.setCaronaNaoFuncionaram(perfilBusiness.getAtributoPerfil(usuarioDomain.getLogin(), "caronas que não funcionaram"));
						resumo.setUsuarioFaltou(perfilBusiness.getAtributoPerfil(usuarioDomain.getLogin(), "faltas em vagas de caronas"));
						resumo.setCaronaFuncionou(perfilBusiness.getAtributoPerfil(usuarioDomain.getLogin(), "presenças em vagas de caronas"));
					resumoPerfil.add(resumo);
				} catch (PerfilException e) {
					
				}
			}
			
			request.getSession().setAttribute("listPerfil", resumoPerfil);
			modelAndView.setViewName("reviewusuario");
			
			return modelAndView;
		}
					
		return modelAndView;
	}

	@RequestMapping(value = "/home/reviewusuario.html", method = RequestMethod.POST)
	public ModelAndView buscarCarona(
			@ModelAttribute("usuarioDomain") @Valid UsuarioDomain usuarioDomain, SessaoDomain sessaoDomain,
			BindingResult bindingResult, HttpServletRequest request, HttpSession session,
			ModelMap modelo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reviewusuario.html");
		
		return modelAndView;
	}
}