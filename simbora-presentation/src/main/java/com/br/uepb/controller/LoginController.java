package com.br.uepb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.br.uepb.constants.SessaoException;
import com.br.uepb.domain.UsuarioDomain;

@Controller
@Scope("request")
public class LoginController {

	private SessaoBusiness sessaoBusiness = new SessaoBusiness();

	@RequestMapping(value = "/home/login.html", method = RequestMethod.GET)
	public ModelAndView showCadastroUsuario(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("usuarioDomain", new UsuarioDomain());
		modelAndView.addObject("mensagem", new String(""));

		return modelAndView;
	}

	@RequestMapping(value = "/home/login.html", method = RequestMethod.POST)
	public ModelAndView validarSenha(
			@ModelAttribute("usuarioDomain") @Valid UsuarioDomain usuarioDomain,
			String mensagem, BindingResult bindingResult,
			ModelMap modelo, HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("paginaprincipal");

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/login");
			return modelAndView;
		}

		modelo.addAttribute("login", usuarioDomain.getLogin());
		modelo.addAttribute("senha", usuarioDomain.getSenha());

		sessaoBusiness = new SessaoBusiness();

		String nome = (String)usuarioDomain.getNome();
		
		session.setAttribute("nome", nome);
		
		try {
			session.setAttribute("sessao", usuarioDomain.getLogin());
			String sessao = sessaoBusiness.abrirSessao(
					usuarioDomain.getLogin(), usuarioDomain.getSenha());
			session.setAttribute("sessao", sessao);
			session.setAttribute("usuario", (UsuarioDomain) usuarioDomain);
			
			
		} catch (SessaoException e) {
			modelAndView.setViewName("login");
			mensagem = "Login ou senha inválido";
			session.setAttribute("mensagem", (String) mensagem);
			return modelAndView;
		}

		return modelAndView;
	}
	

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView lognout(
			@ModelAttribute("sessaoDomain") @Valid SessaoBusiness sessaoDomain, HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		sessaoBusiness.encerrarSessao((String)session.getAttribute("sessao"));

		return modelAndView;
	}
}
