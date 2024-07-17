# API RESTful para encurtador de URL

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) [![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/gabrieudev) ![GPL License](https://img.shields.io/badge/License-GPL-blue)

Seja bem vindo(a) ao meu projeto de **API RESTful para encurtador de URL**. 

## Tabela de conteúdos

- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Iniciando](#iniciando)
- [Endpoints](#endpoints)
- [Contribuições](#contribuições)
- [Contato](#contato)

## Introdução

O objetivo principal deste projeto é fornecer uma API RESTful capaz de receber uma URL, armazená-la em um banco de dados e, caso ela receba uma chamada, redirecionar para a URL original. Além disso, o projeto implementa autenticação com JWTs e autorização por meio de roles para os usuários, utilizando as melhores e mais atualizadas práticas do mercado para assegurar a integridade dos dados sensíveis.

## Funcionalidades

- Login e registro de usuários.
- Envio de email para confirmação do registro.
- Encurtamento de URL.
- Escolha de plano de assinatura para ter URLs sem prazo de expiração.
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

User:

- `POST /auth/register`: Registra um usuário e envia um link para confirmação ao seu email.
- `GET /users/confirm`: Faz a verificação do email.
- `POST /auth/login`: Realiza o login e recebe um JWT.
- `ADMIN Role` `GET /users`: Obtém todos usuários.
- `ADMIN Role` `DELETE /users/{userId}`: Deleta um usuário.
- `BASIC Role` `GET /users/{userId}`: Obtém um usuário de acordo com o ID.
- `BASIC Role` `POST /users/change-password`: Muda a senha de um usuário.

URL:

- `BASIC Role` `POST /shorten`: Encurta uma URL com caracteres aleatórios.
- `BASIC Role` `POST /shorten/custom`: Encurta uma URL adicionando caracteres customizados.
- `GET /r/{token}`: Redireciona de uma URL encurtada para a URL completa.
- `BASIC Role` `GET /r/{token}/count`: Obtém a quantidade de acessos à URL encurtada.
- `ADMIN Role` `DELETE /r/{token}`: Deleta uma URL encurtada.
- `BASIC Role` `GET /history`: Obtém o histórico de URLs encurtadas.

User subscription plan:

- `BASIC Role` `POST /user-subscription/change`: Escolhe um plano de assinatura.
- `BASIC Role` `GET /user-subscription/subscription-plan`: Obtém o plano de assinatura atual do usuário.

Acesse a documentação completa no endpoint `/swagger-ui.html`

## Contribuições

Contribuições são muito bem vindas! Caso queira contribuir, faça um fork do repositório e crie um pull request.

## Contato

Caso tenha alguma sugestão ou dúvida, entre em contato comigo em [LinkedIn](https://www.linkedin.com/in/gabrieudev)

---

**Licença:** Esse projeto é licenciado sob os termos da [GNU General Public License (GPL)](LICENSE).
