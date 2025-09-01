package br.com.vitorlopes.desafiotodolist.repository;

import br.com.vitorlopes.desafiotodolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
