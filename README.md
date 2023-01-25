# pessoas-api

Desafio Backend

## Especificações

Este projeto utiliza:

- Java 17
- Spring Boot 3.0.1
- Lombok 1.18.24
- Maven

## Rodando Localmente

Em ambiente de desenvolvimento:

1. Baixar o [Instalador do Eclipse](https://www.eclipse.org/downloads/) e instalar a versão Enterprise Edition (a mais atual deve funcionar).

2. Instalar o plugin do [Lombok](https://projectlombok.org/download) para o correto funcionamento.

3. Importar o projeto no Eclipse

4. Criar um run configuration ou simplesmente clicar com o botão direito na classe PessoasApiApplication e selecionar Run As > Java Application

Build manual com o Maven através de um terminal:

1. Certifique-se de ter o Maven instalado e configurado adequadamente. ([Ver documentação](https://maven.apache.org/install.html))

2. Abrir um terminal na pasta raíz do projeto

3. Rodar o comando `mvn clean install` para fazer o build com testes ou `mvn install -DskipTests` para pular os testes.

4. Rodar a aplicação com o comando `java -jar target/pessoas-api-0.0.1-SNAPSHOT.war` (Obs. É preciso ter o java adequadamente configurado)

Rodando com o com o spring boot run

1. Também precisa do Maven e o Java instalados e devidamente configurados

2. Executar o comando `mvn spring-boot:run`

## Sobre a API

A API ficará disponível localmente em https://localhost:8080/

Ela conta com uma documentação do Swagger que poder ser consultada no endpoint https://localhost:8080/swagger-ui.html por onde é possível consultar e testar todos os endpoints da apĺicação.

Esta API utiliza o Log4J2 para exibir logs de execução no console. A intenção inicial era gerar arquivos de log, o que não foi feito por questões de tempo e de possíveis problemas com versões de sistema operacional ou permissão de diretórios.

Alguns problemas de mapeamento atrasaram o desenvolvimento da aplicação, de forma que alguns tratamentos relacionados a validações não foram implementados, contudo a funcionalidade básica está toda operacional e todos os serviços essenciais estão contemplados nos testes unitários.
