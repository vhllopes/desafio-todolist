package br.com.vitorlopes.desafiotodolist;

import br.com.vitorlopes.desafiotodolist.entity.Todo;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo test", "desc todo test", false, 1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray() // Verifica se a resposta é um array
                .jsonPath("$.size()").isEqualTo(1) // Verifica se o array tem 1 elemento
                .jsonPath("$[0].nome").isEqualTo(todo.getNome()) // Verifica o campo 'nome' do primeiro elemento
                .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao()) // Verifica o campo 'descricao' do primeiro elemento
                .jsonPath("$[0].status").isEqualTo(todo.getStatus()) // Verifica o campo 'status' do primeiro elemento
                .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade()); // Verifica o campo 'prioridade' do primeiro elemento
    }

    //Teste que retorna falha 400 - bad request quando "nome" ou "descrição" são blanks
    @Test
    void testCreateTodoFailureWhenBlank() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new Todo("", "", false, 0)
                ).exchange()
                .expectStatus().isBadRequest();
    }

    //Teste que retorna falha 400 - bad request quando atribui-se valores invalidos em "prioridade"
    @Test
    void testCreateTodoFailureWhenPriorityIsInvalid() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new Todo("teste", "testando", false, -3)
                ).exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testListTodoSuccess() {
        webTestClient
                .get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateTodoSuccess() {
        var todoToCreate = new Todo("Nome Original", "Descricao Original", false, 2);

        Todo createdTodo = webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todoToCreate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Todo[].class) //A resposta esperada é um array de todos
                .returnResult()
                .getResponseBody()[0]; //Pegamos o primeiro e único elemento do array retornado

        //Garantir que o id não nulo
        assertNotNull(todoToCreate.getId());

        // CORREÇÃO: Use o ID do `createdTodo`, que não é nulo.
        var todoToUpdate = new Todo(createdTodo.getId(), "Nome Atualizado", "Descrição Atualizada", true, 7);

        webTestClient
                .put()
                .uri("/todos/" + createdTodo.getId())
                .bodyValue(todoToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                // Como a API retorna a lista completa, vamos usar um filtro JsonPath
                // para encontrar o objeto com o ID que atualizamos.
                // A sintaxe `$[?(@.id == ...)]` significa: "No array, encontre o elemento onde o campo 'id' é igual a..."
                // CORREÇÃO: O valor esperado deve ser igual ao valor usado na atualização
                .jsonPath("$[?(@.id == %d)].nome".formatted(createdTodo.getId())).isEqualTo("Nome Atualizado")
                .jsonPath("$[?(@.id == %d)].descricao".formatted(createdTodo.getId())).isEqualTo("Descrição Atualizada")
                .jsonPath("$[?(@.id == %d)].realizado".formatted(createdTodo.getId())).isEqualTo(true)
                .jsonPath("$[?(@.id == %d)].prioridade".formatted(createdTodo.getId())).isEqualTo(1);


    }

}
