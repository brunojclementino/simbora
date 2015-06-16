package com.br.uepb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CadastrarUsuarioController {

	
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@RequestMapping(value = "/home/cadastrousuario.html", method = RequestMethod.GET)
    public ModelAndView showCadastroUsuario(HttpServletRequest request){
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cadastrousuario");		
		modelAndView.addObject("usuarioDomain", new UsuarioDomain());
		
		
		return modelAndView;
    }
	
	@RequestMapping(value = "/home/cadastrousuario.html", method = RequestMethod.POST)
	public ModelAndView addNovoUsuario(@ModelAttribute("cadastrousuario") @Valid UsuarioDomain usuarioDomain, BindingResult bindingResult, HttpServletRequest request, ModelMap modelo) throws Exception {
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home/cadastrousuario.html");

		if(bindingResult.hasErrors()){
			modelAndView.addObject("usuarioDomain", usuarioDomain);			
			return modelAndView;
		}
		modelo.addAttribute("nome", usuarioDomain.getNome());
		modelo.addAttribute("login", usuarioDomain.getLogin());
		modelo.addAttribute("endereco", usuarioDomain.getEndereco());
		modelo.addAttribute("email", usuarioDomain.getEmail());
		modelo.addAttribute("senha", usuarioDomain.getSenha());
		usuarioBusiness=new UsuarioBusiness();
		usuarioBusiness.criarUsuario(usuarioDomain.getLogin(), usuarioDomain.getSenha(), usuarioDomain.getLogin(), usuarioDomain.getLogin(), usuarioDomain.getLogin()+"@email.com");
		
		return modelAndView;
	}

}