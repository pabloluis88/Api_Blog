A seguir, está uma versão do `README.md` formatada em Markdown, pronta para ser adicionada ao seu repositório Git.

-----

# 📚 Blog Simples com Spring Boot e JPA

Este projeto é uma API REST simples para um blog, desenvolvida com **Spring Boot**. Ele foi criado como parte de um desafio de programação para praticar a **persistência de dados** usando **Spring Data JPA** e um banco de dados **PostgreSQL** em um container Docker.

## 🚀 Tecnologias Utilizadas

  * **Java 17**: Linguagem de programação principal.
  * **Spring Boot 3.x**: Framework para construir a API.
  * **Spring Data JPA**: Para abstrair a interação com o banco de dados.
  * **Hibernate**: Implementação padrão de JPA no Spring Boot.
  * **Maven**: Ferramenta de gerenciamento de dependências e construção de projetos.
  * **PostgreSQL**: Banco de dados relacional.
  * **Docker**: Para gerenciar o container do banco de dados.

-----

## ⚙️ Pré-requisitos

Antes de executar a aplicação, certifique-se de ter os seguintes itens instalados:

  * **JDK 17**
  * **Maven**
  * **Docker** e **Docker Compose**

-----

## 🏃 Como Executar a Aplicação

### 1\. Inicialize o Banco de Dados com Docker

No diretório raiz do projeto, execute o seguinte comando para subir o container do PostgreSQL:

```bash
docker-compose up -d
```

Este comando irá criar e iniciar um container PostgreSQL na porta `5432`. As configurações de acesso ao banco de dados estão no arquivo `application.properties`.

### 2\. Configure a Aplicação

Verifique o arquivo `src/main/resources/application.properties` para garantir que as configurações do banco de dados correspondem às do seu container Docker:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3\. Execute a Aplicação

Você pode executar a aplicação diretamente da sua IDE ou usando o Maven:

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

-----

## 📖 Endpoints da API

A API expõe os seguintes endpoints para gerenciar posts.

| Método HTTP | Endpoint       | Descrição                                 |
| :---------- | :------------- | :---------------------------------------- |
| `GET`       | `/posts`       | Retorna todos os posts.                   |
| `GET`       | `/posts/{id}`  | Retorna um post específico pelo ID.       |
| `POST`      | `/posts`       | Cria um novo post.                        |
| `PUT`       | `/posts/{id}`  | Atualiza um post existente.               |
| `DELETE`    | `/posts/{id}`  | Deleta um post pelo ID.                   |

### Exemplo de Requisição `POST`

Para criar um novo post, envie um corpo JSON para `/posts`:

```json
POST http://localhost:8080/posts
Content-Type: application/json

{
  "titulo": "Primeiro Post do Blog",
  "conteudo": "Este é o conteúdo do meu primeiro post.",
  "autor": "Gabriel"
}
```

-----

## 🧪 Validações

As validações de entrada foram implementadas usando anotações como `@Nonnull` na classe `PostEntity`, garantindo que campos como `titulo`, `conteudo` e `autor` não sejam nulos. O Spring irá automaticamente retornar um erro `400 Bad Request` caso essas regras de validação sejam violadas.