package com.edgar.pessoasapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edgar.pessoasapi.domain.entity.Endereco;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
	
	@Query("SELECT e FROM Endereco e WHERE e.pessoa.id = :id")
    List<Endereco> enderecosPorPessoa (@Param("id") Long idPessoa);
		
}
