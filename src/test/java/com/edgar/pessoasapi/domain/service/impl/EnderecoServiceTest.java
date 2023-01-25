package com.edgar.pessoasapi.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.service.IEnderecoService;
import com.edgar.pessoasapi.domain.service.IPessoaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnderecoServiceTest {
	
	private @Autowired IEnderecoService service;
	private @Autowired IPessoaService pService;
	
	@Test
    @Order(1)
    @DisplayName("Criar registro de endereço")
	public void testarCriarEndereco() {
		
		PessoaDTO pessoa = new PessoaDTO();
		pessoa.setNome("Marquinhos");
		pessoa.setDtNascimento(LocalDate.of(1994, 12, 30));
		pessoa.setEnderecos(new ArrayList<>());
		ResponseEntity<PessoaDTO> pessoaSalva = pService.criarPessoa(pessoa);
		
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setCep("78993567788");
		endereco.setCidade("São Paulo");
		endereco.setLogradouro("Rua das Calçadas");
		endereco.setNumero("5");
		endereco.setPadrao(Boolean.TRUE);
		endereco.setPessoa(pessoaSalva.getBody());
						
		ResponseEntity<EnderecoDTO> response = service
				.criarEndereco(endereco);
		assertTrue(response.getStatusCode().value() == 201);
		assertTrue(response.getBody().getId().equals(1L));
		assertTrue(response.getBody().getCep().equals("78993567788"));
	}
	
	@Test
    @Order(2)
    @DisplayName("Mudar endereço padrão")
	public void testarMudarEnderecoPadrao() {
		
		ResponseEntity<PessoaDTO> resPessoa = pService.pessoaPorId(1L);
		PessoaDTO pessoa = resPessoa.getBody();
		
		EnderecoDTO endereco1 = new EnderecoDTO();
		endereco1.setCep("12243567788");
		endereco1.setCidade("São Paulo");
		endereco1.setLogradouro("Rua das Jujubas");
		endereco1.setNumero("55");
		endereco1.setPadrao(Boolean.TRUE);
		endereco1.setPessoa(pessoa);
		
		EnderecoDTO endereco2 = new EnderecoDTO();
		endereco2.setCep("11111111111");
		endereco2.setCidade("São José dos Campos");
		endereco2.setLogradouro("Rua das Palmeiras");
		endereco2.setNumero("25");
		endereco2.setPadrao(Boolean.TRUE);
		endereco2.setPessoa(pessoa);
		
		EnderecoDTO endereco3 = new EnderecoDTO();
		endereco3.setCep("2222111111");
		endereco3.setCidade("São Sebastião");
		endereco3.setLogradouro("Rua Amarela");
		endereco3.setNumero("12");
		endereco3.setPadrao(Boolean.FALSE);
		endereco3.setPessoa(pessoa);
				
		ResponseEntity<EnderecoDTO> response1 = service.criarEndereco(endereco1);
		ResponseEntity<EnderecoDTO> response2 = service.criarEndereco(endereco2);
		ResponseEntity<EnderecoDTO> response3 = service.criarEndereco(endereco3);
		
		assertTrue(response1.getStatusCode().value() == 201);
		assertTrue(response2.getStatusCode().value() == 201);
		assertTrue(response3.getStatusCode().value() == 201);
		
		ResponseEntity<EnderecoDTO> resEndereco1 = service.enderecoPorId(2L);
		ResponseEntity<EnderecoDTO> resEndereco2 = service.enderecoPorId(3L);
		ResponseEntity<EnderecoDTO> resEndereco3 = service.enderecoPorId(4L);
		
		assertTrue(!resEndereco1.getBody().getPadrao()); // False
		assertTrue(resEndereco2.getBody().getPadrao()); // True
		assertTrue(!resEndereco3.getBody().getPadrao()); // False
		
	}
	
	@Test
    @Order(3)
    @DisplayName("Editar registro de endereco")	
	public void testarEditarEndereco() {
			
		EnderecoDTO endereco = service.enderecoPorId(1L).getBody();
		
		endereco.setCep("00000000000");
		
		ResponseEntity<EnderecoDTO> response = service
				.editarEndereco(endereco);
		
		assertTrue(response.getStatusCode().value() == 204);
				
		ResponseEntity<EnderecoDTO> resEndereco = service
				.enderecoPorId(1L);
		
		assertTrue(resEndereco.getBody().getId().equals(1L));
		assertTrue(resEndereco.getBody().getCep().equals("00000000000"));	
		
	}
	
	@Test
    @Order(4)
    @DisplayName("Deletar endereços do registro")
	public void testarDeletarEndereco() {
		
		ResponseEntity<String> response = service.deletarEndereco(1L);
		assertTrue(response.getStatusCode().value() == 200);
		
	}
	
	@Test
    @Order(5)
    @DisplayName("Buscar endereços por Pessoa")
	public void testarEnderecosPorPessoa() {
		
		ResponseEntity<List<EnderecoDTO>> response = service
				.enderecosPorPessoa(1L);
		assertTrue(response.getStatusCode().value() == 200);
		assertTrue(response.getBody().size() == 3);
			
	}

}
