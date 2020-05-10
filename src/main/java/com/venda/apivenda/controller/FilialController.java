package com.venda.apivenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.venda.apivenda.models.Filial;
import com.venda.apivenda.repository.FilialRepository;

@Controller
public class FilialController {

	@Autowired
	FilialRepository filialRepository;
	
	@RequestMapping(value = "/cadastrarFilial", method = RequestMethod.GET)
	public String form() {
		return "filial/formFilial";
	}
	
	@RequestMapping(value = "/cadastrarFilial", method = RequestMethod.POST)
	public String form(Filial filial) {
		filialRepository.save(filial);
		
		return "redirect:/cadastrarFilial";
	}
	
	@RequestMapping(value = "/filiais")
	public ModelAndView listaFiliais() {
		ModelAndView model = new ModelAndView("filial/listarFiliais");
		Iterable<Filial> filiais = filialRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		model.addObject("filiais", filiais);
		
		return model;
	}
}
