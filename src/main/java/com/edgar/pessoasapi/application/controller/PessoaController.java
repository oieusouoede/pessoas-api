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

import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.service.IPessoaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	
	private @Autowired IPessoaService service;
	
	@GetMapping("/pessoa={id}")
	public ResponseEntity<PessoaDTO> pessoaPorId (@PathVariable Long id) {
		log.info("Requisição /pessoas/pessoa="+id);
		return service.pessoaPorId(id);
	}
	
	@GetMapping("/todas-pessoas")
	public ResponseEntity<List<PessoaDTO>> todasPessoas (){
		log.info("Requisição /pessoas/todas-pessoas");
		return service.todasPessoas();
	}
	
	@PostMapping("/nova")
	public ResponseEntity<PessoaDTO> criarPessoa (@RequestBody PessoaDTO pessoa){
		log.info("Requisição /pessoas/nova");
		return service.criarPessoa(pessoa);
	}

	@PostMapping("/editar")
	public ResponseEntity<PessoaDTO> editarPessoa (@RequestBody PessoaDTO pessoa){
		log.info("Requisição /pessoas/editar");
		return service.editarPessoa(pessoa);
	}
	
	@DeleteMapping("/apagar={id}")
	public ResponseEntity<String> deletarPessoa (@PathVariable Long id){
		log.info("Requisição /pessoas/apagar="+id);
		return service.deletarPessoa(id);
	}

}
