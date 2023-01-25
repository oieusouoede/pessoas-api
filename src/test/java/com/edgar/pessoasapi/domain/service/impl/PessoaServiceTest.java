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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.service.IPessoaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class PessoaServiceTest {
	
	private @Autowired IPessoaService service;
	
	@Test
    @Order(1)
    @DisplayName("Criar registro de pessoas")
	public void testarCriarPessoa() {
		
		ResponseEntity<PessoaDTO> response = service
				.criarPessoa(new PessoaDTO(
					null,
					"Marc",
					LocalDate.of(1997, 3, 12),
					new ArrayList<EnderecoDTO>()));
		assertTrue(response.getStatusCode().value() == 201);
		assertTrue(response.getBody().getId() == 1L);
		assertTrue(response.getBody().getNome() == "Marc");
	}
	
	@Test
    @Order(2)
    @DisplayName("Buscar pessoa por Id")
	public void testarPessoaPorId() {
		
		ResponseEntity<PessoaDTO> response = service.pessoaPorId(1L);
		assertTrue(response.getStatusCode().value() == 200);
		assertTrue(response.getBody().getId().equals(1L));
		assertTrue(response.getBody().getNome().equals("Marc"));
		
	}
	
	@Test
    @Order(3)
    @DisplayName("Editar registro de pessoas")	
	public void testarEditarPessoa() {
		
		LocalDate data = LocalDate.of(2003, 11, 5);
		PessoaDTO pessoaEditada = new PessoaDTO(
				1L,
				"Marc",
				data,
				null);
		
		ResponseEntity<PessoaDTO> response = service
				.editarPessoa(pessoaEditada);
		
		assertTrue(response.getStatusCode().value() == 204);
		assertTrue(response.getBody().getId().equals(1L));
		assertTrue(response.getBody().getNome().equals("Marc"));
		assertTrue(response.getBody().getDtNascimento().equals(data));
		
	}
	
	@Test
    @Order(4)
    @DisplayName("Deletar pessoas do registro")
	public void testarDeletarPessoa() {
		
		ResponseEntity<String> response = service.deletarPessoa(1L);
		assertTrue(response.getStatusCode().value() == 200);
		
	}
	
	@Test
    @Order(5)
    @DisplayName("Buscar todas as pessoas")
	public void testarTodasPessoas() {
		
		ResponseEntity<List<PessoaDTO>> response = service
				.todasPessoas();
		assertTrue(response.getStatusCode().value() == 204);
		
		service.criarPessoa(new PessoaDTO(null, "Marc", LocalDate.of(1997, 3, 12),	new ArrayList<EnderecoDTO>()));
		service.criarPessoa(new PessoaDTO(null, "Babidi", LocalDate.of(1995, 7, 23),	new ArrayList<EnderecoDTO>()));
		service.criarPessoa(new PessoaDTO(null, "Caco", LocalDate.of(1980, 1, 22),	new ArrayList<EnderecoDTO>()));
		service.criarPessoa(new PessoaDTO(null, "Rei Mauricio", LocalDate.of(1993, 9, 30),	new ArrayList<EnderecoDTO>()));
		
		response = service.todasPessoas();
		assertTrue(response.getBody().size() == 4);

		
	}

}
