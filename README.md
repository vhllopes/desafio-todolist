# Desafio To-Do List API

API RESTful para gerenciamento de tarefas (To-Do list), desenvolvida como um desafio prático utilizando Spring Boot. A aplicação permite criar, listar, atualizar e deletar tarefas, com os dados sendo persistidos em um banco de dados MySQL.

---

## Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias e ferramentas:

* **Java 21**: Versão da linguagem de programação.
* **Spring Boot 3.5.5**: Framework principal para a construção da aplicação.
* **Spring Web**: Para a criação de endpoints RESTful.
* **Spring Data JPA**: Para a persistência de dados de forma simplificada.
* **Hibernate**: Implementação JPA para o mapeamento objeto-relacional (ORM).
* **MySQL**: Banco de dados relacional para armazenamento das tarefas.
* **Spring Boot Validation**: Para validação dos dados de entrada na API.
* **SpringDoc OpenAPI (Swagger)**: Para documentação interativa e visualização da API.
* **Maven**: Gerenciador de dependências e build do projeto.
* **H2 Database**: Banco de dados em memória utilizado para os testes automatizados.

---

## Funcionalidades

A API oferece as operações básicas de um CRUD (Create, Read, Update, Delete) para gerenciar tarefas.

* ✅ **Criar Tarefa**: Adiciona uma nova tarefa à lista.
* ✅ **Listar Tarefas**: Retorna a lista completa de tarefas, ordenadas por prioridade (maior primeiro) e depois por nome.
* ✅ **Atualizar Tarefa**: Modifica os dados de uma tarefa existente.
* ✅ **Deletar Tarefa**: Remove uma tarefa da lista pelo seu ID.

---

## Como Executar o Projeto

Siga os passos abaixo para executar a aplicação localmente.

### Pré-requisitos

* **Java JDK 21** ou superior.
* **Maven 3.8** ou superior.
* Um servidor de banco de dados **MySQL** ativo.

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://LINK_DO_REPOSITORIO.git
    cd desafio-todolist
    ```

2.  **Configure o banco de dados:**
    * Crie um banco de dados no MySQL com o nome `todolist`.
    * Abra o arquivo `src/main/resources/application.properties` e insira seu usuário e senha do MySQL:
        ```properties
        spring.datasource.username=SEU_USUARIO_MYSQL
        spring.datasource.password=SUA_SENHA_MYSQL
        ```

3.  **Execute a aplicação:**
    Utilize o Maven para iniciar o servidor Spring Boot.
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

---

## Como Usar a API

Após iniciar a aplicação, você pode interagir com os endpoints usando uma ferramenta como o Postman, ou acessar a documentação interativa do Swagger.

### Documentação com Swagger UI

Acesse a seguinte URL no seu navegador para ver todos os endpoints e testá-los de forma interativa:

➡ **`http://localhost:8080/swagger-ui.html`**


### Exemplos de Endpoints

#### 1. Criar uma Tarefa (`POST`)

* **URL:** `http://localhost:8080/todos`
* **Método:** `POST`
* **Corpo da Requisição (Body - JSON):**
    ```json
    {
      "nome": "Estudar Spring Boot",
      "descricao": "Finalizar o desafio da API To-Do List",
      "status": false,
      "prioridade": 5
    }
    ```

#### 2. Listar Todas as Tarefas (`GET`)

* **URL:** `http://localhost:8080/todos`
* **Método:** `GET`

#### 3. Atualizar uma Tarefa (`PUT`)

* **URL:** `http://localhost:8080/todos`
* **Método:** `PUT`
* **Corpo da Requisição (Body - JSON):**
    * *O objeto deve conter o `id` da tarefa que será atualizada.*
    ```json
    {
      "id": 1,
      "nome": "Estudar Spring Boot e Testes",
      "descricao": "Implementar os testes unitários da aplicação",
      "status": true,
      "prioridade": 5
    }
    ```

#### 4. Deletar uma Tarefa (`DELETE`)

* **URL:** `http://localhost:8080/todos/1`
* **Método:** `DELETE`
* *Onde `1` é o ID da tarefa que você deseja deletar.*

---

## Autor

Feito por **Vitor Lopes**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/https://www.linkedin.com/in/vhllopes)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/vhllopes)
