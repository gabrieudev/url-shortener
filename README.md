# Encurtador de URL

![Java](https://img.shields.io/badge/Java-8%2B-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-blue) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![Spring Boot](https://img.shields.io/badge/Type-Challenge-purple) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Essa é a minha resolução para o desafio de **Encurtador de URL**, uma API REST desenvolvida em Spring Boot. Acesse o [desafio](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md) para obter mais informações do problema.

## Tabela de conteúdos

- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Iniciando](#iniciando)
- [Configuração](#configuração)
- [Endpoints](#endpoints)
- [Contribuições](#contribuições)
- [Contato](#contato)

## Introdução

O desafio consiste em elaborar uma API REST capaz de receber uma URL, armazená-la em um banco de dados com um prazo de validade e, caso ela receba uma chamada, redirecionar para a URL original.  

## Funcionalidades

- Criar URL encurtada
- Acessar URL original através da encurtada

## Tecnologias

- ![Java](https://img.shields.io/badge/Java-8%2B-orange): Linguagem de programação.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework usado para a construção de aplicações web.
- ![MongoDB](https://img.shields.io/badge/MongoDB-Database-blue): Banco de dados NoSQL.

## Iniciando

Siga esses passos para executar o projeto na sua máquina:

1. Clone o repositório: `git clone https://github.com/gabrieudev/url-shortener.git`
2. Navegue para o diretório do projeto: `cd <caminho>`
3. Atualize as configurações de banco de dados no arquivo `application.properties`.
4. Construa o projeto: `./mvnw clean install` (para Windows: `mvnw.cmd clean install`)
5. Execute a aplicação: `./mvnw spring-boot:run` (para Windows: `mvnw.cmd spring-boot:run`)

## Configuração

- Atualize o arquivo `application.properties` com as informações do seu banco de dados MongoDB.
- Determine quanto tempo uma URL irá permanecer no banco de dados em `UrlService.java`, alterando o parâmetro de tipo LocalDateTime para o construtor da entidade `UrlModel` no método `save` (padrão é 1 hora).


## Endpoints

- `POST /shorten-url`: Encurta e salva uma URL no banco de dados.
- `GET /{id}`: Ao acessar a URL encurtada, redireciona para a URL original.

Acesse a documentação completa no endpoint `/swagger-ui.html`

## Contribuições

Contribuições são muito bem vindas! Caso queira contribuir, faça um fork do repositório e crie um pull request.

## Contato

Caso tenha alguma sugestão ou dúvida, entre em contato comigo em [LinkedIn](https://www.linkedin.com/in/gabrieudev)

---

**Licença:** Esse projeto é licenciado sob os termos da [GNU General Public License (GPL)](LICENSE).
