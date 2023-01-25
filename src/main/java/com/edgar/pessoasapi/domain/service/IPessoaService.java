package com.edgar.pessoasapi.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.edgar.pessoasapi.application.dto.PessoaDTO;

public interface IPessoaService {
	
	public ResponseEntity<PessoaDTO> criarPessoa(PessoaDTO pessoaNova);
	public ResponseEntity<PessoaDTO> pessoaPorId(Long id);
	public ResponseEntity<PessoaDTO> editarPessoa(PessoaDTO pessoaModificada);
	public ResponseEntity<String> deletarPessoa(Long idPessoa);
	public ResponseEntity<List<PessoaDTO>> todasPessoas();
	

}
