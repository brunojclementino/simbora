/**
 * 
 */
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

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.domain.UsuarioDomain;

/**
 * @author Lucas Miranda e Bruno Clementino
 *
 */
@Controller
public class UsuarioController {

	private static final Log LOG = LogFactory.getLog(UsuarioController.class);

	@Autowired
	UsuarioBusiness usuario;

	@RequestMapping(value = "/home/home.html", method = RequestMethod.GET)
	public ModelAndView validarLogin(HttpServletRequest request) {
		
		LOG.debug("Iniciada a execucao do metodo: showWelcomeHtml");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("usuarioDomain", new UsuarioBusiness());
		modelAndView.addObject("userName", "Connected");
		
		
		request.getSession().setAttribute("lstUsers", new ArrayList<UsuarioBusiness>());
		
		LOG.debug("Finalizada a execucao do metodo: showWelcomeHtml");
		
		return modelAndView;
	}
}
