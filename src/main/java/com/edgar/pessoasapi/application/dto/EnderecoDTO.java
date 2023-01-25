package com.edgar.pessoasapi.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
	
	private Long id;	 
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	@JsonIgnoreProperties("pessoa")
	private PessoaDTO pessoa;
	private Boolean padrao;

}
