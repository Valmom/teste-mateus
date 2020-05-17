package com.venda.apivenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.venda.apivenda.model.Filial;
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
	
	@RequestMapping(value = "/editarFilial/{id}")
	public ModelAndView editarFilial(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("filial/editarFilial");
		Filial filial = filialRepository.findById(id).orElse(null);

		model.addObject("filial", filial);
		
		return model;
		
	}
	
    @PostMapping("atualizarFilial")
    public String atualizarFilial(@ModelAttribute Filial filial) {
        filialRepository.save(filial);

        return "redirect:/filiais";
    }
	
	@RequestMapping("/excluirFilial/{id}")
	public String excluirFilial(@PathVariable("id") Long id) {
		filialRepository.deleteById(id);
		
		return "redirect:/filiais";
	}
	
}
