package com.edgar.pessoasapi.domain.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "TB_PESSOA")
public class Pessoa {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idPessoa;
	 
	 @Column(name = "NOME") private String nome;
	 @Column(name = "NASCIMENTO") private Date dtNascimento;
	 
	 @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	 @JsonIgnoreProperties("pessoa")
	 private List<Endereco> enderecos;
}
