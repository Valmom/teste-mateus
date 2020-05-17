package com.venda.apivenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.venda.apivenda.model.Filial;
import com.venda.apivenda.model.Produto;
import com.venda.apivenda.repository.FilialRepository;
import com.venda.apivenda.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	FilialRepository filialRepository;
	
	@RequestMapping(value = "/cadastrarProduto/{id}", method = RequestMethod.GET)
	public ModelAndView form(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("produto/formProduto");
		Filial filial = filialRepository.findById(id).orElse(null);
		model.addObject("filial", filial);
		
		return model;
	}
	
	@RequestMapping(value = "/cadastrarProduto/{id}", method = RequestMethod.POST)
	public String form(@PathVariable Long id, Produto produto) {
		Filial filial = filialRepository.findById(id).orElse(null);
		
		produto.setFilial(filial);
		
		produtoRepository.save(produto);
		
		return "redirect:/cadastrarProduto/{id}";
	}
	
	@RequestMapping(value = "/produtos/{id}")
	public ModelAndView listaProdutos(@PathVariable Long id) {
		Filial filial = filialRepository.findById(id).orElse(null);
		ModelAndView model = new ModelAndView("produto/listarProdutos");
		Iterable<Produto> produtos = produtoRepository.findByFilial(filial);
		
		model.addObject("produtos", produtos);
		model.addObject("filial", filial);
		
		return model;
	}
	
	@RequestMapping(value = "/editarProduto/{id}")
	public ModelAndView editarProduto(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("produto/editarProduto");
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		model.addObject("produto", produto);
		
		return model;
	}
	
	@PostMapping("atualizarProduto/{id}")
	public String atualizarUsuario(@PathVariable Long id, @ModelAttribute Produto produto) {
		
		produtoRepository.save(produto);
		
		return "redirect:/produtos/{id}";
	}
	
	@RequestMapping(value = "/excluirProduto/{id}")
	public String excluirProduto(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		
		return "redirect:/produtos";
	}
}
