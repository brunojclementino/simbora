package com.br.uepb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.uepb.business.HomeBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDom;

@Controller
public class HomeController {

	private static final Log LOG = LogFactory.getLog(HomeController.class);

	@Autowired
	private HomeBusiness homeBusiness;
//	
//	@Autowired 
//	private SessaoBusiness sessaoSimbora;

	@RequestMapping(value = "/home/home.html", method = RequestMethod.GET)
	public ModelAndView showWelcomeHtml(HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: showWelcomeHtml");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("usuarioDomain", new UsuarioDom());
		modelAndView.addObject("userName", "Noca Connected");

		request.getSession().setAttribute("lstUsers",
				new ArrayList<UsuarioDom>());

		LOG.debug("Finalizada a execucao do metodo: showWelcomeHtml");

		return modelAndView;
	}

	@RequestMapping(value = "/home/home.html", method = RequestMethod.POST)
	public ModelAndView addNewUser(
			@ModelAttribute("usuarioDomain") @Valid UsuarioDom usuarioDomain,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug("Iniciada a execucao do metodo: addNewUser");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");

		if (bindingResult.hasErrors()) {
			usuarioDomain.setLstUsers((List<UsuarioDom>) request.getSession()
					.getAttribute("lstUsers"));
			modelAndView.addObject("usuarioDomain", usuarioDomain);
			modelAndView.addObject("userName", "Noca Connected");
			return modelAndView;
		}

		UsuarioDom ud = new UsuarioDom();

		ud.setCpf(usuarioDomain.getCpf());
		ud.setNome(usuarioDomain.getNome());
		ud.setLstUsers((List<UsuarioDom>) request.getSession().getAttribute(
				"lstUsers"));
		ud.getLstUsers().add(ud);

		request.getSession().setAttribute("lstUsers", ud.getLstUsers());

		modelAndView.addObject("usuarioDomain", ud);
		modelAndView.addObject("userName", "Noca Connected");

		LOG.debug("Finalizada a execucao do metodo: addNewUser");

		return modelAndView;
	}

	@RequestMapping(value = "/home/homeDeleteUserAjax.html", method = RequestMethod.GET)
	public ModelAndView removeUser(String userName, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<UsuarioDom> lstUsers = (List<UsuarioDom>) request.getSession()
				.getAttribute("lstUsers");
		int indexToRemove = -1;
		for (int i = 0; i < lstUsers.size(); i++) {
			if (lstUsers.get(i).getNome().equals(userName)) {
				indexToRemove = i;
				break;
			}
		}
		if (indexToRemove != -1) {
			lstUsers.remove(indexToRemove);
		}

		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		request.getSession().setAttribute("lstUsers", lstUsers);
		modelAndView.addObject("lstUsersAsParameter", lstUsers);
		modelAndView.setViewName("home/conteudoUsuario");
		return modelAndView;
	}

	@RequestMapping(value = "/home/homeDeleteUser.html", method = RequestMethod.GET)
	public ModelAndView removeUserWithAjaxWithoutWait(String userName,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<UsuarioDom> lstUsers = (List<UsuarioDom>) request.getSession()
				.getAttribute("lstUsers");
		int indexToRemove = -1;
		for (int i = 0; i < lstUsers.size(); i++) {
			if (lstUsers.get(i).getNome().equals(userName)) {
				indexToRemove = i;
				break;
			}
		}
		if (indexToRemove != -1) {
			lstUsers.remove(indexToRemove);
		}

		UsuarioDom ud = new UsuarioDom();
		ud.setLstUsers(lstUsers);

		request.getSession().setAttribute("lstUsers", lstUsers);
		modelAndView.setViewName("home");
		modelAndView.addObject("usuarioDomain", ud);
		return modelAndView;
	}
	
//	@RequestMapping(value = "/home/login.html", method = RequestMethod.GET)
//	public ModelAndView showLogin(HttpServletRequest request) {
//		
//		LOG.debug("Iniciada a execucao do método: showLogin");
//
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("login");
//		try {
//			modelAndView.addObject("sessaoDomain", new SessaoDomain());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		LOG.debug("Finalizada a execucao do metodo: showLogin");
//		
//		return modelAndView;
//	}
}
