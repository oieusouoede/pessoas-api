package com.edgar.pessoasapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.pessoasapi.domain.entity.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {

}
