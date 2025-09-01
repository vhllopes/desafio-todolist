package br.com.vitorlopes.desafiotodolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Entidade que representa uma tarefa (Todo).
 * Regras de validação via Jakarta Validation.
 */
@Entity
@Table(name = "todos")
public class Todo {
    /** Identificador gerado automaticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nome da tarefa. Não pode ser vazio. */
    @NotBlank
    private String nome;

    /** Descrição detalhada da tarefa. Não pode ser vazia. */
    @NotBlank
    private String descricao;

    /** Status de conclusão. */
    private boolean status;

    /** Prioridade da tarefa (deve ser > 0). */
    @Positive
    private int prioridade;

    //Construtores
    public Todo(int id, String nome, String descricao, boolean status, int prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public Todo(String nome, String descricao, boolean status, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public Todo() {

    }

    // getters e setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }


}
