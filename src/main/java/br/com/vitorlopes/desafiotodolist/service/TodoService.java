package br.com.vitorlopes.desafiotodolist.service;

import br.com.vitorlopes.desafiotodolist.entity.Todo;
import br.com.vitorlopes.desafiotodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Encapsula as regras de negócio para o gerenciamento de tarefas (Todos).
 * Esta classe é responsável por coordenar as operações de criação, listagem,
 * atualização e exclusão de tarefas, interagindo com a camada de repositório.
 */
@Service
public class TodoService {
    private TodoRepository todoRepository;
    /**
     * Construtor que utiliza injeção de dependência para obter uma instância do TodoRepository.
     * @param todoRepository O repositório para acesso aos dados das tarefas.
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Cria uma nova tarefa no banco de dados.
     * Após salvar, retorna a lista completa e atualizada de todas as tarefas.
     * @param todo A entidade Todo a ser salva.
     * @return A lista de todas as tarefas ordenadas.
     */
    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    /**
     * Lista todas as tarefas existentes no banco de dados.
     * A listagem é ordenada por prioridade (do maior para o menor) e, como critério
     * de desempate, por nome (em ordem alfabética).
     * @return Uma lista (List<Todo>) com todas as tarefas ordenadas.
     */
    public List<Todo> list(){
        Sort sort = Sort.by("prioridade").descending().and
                (Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    /**
     * Atualiza uma tarefa existente no banco de dados.
     * O método `save` do JpaRepository lida tanto com a criação quanto com a
     * atualização, dependendo se a entidade possui um ID.
     * @param todo A entidade Todo com os dados atualizados (incluindo o ID).
     * @return A lista de todas as tarefas ordenadas após a atualização.
     */
    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    /**
     * Deleta uma tarefa do banco de dados com base no seu ID.
     * @param id O identificador único da tarefa a ser deletada.
     * @return A lista de todas as tarefas ordenadas após a exclusão.
     */
    public List<Todo> delete(int id){
        todoRepository.deleteById(id);
        return list();
    }
}
