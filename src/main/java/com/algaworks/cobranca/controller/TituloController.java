package com.algaworks.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@PostMapping
	public String salvar(Titulo titulo) {
		System.out.println(">>> Salvando " + titulo.getDescricao());
		
		tituloRepository.save(titulo);
		
		return "CadastroTitulo";
	}

}
