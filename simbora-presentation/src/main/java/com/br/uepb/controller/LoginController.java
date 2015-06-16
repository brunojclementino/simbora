package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.SessaoException;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

@Controller
@Scope("request")
public class LoginController {

		private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		private SessaoBusiness sessaoBusiness = new SessaoBusiness();
		
		@RequestMapping(value = "/home/login.html", method = RequestMethod.GET)
	    public ModelAndView showCadastroUsuario(HttpServletRequest request){
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("login");		
			modelAndView.addObject("usuarioDomain", new UsuarioDomain());
			modelAndView.addObject("sessaoDomain", new SessaoDomain());
			
			return modelAndView;
	    }
		
		@RequestMapping(value = "/home/login.html", method = RequestMethod.POST)
		public ModelAndView validarSenha(@ModelAttribute("login") @Valid UsuarioDomain usuarioDomain, SessaoDomain sessaoDomain, 
				BindingResult bindingResult, HttpServletRequest request, ModelMap modelo) throws Exception {
			

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("paginaprincipal");
			
			if(bindingResult.hasErrors()){
				modelAndView.setViewName("/login");
				return modelAndView;
			}
						
			modelo.addAttribute("login", usuarioDomain.getLogin());
			modelo.addAttribute("senha", usuarioDomain.getSenha());
			
			usuarioBusiness=new UsuarioBusiness();
			sessaoBusiness = new SessaoBusiness();
			
			try {
				
				sessaoBusiness.abrirSessao(usuarioDomain.getLogin(), usuarioDomain.getSenha());
			} catch (SessaoException e) {
				
				modelAndView.setViewName("forward:/home/login.html");
				return modelAndView;
			}
						
			return modelAndView;
		}
}
