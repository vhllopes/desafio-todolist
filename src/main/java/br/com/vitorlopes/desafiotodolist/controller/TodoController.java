package br.com.vitorlopes.desafiotodolist.controller;


import br.com.vitorlopes.desafiotodolist.entity.Todo;
import br.com.vitorlopes.desafiotodolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciar as operações CRUD de tarefas (Todos).
 * Esta classe define os endpoints da API para interagir com as tarefas,
 * recebendo requisições HTTP e delegando a lógica de negócio para o TodoService.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    /**
     * Construtor para injeção de dependência do TodoService.
     * @param todoService A camada de serviço que contém a lógica de negócio das tarefas.
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Endpoint para criar uma nova tarefa.
     * Mapeado para requisições HTTP POST em /todos.
     * A anotação @Valid ativa as validações definidas na entidade Todo.
     * @param todo O objeto Todo, vindo do corpo da requisição (@RequestBody), a ser criado.
     * @return A lista completa de tarefas após a criação da nova.
     */
    @PostMapping
    List<Todo> create(@RequestBody @Valid Todo todo){
        return todoService.create(todo);
    }

    /**
     * Endpoint para listar todas as tarefas.
     * Mapeado para requisições HTTP GET em /todos.
     * @return A lista completa de tarefas, ordenadas por prioridade e nome.
     */
    @GetMapping
    List<Todo> list(){
        return todoService.list();
    }

    /**
     * Endpoint para atualizar uma tarefa existente.
     * Mapeado para requisições HTTP PUT em /todos.
     * @param todo O objeto Todo com os dados atualizados, vindo do corpo da requisição.
     * O objeto deve conter o ID da tarefa a ser atualizada.
     * @return A lista completa de tarefas após a atualização.
     */
    @PutMapping
    List<Todo> update(@RequestBody Todo todo){
        return todoService.update(todo);
    }

    /**
     * Endpoint para deletar uma tarefa pelo seu ID.
     * Mapeado para requisições HTTP DELETE em /todos/{id}.
     * @param id O ID da tarefa a ser deletada, vindo da variável de caminho (@PathVariable).
     * @return A lista completa de tarefas após a exclusão.
     */
    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") int id){
        return todoService.delete(id);
    }
}
