# Desafio 2: API de Gerenciamento de Blog ✍️

## 📄 Descrição do Projeto

Esta API REST foi desenvolvida para gerenciar posts de um blog. Ao contrário do desafio anterior, os dados são persistidos em um banco de dados **PostgreSQL** rodando em um container Docker. A aplicação utiliza **Spring Data JPA** para a comunicação com o banco de dados, seguindo o padrão de arquitetura em camadas (`Controller`, `Service`, `Repository` e `Model`).

## 🚀 Tecnologias e Ferramentas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA:** Para persistência de dados.
* **Lombok:** Para reduzir o código boilerplate.
* **Maven:** Gerenciador de dependências.
* **Docker e Docker Compose:** Para orquestração do banco de dados (PostgreSQL) e do cliente gráfico (pgAdmin).
* **PostgreSQL:** Banco de dados relacional.
* **UUID:** Utilizado como tipo de dado para os IDs, garantindo identificadores únicos.

---

## 🏗️ Estrutura e Configuração

O projeto é configurado com dois serviços em contêineres:

* **`postgres`**: Onde os dados do blog são armazenados. O volume nomeado `postgres_data` garante a persistência dos dados.
* **`pgadmin`**: Uma interface gráfica para gerenciar o banco de dados.

As configurações de conexão e o comportamento do JPA são definidos no arquivo `src/main/resources/application.properties`.

---

## 🛠️ Endpoints da API

A API está disponível no caminho base `/postagens`.

### 1. Criar um novo post

* **`POST /postagens`**
* **Descrição**: Cria um novo post no banco de dados. O ID e a data de criação são gerados automaticamente pelo sistema.
* **Corpo da Requisição**:
    ```json
    {
        "titulo": "Primeiro Post do Blog",
        "conteudo": "Este é o conteúdo do primeiro post, que será persistido no banco de dados.",
        "autor": "Fulano de Tal"
    }
    ```
* **Resposta (Status: 201 Created)**:
    ```json
    {
        "id": "e6f9b1d0-1c9f-4b0d-8c4d-2a1e3f5c7d8a",
        "titulo": "Primeiro Post do Blog",
        "conteudo": "Este é o conteúdo do primeiro post, que será persistido no banco de dados.",
        "autor": "Fulano de Tal",
        "dataCriacao": "2024-08-14T10:00:00.123456789"
    }
    ```

### 2. Listar todos os posts

* **`GET /postagens`**
* **Descrição**: Retorna uma lista de todos os posts cadastrados no banco de dados.
* **Resposta (Status: 200 OK)**:
    ```json
    [
        {
            "id": "e6f9b1d0-1c9f-4b0d-8c4d-2a1e3f5c7d8a",
            "titulo": "Primeiro Post do Blog",
            "conteudo": "...",
            "autor": "...",
            "dataCriacao": "..."
        },
        // ... outros posts
    ]
    ```

### 3. Buscar um post por ID

* **`GET /postagens/{id}`**
* **Descrição**: Retorna um único post pelo seu ID.
* **Exemplo de URL**: `http://localhost:8080/postagens/d33bd751-71cb-4690-8b75-1469e7989c1b`
* **Resposta (Status: 200 OK)**: Se o post for encontrado.
* **Resposta (Status: 404 Not Found)**: Se o post não for encontrado.

### 4. Buscar posts por período

* **`GET /postagens/buscarPorPeriodo`**
* **Descrição**: Retorna uma lista de posts criados em um período específico.
* **Exemplo de URL**: `http://localhost:8080/postagens/buscarPorPeriodo?inicio=2024-08-01&fim=2024-08-15`
* **Parâmetros de Query**:
    * `inicio`: Data de início no formato `YYYY-MM-DD`.
    * `fim`: Data de fim no formato `YYYY-MM-DD`.
* **Resposta (Status: 200 OK)**:
    ```json
    [
        // ... lista de posts criados entre as datas fornecidas
    ]
    ```

### 5. Atualizar um post

* **`PUT /postagens/{id}`**
* **Descrição**: Atualiza completamente um post existente.
* **Corpo da Requisição**:
    ```json
    {
        "titulo": "Título Atualizado",
        "conteudo": "Novo conteúdo do post.",
        "autor": "Autor Atualizado"
    }
    ```
* **Resposta (Status: 200 OK)**: Se o post for atualizado com sucesso.
* **Resposta (Status: 404 Not Found)**: Se o post não for encontrado.

### 6. Deletar um post

* **`DELETE /postagens/{id}`**
* **Descrição**: Deleta um post pelo seu ID.
* **Resposta (Status: 204 No Content)**: Se o post for deletado com sucesso.
* **Resposta (Status: 404 Not Found)**: Se o post não for encontrado.

---

## ▶️ Como Executar o Projeto

1.  **Pré-requisitos**: Certifique-se de ter o **Docker**, **Java 17** e **Maven** instalados.
2.  **Inicie os Contêineres**: Abra o terminal no diretório do projeto e execute:
    ```bash
    docker-compose up -d
    ```
3.  **Execute a Aplicação Spring Boot**: No terminal, execute o comando:
    ```bash
    mvn spring-boot:run
    ```
4.  A API estará rodando em `http://localhost:8080`.

---

## 👨‍💻 Autor

* **Seu Nome** - [https://www.linkedin.com/in/pabloleiteti/] / [https://github.com/pabloluis88]

