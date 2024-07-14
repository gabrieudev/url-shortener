# API RESTful para encurtador de URLs 

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Seja bem vindo(a) ao meu projeto de **API RESTful para encurtador de URLs**. 

## Tabela de conteúdos

- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Iniciando](#iniciando)
- [Endpoints](#endpoints)
- [Contribuições](#contribuições)
- [Contato](#contato)

## Introdução

O objetivo principal deste projeto é fornecer uma API RESTful capaz de encurtar URLs. Além disso, também é possível customizar caracteres e visualizar a quantidade de acessos de uma URL encurtada.

## Funcionalidades

- Encurtamento de URL.
- Visualização da quantidade de acessos à URL.
- Integração com o banco de dados MySQL.
- Documentação de cada endpoint utilizando Swagger.

## Tecnologias

- ![Java](https://img.shields.io/badge/Java-17-orange): Linguagem de programação.
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green): Framework usado para a construção de aplicações.
- ![MySQL](https://img.shields.io/badge/MySQL-Database-blue): Banco de dados relacional.

## Iniciando

Siga esses passos para executar o projeto na sua máquina (é necessário ter o Docker instalado):

1. Clone o repositório: `git clone https://github.com/gabrieudev/url-shortener.git`
2. Navegue para o diretório do projeto: `cd <caminho>`
3. Navegue para o diretório docker: `cd docker`
4. Execute o arquivo docker compose para criar e inicializar o container MySQL: `docker compose up`
5. Volte para o diretório inicial: `cd <caminho>`
6. Construa o projeto: `./mvnw clean install` (para Windows: `mvnw.cmd clean install`)
7. Execute a aplicação: `./mvnw spring-boot:run` (para Windows: `mvnw.cmd spring-boot:run`)

## Endpoints

- `POST /shorten`: Encurta uma URL com caracteres aleatórios.
- `POST /shorten/custom`: Encurta uma URL adicionando caracteres customizados.
- `GET /r/{token}`: Redireciona de uma URL encurtada para a URL completa.
- `GET /r/{token}/count`: Obtém a quantidade de acessos à URL encurtada.
- `DELETE /r/{token}`: Deleta uma URL encurtada.

Acesse a documentação completa no endpoint `/swagger-ui.html`

## Contribuições

Contribuições são muito bem vindas! Caso queira contribuir, faça um fork do repositório e crie um pull request.

## Contato

Caso tenha alguma sugestão ou dúvida, entre em contato comigo em [LinkedIn](https://www.linkedin.com/in/gabrieudev)

---

**Licença:** Esse projeto é licenciado sob os termos da [GNU General Public License (GPL)](LICENSE).
