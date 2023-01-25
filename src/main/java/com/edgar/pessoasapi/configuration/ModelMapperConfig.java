package com.edgar.pessoasapi.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edgar.pessoasapi.application.dto.EnderecoDTO;
import com.edgar.pessoasapi.application.dto.PessoaDTO;
import com.edgar.pessoasapi.domain.entity.Endereco;
import com.edgar.pessoasapi.domain.entity.Pessoa;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Pessoa, PessoaDTO> typeMapPessoa = mapper.createTypeMap(Pessoa.class, PessoaDTO.class);
        typeMapPessoa.addMappings(mapping -> mapping.skip(PessoaDTO::setEnderecos));

        TypeMap<Endereco, EnderecoDTO> typeMapEndereco = mapper.createTypeMap(Endereco.class, EnderecoDTO.class);
        typeMapEndereco.addMappings(mapping -> mapping.skip(EnderecoDTO::setPessoa));

        return mapper;
    }
}

