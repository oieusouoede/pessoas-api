package com.edgar.pessoasapi.application.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
	
	 private Long id;
	 private String nome;
	 private LocalDate dtNascimento;
	 @JsonIgnoreProperties("pessoa")
	 private List<EnderecoDTO> enderecos;
	 
	 @Transactional
	 public void addEndereco(EnderecoDTO endereco) {
		 endereco.setPessoa(this);
		 this.enderecos.add(endereco);
	 }
	 
	 @Transactional
	 public void addEnderecos(List<EnderecoDTO> enderecos) {
		 enderecos.forEach(x -> x.setPessoa(this));
		 this.enderecos.addAll(enderecos);
	 }

}
