package org.example.eventos.dto;

import jakarta.persistence.OneToMany;
import org.example.eventos.model.Evento;

public record LocalEventoResponseDTO (

        //TODO: colocar dados trazidos no response
        Long id,
        String nome,
        String endereco,
        Integer capacidade
){
}
