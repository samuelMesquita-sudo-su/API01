package org.example.eventos.repository;

import org.example.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    // TODO: criar método para filtrar eventos pelo nome.
    List<Evento> findByNomeContainigIgnoreCase (String nome);
    //TODO: criar método para listar eventos de um determinado local.
    List<Evento> findByLocalId (Long localId);

}
