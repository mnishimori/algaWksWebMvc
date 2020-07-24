package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@PostMapping
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		tituloRepository.save(titulo);
		
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = tituloRepository.findAll();
		
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		
		return mv;
	}
	
	@GetMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoTitulo) {
		Titulo titulo = tituloRepository.getOne(codigoTitulo);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

}
