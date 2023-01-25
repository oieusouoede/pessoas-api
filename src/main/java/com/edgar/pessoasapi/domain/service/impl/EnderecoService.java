package com.edgar.pessoasapi.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.domain.entity.Endereco;
import com.edgar.pessoasapi.domain.repository.IEnderecoRepository;
import com.edgar.pessoasapi.domain.service.IEnderecoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EnderecoService implements IEnderecoService {
	
	private @Autowired ModelMapper mapper;
	private @Autowired IEnderecoRepository repository;
	
	@Override
	public ResponseEntity<EnderecoDTO> criarEndereco(EnderecoDTO enderecoNovo) {
		Endereco entity = mapper.map(enderecoNovo, Endereco.class);
		List<Endereco> enderecos = repository.enderecosPorPessoa(entity.getPessoa().getId());
		if (entity.getPadrao().equals(Boolean.TRUE) && enderecos.size() > 0) {	
			log.info("Usuário informou novo endereço padrão. Alterando regitros.");
			mudarEnderecoPadrao(enderecos);
		}
		if (entity.getPadrao().equals(Boolean.FALSE) && enderecos.size() == 0) {
			log.info("Usuário não possui endereço padrão definido. Definindo novo endereço como padrão.");
			entity.setPadrao(Boolean.TRUE);
		}
		Endereco enderecoSalvo = repository.save(entity);
		log.info("Novo endereço salvo com Id = "+enderecoSalvo.getId());
		return ResponseEntity.status(201).body(mapper.map(enderecoNovo, EnderecoDTO.class));
	}

	private void mudarEnderecoPadrao(List<Endereco> enderecos) {
		enderecos.forEach(e -> e.setPadrao(Boolean.FALSE));
		repository.saveAllAndFlush(enderecos);	
	}

	@Override
	@Transactional
    public ResponseEntity<EnderecoDTO> editarEndereco(EnderecoDTO enderecoEditado) {
		mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);

		Endereco entity = repository.findById(enderecoEditado.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado"));
  
		List<Endereco> enderecos = repository.enderecosPorPessoa(entity.getPessoa().getId());
		if (entity.getPadrao() && enderecos.size() > 0) {
			log.info("Usuário informou novo endereço padrão. Alterando regitros.");
			mudarEnderecoPadrao(enderecos);
		}
        
        mapper.map(enderecoEditado, entity);
        entity = repository.saveAndFlush(entity);
        log.info("Endereço com Id = "+ enderecoEditado.getId() + " atualizado");
        return ResponseEntity.status(204).body(mapToDto(entity));
    }

    private EnderecoDTO mapToDto(Endereco entity) {
        return mapper.map(entity, EnderecoDTO.class);
    }
	
	@Override
	public ResponseEntity<String> deletarEndereco(Long idEndereco) {
		Optional<Endereco> optional = repository.findById(idEndereco);
		
		if (optional.isPresent()) {
			repository.deleteById(idEndereco);
			log.info("Endereço com ID = "+ idEndereco + "deletado da base de dados");
			return ResponseEntity.status(200).body("Endereço excluido com sucesso");
		} else {
			log.error("Id não está na base de dados");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public ResponseEntity<List<EnderecoDTO>> enderecosPorPessoa(Long idPessoa) {
		List<Endereco> lista = repository.enderecosPorPessoa(idPessoa);
		
		if (lista.isEmpty()) {
			log.info("Pessoa com ID = " + idPessoa + " não possui endereços cadastrados");
			return ResponseEntity.status(204).build();
		} else {
			log.info("Retornando "+ lista.size() + " endereços");
			return ResponseEntity.status(200).body(lista
					.stream()
					.map(endereco -> mapper.map(endereco, EnderecoDTO.class))
					.collect(Collectors.toList()));
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public ResponseEntity<EnderecoDTO> enderecoPorId(Long idEndereco) {		
		return repository.findById(idEndereco)
				.map(resp -> {
					log.info("Retornando endereço com ID = " + resp.getId());
					return ResponseEntity.status(200).body(mapper.map(resp, EnderecoDTO.class));
				})
				.orElseGet(() -> {
					log.error("Id não está na base de dados");
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Não encontrado");
				});		
	}

}
