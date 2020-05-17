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

import com.venda.apivenda.model.Cliente;
import com.venda.apivenda.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.GET) 
	public String form() {
		return "cliente/formCliente";
	}
	
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(Cliente cliente) {
		clienteRepository.save(cliente);
		
		return "redirect:/cadastrarCliente";
	}
	
	@RequestMapping(value = "/clientes")
	public ModelAndView listaClientes() {
		ModelAndView model = new ModelAndView("cliente/listarClientes");
		Iterable<Cliente> clientes = clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		
		model.addObject("clientes", clientes);
		
		return model;
	}
	
	@RequestMapping(value = "/editarCliente/{id}")
	public ModelAndView editarFilial(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("cliente/editarCliente");
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		model.addObject("cliente", cliente);
		
		return model;
	}
	
	@PostMapping("atualizarCliente")
	public String atualizaCliente(@ModelAttribute Cliente cliente) {
		clienteRepository.save(cliente);
		
		return "redirect:/clientes";
	}
	
	@RequestMapping(value = "/excluirCliente/{id}")
	public String excluirCliente(@PathVariable Long id) {
		clienteRepository.deleteById(id);
		
		return "redirect:/clientes";
	}
	
}
