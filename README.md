# Vacancy Manager

Sistema de gerenciamento de vagas de emprego desenvolvido com Spring Boot. Permite o cadastro de empresas, candidatos e vagas de trabalho.

## Tecnologias

- **Java 21**
- **Spring Boot 3.5.10**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**
- **Lombok**
- **Spring Validation**

## Estrutura do Projeto

```
vacancy_manager/
├── src/main/java/br/com/felipemenezes/vacancy_manager/
│   ├── VacancyManagerApplication.java
│   ├── exceptions/
│   │   ├── ErrorMessageDTO.java
│   │   ├── ExceptionHandlerController.java
│   │   └── UserFoundException.java
│   └── modules/
│       ├── candidate/
│       │   ├── CandidateEntity.java
│       │   ├── controllers/
│       │   │   ├── CandidateController.java
│       │   │   └── CandidateRepository.java
│       │   └── useCases/
│       │       ├── CreateCandidateUseCase.java
│       │       └── CreateCompanyUseCase.java
│       └── company/
│           ├── controllers/
│           │   ├── CompanyController.java
│           │   └── JobController.java
│           ├── entities/
│           │   ├── CompanyEntity.java
│           │   └── JobEntity.java
│           ├── repositories/
│           │   ├── CompanyRepository.java
│           │   └── JobRepository.java
│           └── useCases/
│               └── CreateJobUseCase.java
├── src/main/resources/
│   ├── application.properties
│   └── application-test.properties
├── docker-compose.yaml
├── pom.xml
└── README.md
```

## Arquitetura

O projeto segue uma arquitetura em camadas com separação de responsabilidades:

- **Controllers**: Responsáveis por receber requisições HTTP e retornar respostas
- **UseCases**: Contêm a lógica de negócio da aplicação
- **Repositories**: Interface com o banco de dados utilizando Spring Data JPA
- **Entities**: Representam as tabelas do banco de dados
- **Exceptions**: Tratamento global de exceções

## Entidades

### Candidate (Candidato)

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| nome | String | Nome do candidato |
| username | String | Nome de usuário (sem espaços) |
| email | String | E-mail válido |
| password | String | Senha (10-100 caracteres) |
| description | String | Descrição do candidato |
| curriculum | String | Currículo |
| createdAt | LocalDateTime | Data de criação |

### Company (Empresa)

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| nome | String | Nome da empresa |
| username | String | Nome de usuário (sem espaços) |
| email | String | E-mail válido |
| password | String | Senha (10-100 caracteres) |
| website | String | Site da empresa |
| description | String | Descrição da empresa |
| createdAt | LocalDateTime | Data de criação |

### Job (Vaga)

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| description | String | Descrição da vaga |
| benefits | String | Benefícios |
| level | String | Nível (obrigatório) |
| company_id | UUID | ID da empresa |
| created_at | LocalDateTime | Data de criação |

## Endpoints da API

### Candidatos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/candidate/` | Cria um novo candidato |

**Exemplo de requisição:**
```json
{
  "nome": "João Silva",
  "username": "joaosilva",
  "email": "joao@email.com",
  "password": "senhaSegura123",
  "description": "Desenvolvedor Java",
  "curriculum": "URL do currículo"
}
```

### Empresas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/company/` | Cria uma nova empresa |

**Exemplo de requisição:**
```json
{
  "nome": "Tech Solutions",
  "username": "techsolutions",
  "email": "contato@techsolutions.com",
  "password": "senhaSegura123",
  "website": "www.techsolutions.com",
  "description": "Empresa de tecnologia"
}
```

### Vagas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/job/` | Cria uma nova vaga |

**Exemplo de requisição:**
```json
{
  "description": "Desenvolvedor Java Pleno",
  "benefits": "Vale refeição, home office",
  "level": "Pleno",
  "company_id": "uuid-da-empresa"
}
```

## Validações

- **Username**: Não pode conter espaços em branco
- **Email**: Deve ser um e-mail válido
- **Password**: Mínimo 10 e máximo 100 caracteres
- **Level** (Job): Campo obrigatório

## Tratamento de Erros

O sistema possui tratamento global de exceções que retorna erros de validação no formato:

```json
[
  {
    "message": "mensagem de erro",
    "field": "nomeDoCampo"
  }
]
```

### Exceções Personalizadas

- **UserFoundException**: Lançada quando tenta criar um usuário (candidato ou empresa) com username ou email já existentes

## Configuração do Banco de Dados

### PostgreSQL com Docker

O projeto inclui um arquivo `docker-compose.yaml` para facilitar a configuração do banco de dados:

```yaml
version: '3.8'

services:
  postgres:
    container_name: gestao_vagas_postgres
    image: postgres
    ports:
      - 5432:5432
    environment:
      - POSTGRES_USER=admin
      - POSTGRES_PASSWORD=admin
      - POSTGRES_DB=gestao_vagas
```

### Configurações de Conexão

**application.properties:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_vagas
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

## Como Executar

### Pré-requisitos

- Java 21
- Maven
- Docker e Docker Compose

### Passos

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd vacancy_manager
   ```

2. **Inicie o banco de dados PostgreSQL:**
   ```bash
   docker-compose up -d
   ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   
   Ou no Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

4. **Acesse a API:**
   A aplicação estará disponível em `http://localhost:8080`

### Usando Maven Wrapper

O projeto inclui o Maven Wrapper (`mvnw`), não é necessário ter o Maven instalado:

```bash
# Linux/Mac
./mvnw clean install
./mvnw spring-boot:run

# Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

## Desenvolvimento

### Perfil de Teste

O arquivo `application-test.properties` está configurado para execução de testes.

### DevTools

O Spring Boot DevTools está configurado para reiniciar a aplicação automaticamente durante o desenvolvimento.

## Testes

Execute os testes com:

```bash
./mvnw test
```

## Dependências Principais

| Dependência | Versão | Descrição |
|------------|--------|-----------|
| spring-boot-starter-web | 3.5.10 | Web framework |
| spring-boot-starter-data-jpa | 3.5.10 | Persistência de dados |
| spring-boot-starter-validation | 3.5.10 | Validação de dados |
| postgresql | 42.6.0 | Driver PostgreSQL |
| lombok | 1.18.38 | Redução de boilerplate |
| spring-boot-devtools | 3.5.10 | Hot reload |

## Autor

Felipe Menezes

## Licença

Este projeto está sob a licença MIT.
