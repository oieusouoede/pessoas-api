package com.edgar.pessoasapi.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.domain.service.IEnderecoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {
	
	private @Autowired IEnderecoService service;
	
	@GetMapping("/endereco={id}")
	public ResponseEntity<EnderecoDTO> enderecoPorId (@PathVariable Long id){
		log.info("Requisição /enderecos/endereco="+id);
		return service.enderecoPorId(id);
	}
	
	@GetMapping("/pessoa={id}")
	public ResponseEntity<List<EnderecoDTO>> enderecoPorPessoa (@PathVariable Long id){
		log.info("Requisição /enderecos/pessoa="+id);
		return service.enderecosPorPessoa(id);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<EnderecoDTO> criarEndereco (@RequestBody EnderecoDTO endereco){
		log.info("Requisição /enderecos/novo");
		return service.criarEndereco(endereco);
	}

	@PostMapping("/editar")
	public ResponseEntity<EnderecoDTO> editarEndereco (@RequestBody EnderecoDTO endereco){
		log.info("Requisição /enderecos/editar");
		return service.editarEndereco(endereco);
	}
	
	@DeleteMapping("/apagar={id}")
	public ResponseEntity<String> deletarEndereco (@PathVariable Long id){
		log.info("Requisição /enderecos/apagar="+id);
		return service.deletarEndereco(id);
	}
 	
}
