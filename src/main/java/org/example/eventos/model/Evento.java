package org.example.eventos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.eventos.model.LocalEvento;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String nome;

    private String descricao;

    //exemplo = "2026-05-19"
    private LocalDate dataEvento;

    private Double valorIngresso;

    //TO DO: corrigir o relacionamento entre Evento e LocalEvento
    @ManyToOne
    @JoinColumn(name = "Evento")
    private LocalEvento localId;

    @OneToMany(mappedBy = "idInscricao")
    private List<Inscricao> inscricoes;

    public Evento() {
    }
}


