package com.edgar.pessoasapi.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;

public interface IEnderecoService {
	
	public ResponseEntity<EnderecoDTO> criarEndereco(EnderecoDTO enderecoNovo);
	public ResponseEntity<EnderecoDTO> editarEndereco(EnderecoDTO enderecoEditado);
	public ResponseEntity<String> deletarEndereco(Long idEndereco);
	public ResponseEntity<List<EnderecoDTO>> enderecosPorPessoa (Long idPessoa);
	public ResponseEntity<EnderecoDTO> enderecoPorId (Long idEndereco);
	
}
