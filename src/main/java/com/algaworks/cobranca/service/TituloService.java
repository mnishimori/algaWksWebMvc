package com.algaworks.cobranca.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloRepository;

@Service
public class TituloService {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	@Transactional
	public void salvar(Titulo titulo) {
		try {
			tituloRepository.save(titulo);
			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	@Transactional
	public void excluir(Titulo titulo) {
		tituloRepository.delete(titulo);
	}
	
	@Transactional
	public String receber(Long codigo) {
		Titulo titulo = tituloRepository.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		tituloRepository.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

}
