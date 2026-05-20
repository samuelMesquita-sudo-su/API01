package org.example.eventos.repository;

import org.example.eventos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    //TODO: criar método para listar inscrições de um determinado evento.
    List<Inscricao> findByEventoId (Long eventoId);
}
