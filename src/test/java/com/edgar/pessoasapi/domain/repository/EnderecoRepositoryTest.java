package com.edgar.pessoasapi.domain.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.entity.Endereco;
import com.edgar.pessoasapi.domain.service.IPessoaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoRepositoryTest {
	
	private @Autowired IEnderecoRepository repository;
	private @Autowired IPessoaService service;
	
	@Test
    @DisplayName("Buscar endereços de uma pessoa")
	public void testarEnderecosPorPessoa() {
			
		PessoaDTO pessoaEntity = new PessoaDTO();
		pessoaEntity.setNome("Marc");
		pessoaEntity.setDtNascimento(LocalDate.of(1997, 3, 12));
		pessoaEntity.setEnderecos(new ArrayList<EnderecoDTO>());
		
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setPessoa(pessoaEntity);
		endereco.setLogradouro("Avenida Almirante Maximiniano");
		endereco.setCep("96204040");
		endereco.setNumero("299");
		endereco.setCidade("Rio  Grande");
		endereco.setPadrao(Boolean.TRUE);
		
		pessoaEntity.addEndereco(endereco);
				
		ResponseEntity<PessoaDTO> pessoaCriada =  service.criarPessoa(pessoaEntity);		
		
		List<Endereco> retorno = repository.enderecosPorPessoa(pessoaCriada.getBody().getId());
		assertTrue(retorno.size() == 1);
		assertTrue(retorno.get(0).getCep().equals("96204040"));
	}

}
