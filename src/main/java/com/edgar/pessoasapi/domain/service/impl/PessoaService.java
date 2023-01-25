package com.edgar.pessoasapi.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.entity.Pessoa;
import com.edgar.pessoasapi.domain.repository.IPessoaRepository;
import com.edgar.pessoasapi.domain.service.IPessoaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PessoaService implements IPessoaService {
	
	private @Autowired ModelMapper mapper;
	private @Autowired IPessoaRepository repository;
	
	@Override
	public ResponseEntity<PessoaDTO> criarPessoa(PessoaDTO pessoaNova) {
		
		Pessoa pessoaCriada = repository.save(mapper.map(pessoaNova, Pessoa.class));
		log.info("Criada nova pessoa! ID = " + pessoaCriada.getId() + " Nome = " + pessoaCriada.getNome());
		return ResponseEntity.status(201).body(mapper.map(pessoaCriada, PessoaDTO.class));
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public ResponseEntity<PessoaDTO> pessoaPorId(Long id){
		return repository.findById(id)
                .map(resp -> {
                    log.info("Retornando pessoa! ID = " + resp.getId() + " Nome = " + resp.getNome());
                    return ResponseEntity.status(200).body(mapper.map(resp, PessoaDTO.class));
                })
                .orElseGet(() -> {
                    log.error("Id solicitado não está na base de dados");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Não encontrado");
                });
	
	}

	@Override
	public ResponseEntity<PessoaDTO> editarPessoa(PessoaDTO pessoaModificada) {
		Optional<Pessoa> opt = repository.findById(pessoaModificada.getId());
		Pessoa entity = mapper.map(pessoaModificada, Pessoa.class);
		if (opt.isPresent()) {
			log.info("Pessoa editada! ID = " + entity.getId() + " Nome " + entity.getNome());
			return ResponseEntity
					.status(204)
					.body(mapper.map(repository.save(entity), PessoaDTO.class));
		} else {
			log.error("Id não está na base de dados");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
		
	}

	@Override
	public ResponseEntity<String> deletarPessoa(Long idPessoa) {
		Optional<Pessoa> optional = repository.findById(idPessoa);
		
		if (optional.isPresent()) {
			repository.deleteById(idPessoa);
			log.info("Pessoa com ID = "+ idPessoa + "deletada da base de dados");
			return ResponseEntity.status(200).body("Pessoa excluida com sucesso");
		} else {
			log.error("Id não está na base de dados");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public ResponseEntity<List<PessoaDTO>> todasPessoas() {
		List<Pessoa> lista = repository.findAll();
		
		if (lista.isEmpty()) {
			log.info("Não há registros de pessoas na base de dados");
			return ResponseEntity.status(204).build();
		} else {
			log.info("Retornando "+ lista.size() + " pessoas");
			return ResponseEntity.status(200).body(lista
					.stream()
					.map(pessoa -> mapper.map(pessoa, PessoaDTO.class))
					.collect(Collectors.toList()));
		}
	}
}
