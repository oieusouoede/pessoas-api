package com.edgar.pessoasapi.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBlogdoedeOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Pessoas API")
						.description("Desafio Backend")
						.version("v0.0.1")
				.contact(new Contact()
						.name("Edgar Soares")
						.url("https://github.com/oieusouoede")
						.email("edemarinho@outlook.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Repositorio")
						.url("https://github.com/oieusouoede/pessoas-api"));
	}

}
