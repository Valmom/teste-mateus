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

import com.venda.apivenda.model.Usuario;
import com.venda.apivenda.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public String form(Usuario usuario) {
		usuarioRepository.save(usuario);
		
		return "redirect:/cadastrarUsuario";
	}
	
	@RequestMapping(value = "/usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView model = new ModelAndView("usuario/listarUsuarios");
		Iterable<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "login"));
		model.addObject("usuarios", usuarios);
		
		return model;
	}
	
	@RequestMapping(value = "/editarUsuario/{id}")
	public ModelAndView editarUsuario(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("usuario/editarUsuario");
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		
		model.addObject("usuario", usuario);
		
		return model;		
	}
	
	@PostMapping("atualizarUsuario")
	public String atualizarUsuario(@ModelAttribute Usuario usuario) {
		usuarioRepository.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value = "/excluirUsuario/{id}")
	public String excluirUsuario(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		
		return "redirect:/usuarios";
	}
}
