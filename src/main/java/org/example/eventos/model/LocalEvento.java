package org.example.eventos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class LocalEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocal;

    private String nome;

    private String endereco;

    private Integer capacidade;

    @OneToMany(mappedBy = "idEvento")
    private List<Evento> eventos;

    public LocalEvento(){}
/*
    public Long getIdLocal() {
        return idLocal;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setIdEvento(List<Evento> eventos) {
        this.eventos = eventos;
    }
    */
}
