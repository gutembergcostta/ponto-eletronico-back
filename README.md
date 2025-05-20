# Worklog - Projeto Spring Boot

Este é um projeto **Spring Boot** desenvolvido para gerenciar logs de trabalho. Ele utiliza **PostgreSQL** como banco de dados e emprega autenticação **JWT**.

## 🚀 Tecnologias e Dependências

O projeto utiliza as seguintes dependências:

- **Spring Boot 3.4.5** (Starter Parent)
- **Spring Boot Starter Data JPA** (Gerenciamento ORM)
- **Spring Boot Starter Web** (APIs REST)
- **Spring Boot Starter Security** (Autenticação)
- **PostgreSQL** (Driver do banco de dados)
- **Flyway** (Migração de banco de dados)
- **Lombok** (Redução de código boilerplate)
- **Java JWT (Auth0) 4.4.0** (Autenticação JWT)

## 📌 Pré-requisitos

Antes de instalar e rodar o projeto, você precisa:

- **Java 17** instalado
- **Maven 3+** instalado
- **PostgreSQL** configurado

## ⚙️ Como instalar

Clone o repositório e entre na pasta do projeto:

```bash
git clone https://github.com/gutembergcostta/ponto-eletronico-back.git
cd worklog
```

## 🏗️ Configuração do banco de dados

Crie um banco PostgreSQL chamado worklog e configure suas credenciais em `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/worklog
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ Como rodar o projeto

Usando Maven:

```bash
mvn spring-boot:run
```

Ou gerando um JAR:

```bash
mvn clean package
java -jar target/worklog-0.0.1-SNAPSHOT.jar
```

## 🔥 Testando a API

Com o servidor rodando, acesse a API:

http://localhost:8080/

## 📚 Lista de Endpoints da API

```
POST /auth/register - Registro de novos usuários

POST auth/login - Login de usuários cadastrados.

GET /users - Retorna usuários cadastrados

PUT /users/id - Atualiza usuário

DELETE /users/id - Deleta usuário

GET /worklogs/today/id - Retorna resumo de horas para o dia por usuário

GET /work-shift-types - Retorna tipos de carga horária disponíveis

GET /roles - Retorna cargos disponíveis
```

- Todos com exceção do Login(auth/login) são protegidos por autenticação
- Registro de usuários protegido por cargo usuário (Somente Admins)
