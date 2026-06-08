# 📝 Blog Pessoal API

API REST para uma plataforma de blog pessoal, desenvolvida com Spring Boot 3 e Java 21. Oferece autenticação segura com JWT, gerenciamento de postagens e temas, e documentação interativa via Swagger.

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.14 |
| Spring Security | - |
| Spring Data JPA | - |
| MySQL | - |
| PostgreSQL | - |
| H2 (testes) | - |
| JWT (jjwt) | 0.12.6 |
| SpringDoc OpenAPI (Swagger) | 2.8.9 |
| Maven | Wrapper incluso |
| Docker | Multi-stage build |

## 📋 Funcionalidades

- Cadastro e autenticação de usuários
- Autenticação via token JWT
- CRUD de postagens
- CRUD de temas
- Relacionamento entre usuário, postagem e tema
- Documentação interativa via Swagger UI
- Containerização com Docker

## 🔗 API em produção

A API está disponível em:

```
https://blogpessoal-api-java84.onrender.com
```

Documentação Swagger:

```
https://blogpessoal-api-java84.onrender.com/swagger-ui/swagger-ui/index.html
```

## ⚙️ Como executar localmente

### Pré-requisitos

- Java 21+
- Maven (ou use o wrapper `./mvnw`)
- MySQL rodando localmente

### Configuração do banco de dados

No arquivo `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogpessoal
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executando

```bash
# Clone o repositório
git clone https://github.com/indiomedeiros/blogpessoal-api-java84.git
cd blogpessoal-api-java84

# Execute com o Maven Wrapper
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## 🐳 Executando com Docker

```bash
# Build da imagem
docker build -t blogpessoal-api .

# Execute o container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/blogpessoal \
  -e SPRING_DATASOURCE_USERNAME=seu_usuario \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha \
  blogpessoal-api
```

## 🧪 Testes

Os testes utilizam banco H2 em memória, sem necessidade de banco externo:

```bash
./mvnw test
```

## 📚 Endpoints principais

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/usuarios/cadastrar` | Cadastra novo usuário | ❌ |
| POST | `/usuarios/logar` | Autentica e retorna token JWT | ❌ |
| GET | `/postagens` | Lista todas as postagens | ✅ |
| POST | `/postagens` | Cria uma nova postagem | ✅ |
| PUT | `/postagens` | Atualiza uma postagem | ✅ |
| DELETE | `/postagens/{id}` | Remove uma postagem | ✅ |
| GET | `/temas` | Lista todos os temas | ✅ |
| POST | `/temas` | Cria um novo tema | ✅ |

> Endpoints marcados com ✅ requerem o header `Authorization: Bearer <token>`.

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)**. Para acessar endpoints protegidos:

1. Faça login em `POST /usuarios/logar` com e-mail e senha.
2. Copie o token retornado no campo `token`.
3. Inclua o header em todas as requisições:
   ```
   Authorization: Bearer <seu_token>
   ```

## 📁 Estrutura do projeto

```
src/
├── main/
│   ├── java/com/example/blogpessoal/
│   │   ├── controller/     # Controladores REST
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Interfaces Spring Data
│   │   ├── security/       # Configuração JWT e Spring Security
│   │   └── service/        # Regras de negócio
│   └── resources/
│       └── application.properties
└── test/                   # Testes com H2
```

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.
