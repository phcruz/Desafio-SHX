# Desafio-SHX
DESAFIO PRÁTICO

Descrição
O desafio consiste na criação de um “sistema” gerador de títulos a receber, seguindo regras específicas.

Tecnologias utilizadas
- Linguagem Java;
- Banco de dados PostgreSQL;
- Spring Boot;
- Spring MVC / WEB;
- Spring Data JPA;
- Thymeleaf;
- HTML;
- CSS;
- JavaScript;
- Jquery;
- Bootstrap;
- Apache Tomcat;
- Maven;

Script de criação de banco de dados e usuário
* CREATE DATABASE projeto;
* CREATE USER phcruz WITH PASSWORD 'phcruz';
* GRANT ALL PRIVILEGES ON DATABASE projeto TO phcruz;


Observações:
Documento detalhado e explicativo de telas encontra-se na pasta "Docs".
O script para a criação da base de dados, usuário e perfil de acesso/privilégios encontra-se na pasta "Scripts".
A criação das tabelas é delegada a aplicação por meio do hibernate.
A aplicação está configurada para utilização do banco de dados PostgreSQL, configuração esta que encontra-se no arquivo application.properties do projeto. Caso essas linhas sejam comentadas, a aplicação utiliação por default o banco de dados em memória H2.
As regras gerais do desafio encontra-se na pasta "Docs".

Para desenvolvimento foi utilizada a IDE STS, rodando a aplicação com o Tomcat embarcado do Spring Boot na porta padrão 8080. Os Mapeamentos são:
* http://localhost:8080/clientes/   -> para clientes
* http://localhost:8080/títulos/    -> para títulos
