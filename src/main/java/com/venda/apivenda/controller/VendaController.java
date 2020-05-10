package com.venda.apivenda.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VendaController {
	private String appMode;
	
	@Autowired
	public VendaController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("datetime", new Date());
		model.addAttribute("usuario", "Valter Monteiro de Melo");
		
		model.addAttribute("mode", appMode);
		
		return "index";
	}
}
