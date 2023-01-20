package com.edgar.pessoasapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ENDERECO")
public class Endereco {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idEndereco;
	 
	 @Column(name = "LOGRADOURO") private String logradouro;
	 @Column(name = "CEP") private String cep;
	 @Column(name = "NUMERO") private String numero;
	 @Column(name = "CIDADE") private String cidade;
	 
	@ManyToOne
	@JsonIgnoreProperties("enderecos")
	private Pessoa pessoa;
	 
}
